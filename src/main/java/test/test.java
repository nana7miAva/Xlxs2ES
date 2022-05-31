package test;

import com.alibaba.excel.EasyExcel;
import listener.jzTrackListener;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import toes.jzTrack;

import java.io.File;
import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {

        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("172.16.10.49", 9200, "http"));
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        GetIndexRequest getIndexRequest = new GetIndexRequest("flow_test2");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
        restHighLevelClient.close();

    }

    public static void main2(String[] args) {

        String b_jkb = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\新世界百货健康宝扫码记录.xlsx";
        String jkb = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\健康宝.xlsx";
        String zfb = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\支付宝交易数据.xlsx";
        String bus = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\公交出行记录.xlsx";
        String wx = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\微信交易数据.xlsx";

        //文件的路径
        String jz = "C:\\Users\\39067\\AppData\\Roaming\\Tencent\\WXWork\\Data\\1688856634259340\\Cache\\File\\2022-05\\JZ轨迹.xlsx";
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

    }
}
