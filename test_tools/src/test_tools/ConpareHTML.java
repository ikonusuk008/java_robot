package test_tools;
import java.io.File;
import java.io.FileOutputStream;
import javax.lang.model.util.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class ConpareHTML {
   public static void main(String[] args) {
      String p1 = "";
      Document ph1 = Jsoup.parse(new File(p1),"utf-8");

   }

   private static void p1_w_book(String f_name,Elements e1, String t1) {

      File　f1　= new File(f_name);

      Workbook outputWorkbook = new XSSFWorkbook();

      // シートを作成
      Sheet outputSheet = outputWorkbook.createSheet("user_data");

      // 行を作成
      Row outputRow = outputSheet.createRow(0);

      // セルを作成
      Cell outputCell_name = outputRow.createCell(0);
      Cell outputCell_sex = outputRow.createCell(1);
      Cell outputCell_age = outputRow.createCell(2);

      // セルに値を設定
      outputCell_name.setCellValue("田中太郎");
      outputCell_sex.setCellValue("男性");
      outputCell_age.setCellValue("26歳");

      // 出力用のストリームを用意
      FileOutputStream out = new FileOutputStream("User.xlsx");

      // ファイルへ出力
      outputWorkbook.write(out);

   }
}
