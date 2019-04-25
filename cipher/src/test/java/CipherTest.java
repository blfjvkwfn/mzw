
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.util.Base64;

/**
 * cipher test
 *
 * @author Jonathan Meng
 * @date 24/04/2019
 */
public class CipherTest {
    private static final String DEFAULT_ENCODING_UTF_8 = "UTF-8";
    private static final String DES_KEY = "desKey11";
    // must be 8 bytes long
    private static final String DES_IV = "desIv222";
    private static final String DATA = "{\"applyNo\":\"1\",\"certNo\":\"2\",\"userMobile\":\"3\"}";
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
    private static final String SECRET_KEY_ALGORITHM = "DES";

    public static void main(String[] args) throws Exception {
        String securityParam = encrypt(DATA.getBytes());
        System.out.println(securityParam);

        System.out.println(decrypt(securityParam));
    }

    private static String decrypt(String securityParam) throws Exception {
        Cipher decrypt = getCipher(Cipher.DECRYPT_MODE);
        byte[] result = decrypt.doFinal(Base64.getDecoder().decode(securityParam));
        return new String(result, DEFAULT_ENCODING_UTF_8);
    }

    private static String encrypt(byte[] bytes) throws Exception {
        Cipher encrypt = getCipher(Cipher.ENCRYPT_MODE);
        return new String(Base64.getEncoder().encode(encrypt.doFinal(bytes)), DEFAULT_ENCODING_UTF_8);
    }

    private static Cipher getCipher(int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // set encrypt mode
        cipher.init(mode, getSecretKey(), new IvParameterSpec(DES_IV.getBytes(DEFAULT_ENCODING_UTF_8)));
        return cipher;
    }

    private static Key getSecretKey() throws Exception {
        return SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM).generateSecret(new DESKeySpec(DES_KEY.getBytes(DEFAULT_ENCODING_UTF_8)));
    }
}
