import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * @author Jonathan Meng
 * @date 26/04/2019
 */
public class Test {
    public static void main(String[] args) {
        String fileName = "D:\\test\\AF(Application Form).docx";
        String pdfPath = "D:\\test";
        File demoFile = new File(fileName);
        try {
            FileInputStream in = new FileInputStream(demoFile);
            XWPFDocument document = new XWPFDocument(in);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.write(baos);
            byte[] bytes = baos.toByteArray();


            PdfOptions options = PdfOptions.create();
            XWPFDocument document1 = new XWPFDocument(new ByteArrayInputStream(bytes));
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            PdfConverter.getInstance().convert(document, baos1, options);

            System.out.println("hh12");

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}