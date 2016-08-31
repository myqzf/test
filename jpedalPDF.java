package wei;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;
import org.jpedal.fonts.FontMappings;

/**
 * jpedal pdf 转图片 
 * 缺点： pdf文件为英文时报错
 * 
 */
public class jpedalPDF {
	// E:/workspace/test05/pdffile/

	public static void main(String[] args) throws IOException, PdfException,
			InterruptedException {
		/** instance of PdfDecoder to convert PDF into image */
		PdfDecoder decode_pdf = new PdfDecoder(true);

		/** set mappings for non-embedded fonts to use */
		FontMappings.setFontReplacements();

		/** open the PDF file - can also be a URL or a byte array */
		try {
			decode_pdf.setExtractionMode(0, 1.33f); // 图片清晰度
			decode_pdf.openPdfFile("E:/workspace/test05/pdffile/2015-1.pdf"); // file

			// decode_pdf.openPdfFile("C:/myPDF.pdf", "password"); //encrypted
			// decode_pdf.openPdfArray(bytes); //bytes is byte[] array with PDF
			// decode_pdf.openPdfFileFromURL("http://www.mysite.com/myPDF.pdf",false);

			/** get page 1 as an image */
			// page range if you want to extract all pages with a loop
			int start = 1, end = decode_pdf.getPageCount();
			for (int i = start; i < end + 1; i++) {
				BufferedImage img = decode_pdf.getPageAsImage(i);
				ImageIO.write(img, "png", new File(
						"E:/workspace/test05/pdffile/123" + i + ".png"));
			}
			/** close the pdf file */
			decode_pdf.closePdfFile();
			System.out.println("ok");

		} catch (PdfException e) {
			e.printStackTrace();
		}
	}
}
