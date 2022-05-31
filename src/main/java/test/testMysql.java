package test;

import Utils.CreatEs;
import Utils.MinioUtil;
import cache.MemoryCache;
import com.alibaba.excel.EasyExcel;
import entity.CaseDetailInfo;
import listener.*;
import org.elasticsearch.client.RestHighLevelClient;
import thread.loadCaseInfoThread;
import toes.*;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author：WeiPengCheng
 * @Date：2022-05-31 16:39
 **/
public class testMysql {
    public static void main(String[] args) throws Exception {

        new Thread(new loadCaseInfoThread()).start();


        RestHighLevelClient esClient = new CreatEs().createEsClient();
        MinioUtil minioUtil = new MinioUtil();
        for (Map.Entry<String, CaseDetailInfo> stringCaseDetailInfoEntry : MemoryCache.schemeCache.entrySet()) {
            CaseDetailInfo value = stringCaseDetailInfoEntry.getValue();
            String caseId = value.getCaseId();
            String wosName = value.getWosName();
            String name = value.getName();//文件名称

            if (name.contains("JZ") && name.contains("xls")) {
                InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                EasyExcel.read(mediaByObjectName, jzTrack.class, new jzTrackListener(caseId, esClient)).sheet("压缩结果").doRead();
            }

            if (name.contains("公交") && name.contains("xls")) {
                InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                EasyExcel.read(mediaByObjectName, BusFlow.class, new BusFlowListener()).headRowNumber(4).sheet().doRead();
            }

            if (name.contains("健康宝") && name.contains("xls")) {
                InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                EasyExcel.read(mediaByObjectName, jkbTrack.class, new jkbTrackListener()).sheet("扫码人查询结果").doRead();
            }

            if (name.contains("支付宝") && name.contains("xls")) {
                InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                EasyExcel.read(mediaByObjectName, zfbTrack.class, new zfbTrackListener()).sheet("18701457955").doRead();
            }

            if (name.contains("微信") && name.contains("xls")) {
                InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                EasyExcel.read(mediaByObjectName, WxTrade.class, new WxTrackListener()).sheet().doRead();
            }


            if (name.contains("扫码记录") && name.contains("xls")) {
                InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                EasyExcel.read(mediaByObjectName, b_jkbTrack.class, new b_jkbTrackListener()).sheet("被扫码人查询结果").doRead();
            }





            //String jz = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\JZ轨迹.xlsx";
            //String b_jkb = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\新世界百货健康宝扫码记录.xlsx";
            //String jkb = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\健康宝.xlsx";
            //String zfb = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\支付宝交易数据.xlsx";
            //String bus = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\公交出行记录.xlsx";
            //String wx = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\微信交易数据.xlsx";

            //文件的路径

            //需要指定去哪个excel中读取，然后读取到第一个sheet之后文件流就会自动关闭
            /**
             * new File(fileName):需要读取的文件
             * User.class：读出来的数据需要绑定哪一个类
             * new UserListener()：读出来的数据需要如何处理
             */
            //ArrayList<jzTrack> jz = new ArrayList<>();

            //基站
            //EasyExcel.read(new File(jz), jzTrack.class, new jzTrackListener()).sheet("压缩结果").doRead();

            //健康宝
            //EasyExcel.read(new File(jkb), jkbTrack.class, new jkbTrackListener()).sheet("扫码人查询结果").doRead();

            //支付宝
            //EasyExcel.read(new File(zfb), zfbTrack.class, new zfbTrackListener()).sheet("18701457955").doRead();

            //微信
            //EasyExcel.read(new File(wx), WxTrade.class, new WxTrackListener()).sheet().doRead();

            //公交
            //EasyExcel.read(new File(bus), BusFlow.class, new BusFlowListener()).headRowNumber(4).sheet().doRead();

            //被扫记录
            //EasyExcel.read(new File(b_jkb), b_jkbTrack.class, new b_jkbTrackListener()).sheet("被扫码人查询结果").doRead();


            System.out.println("案例:" + stringCaseDetailInfoEntry);
        }

        esClient.close();
    }
}
