package com.mzw.cipher.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * @author Jonathan Meng
 * @date 09/05/2019
 */
public class CipherUtils {
    public static final String ALGORITHM_DH = "DH";
    public static final String ALGORITHM_DES = "DES";
    public static final int DEFAULT_ALGORITHM_DH_KEY_SIZE = 512;

    /**
     * 创建dh发送方key
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] createDHSenderKey() throws NoSuchAlgorithmException {
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_DH);
        senderKeyPairGenerator.initialize(DEFAULT_ALGORITHM_DH_KEY_SIZE);
        KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
        return senderKeyPair.getPublic().getEncoded();
    }

    public static void jdkDh() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        // 初始化发送方密匙
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_DH);
        senderKeyPairGenerator.initialize(DEFAULT_ALGORITHM_DH_KEY_SIZE);
        KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
        byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();

        // 初始化接收方密匙
        KeyFactory receiverKeyFactory = KeyFactory.getInstance(ALGORITHM_DH);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
        PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
        KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_DH);
        receiverKeyPairGenerator.initialize(dhParameterSpec);
        KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
        PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
        byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();
        System.out.println("receiver public key:"+ Base64.encodeBase64String(receiverPublicKeyEnc));

        // 密匙构建
        KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance(ALGORITHM_DH);
        receiverKeyAgreement.init(receiverPrivateKey);
        receiverKeyAgreement.doPhase(receiverPublicKey, true);
        SecretKey receiverDesKey = receiverKeyAgreement.generateSecret(ALGORITHM_DES);

        KeyFactory senderKeyFactory = KeyFactory.getInstance(ALGORITHM_DH);
        x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
        PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
        KeyAgreement senderKeyAgreement = KeyAgreement.getInstance(ALGORITHM_DH);
//        senderKeyAgreement.init(senderKeyPair.getPrivate());
        senderKeyAgreement.init(senderKeyPair.getPrivate());
        senderKeyAgreement.doPhase(senderPublicKey, true);
        SecretKey senderDesKey = senderKeyAgreement.generateSecret(ALGORITHM_DES);
        if (Objects.equals(receiverDesKey, senderDesKey)) {
            System.out.println("双方密匙相同");
        }

        // 加密
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
        byte[] result = cipher.doFinal("test".getBytes());
        System.out.println("bc dh encrypt:" + Base64.encodeBase64String(result));
        //解密

        cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
        result = cipher.doFinal(result);
        System.out.println("bc dh decrypt:" + new String(result));
    }

    public static void jdkElgamal() {
        try {
// 加入对BouncyCastle支持  
            Security.addProvider(new BouncyCastleProvider());

// 1.初始化发送方密钥
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("Elgamal");
// 初始化参数生成器
            algorithmParameterGenerator.init(256);
// 生成算法参数
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
// 构建参数材料
            DHParameterSpec dhParameterSpec = (DHParameterSpec) algorithmParameters.getParameterSpec(DHParameterSpec.class);
// 实例化密钥生成器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Elgamal");
// 初始化密钥对生成器  
            keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
// 公钥
            PublicKey elGamalPublicKey = keyPair.getPublic();
// 私钥 
            PrivateKey elGamalPrivateKey = keyPair.getPrivate();
            System.out.println("Public Key:" + Base64.encodeBase64String(elGamalPublicKey.getEncoded()));
            System.out.println("Private Key:" + Base64.encodeBase64String(elGamalPrivateKey.getEncoded()));


// 2.私钥解密、公钥加密 ---- 加密
// 初始化公钥  
// 密钥材料转换
            X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
// 实例化密钥工厂
            KeyFactory keyFactory2 = KeyFactory.getInstance("Elgamal");
// 产生公钥
            PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);
// 数据加密 
// Cipher cipher2 = Cipher.getInstance("Elgamal");
            Cipher cipher2 = Cipher.getInstance(keyFactory2.getAlgorithm());
            cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
            byte[] result2 = cipher2.doFinal("test".getBytes());
            System.out.println("私钥加密、公钥解密 ---- 加密:" + Base64.encodeBase64String(result2));

// 3.私钥解密、公钥加密 ---- 解密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
            KeyFactory keyFactory5 = KeyFactory.getInstance("Elgamal");
            PrivateKey privateKey5 = keyFactory5.generatePrivate(pkcs8EncodedKeySpec5);
//	Cipher cipher5 = Cipher.getInstance("Elgamal");
            Cipher cipher5 = Cipher.getInstance(keyFactory5.getAlgorithm());
            cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
            byte[] result5 = cipher5.doFinal(result2);
            System.out.println("Elgamal 私钥加密、公钥解密 ---- 解密:" + new String(result5));




            /*
// 私钥加密、公钥解密: 有问题
// 4.私钥加密、公钥解密 ---- 加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("Elgamal");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("Elgamal");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal("test".getBytes());
            System.out.println("私钥加密、公钥解密 ---- 加密:" + Base64.encodeBase64String(result));

// 5.私钥加密、公钥解密 ---- 解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("Elgamal");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("Elgamal");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("Elgamal 私钥加密、公钥解密 ---- 解密:" + new String(result));
*/

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        jdkDh();
//        jdkElgamal();
    }
}
