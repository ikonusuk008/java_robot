package test_tools;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.lang.model.util.Elements;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class ConpareHTML {

	public static void main(String[] args) throws IOException {
		String						p1		= "aaa";										//html path1
		Document					ph1		= Jsoup.parse(new File(p1), "utf-8");
		String						p2		= "aaa";										//html path1
		Document					ph2		= Jsoup.parse(new File(p1), "utf-8");
		org.jsoup.select.Elements	e1		= ph1.getElementsByTag("td");
		org.jsoup.select.Elements	e2		= ph2.getElementsByTag("td");
		String						path_p1	= p1.replace("", "").replace("html", "xlsx");
		String						path_p2	= p2.replace("", "").replace("html", "xlsx");
		p1_w_book(path_p1, e1, "subject");
		p2_w_book(path_p1, path_p2, e2, "subject");
	}

	private static void p2_w_book(String path_p1, String path_p2, org.jsoup.select.Elements e2, String t1)
																		throws EncryptedDocumentException, IOException {
		File f1 = new File(path_p2);
		if (f1.exists()) {
			f1.delete();
		}
		Workbook	outputWorkbook	= WorkbookFactory.create(new File(path_p1));
		Sheet		outputSheet		= outputWorkbook.getSheet("user_data");
		int			i1				= 0;
		for (Element element : e2) {
			Row		outputRow		= outputSheet.getRow(i1);
			Cell	outputCell_name	= outputRow.createCell(0);
			if (t1 == "js") {
				outputCell_name.setCellValue(element.attr("src"));
			} else if (t1 == "subject") {
				outputCell_name.setCellValue(element.ownText());
			}
			i1++;
		}
		FileOutputStream out = new FileOutputStream(path_p2);
		outputWorkbook.write(out);
	}

	private static void p1_w_book(String f_name, org.jsoup.select.Elements e1, String t1) throws IOException {
		File f1 = new File(f_name);
		if (f1.exists()) {
			f1.delete();
		}
		Workbook	outputWorkbook	= new XSSFWorkbook();
		Sheet		outputSheet		= outputWorkbook.createSheet("user_data");
		int			i1				= 0;
		for (Element element : e1) {
			Row		outputRow		= outputSheet.createRow(i1);
			Cell	outputCell_name	= outputRow.createCell(0);
			if (t1 == "js") {
				outputCell_name.setCellValue(element.attr("src"));
			} else if (t1 == "subject") {
				outputCell_name.setCellValue(element.ownText());
			}
			i1++;
		}
		FileOutputStream out = new FileOutputStream(f_name);
		outputWorkbook.write(out);
	}
}
