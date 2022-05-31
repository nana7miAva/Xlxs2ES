package Demo;

import Utils.MinioUtil;
import com.alibaba.excel.EasyExcel;

import toes.jzTrack;
import toes.jzTrackListener;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

public class MInIOTest {
    public static void main(String[] args) throws Exception {

        //从mysql拿到对应的wosName 最好还有任务名称
        String wosName = "609c9fd9b13659be349b5eb3c7c5ef65.xlsx";
        MinioUtil minioUtil = new MinioUtil();

        //File fileByObjectName = minioUtil.getFileByObjectName(wosName, "bucket-traceability");

        InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");


        EasyExcel.read(mediaByObjectName, jzTrack.class, new jzTrackListener()).sheet("压缩结果").doRead();


    }


}
