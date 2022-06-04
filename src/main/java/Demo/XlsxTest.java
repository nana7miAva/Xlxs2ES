package Demo;

import Utils.CreatEs;
import com.alibaba.excel.EasyExcel;
import listener.aTrackListener;
import listener.bTrackListener;
import listener.b_lwTrackListener;
import listener.gTrackListener;
import org.elasticsearch.client.RestHighLevelClient;
import toes.aTrack;
import toes.bTrack;
import toes.b_lwTrack;
import toes.gTrack;

import java.io.File;
import java.io.IOException;

public class XlsxTest {
    public static void main(String[] args) throws IOException {
        CreatEs creatEs = new CreatEs();
        RestHighLevelClient esClient = creatEs.createEsClient();


        File file = new File("C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-06\\表B.xlsx");
        //EasyExcel.read(file, bTrack.class, new bTrackListener("123", esClient)).sheet().doRead();


        File file2 = new File("C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-06\\表B_落位.xlsx");
        //EasyExcel.read(file2, b_lwTrack.class, new b_lwTrackListener("123", esClient)).sheet().doRead();

        File file3 = new File("C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-06\\表A.xlsx");
        //EasyExcel.read(file3, aTrack.class, new aTrackListener("123", esClient)).sheet().doRead();

        File file4 = new File("C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-06\\G-石文军.xlsx");
        EasyExcel.read(file4, gTrack.class, new gTrackListener("123", esClient)).sheet().doRead();
    }
}
