package Demo;

import Utils.CreatEs;
import Utils.MinioUtil;
import com.alibaba.excel.EasyExcel;

import org.elasticsearch.client.RestHighLevelClient;
import toes.jzTrack;
import listener.jzTrackListener;

import java.io.InputStream;

public class MInIOTest {
    public static void main(String[] args) throws Exception {

        //从mysql拿到对应的wosName 最好还有任务名称
        String wosName = "609c9fd9b13659be349b5eb3c7c5ef65.xlsx";
        MinioUtil minioUtil = new MinioUtil();

        //File fileByObjectName = minioUtil.getFileByObjectName(wosName, "bucket-traceability");

        InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");


        CreatEs creatEs = new CreatEs();
        RestHighLevelClient esClient = creatEs.createEsClient();
        EasyExcel.read(mediaByObjectName, jzTrack.class, new jzTrackListener("1",esClient)).sheet("压缩结果").doRead();


    }


}
