package toes;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.util.ArrayList;

public class test {


    public static void main(String[] args) {

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

        //EasyExcel.read(new File(jz), jzTrack.class, jzTrackListener).sheet("压缩结果").doRead();


        EasyExcel.read(new File(jkb), jkbTrack.class, new jkbTrackListener()).sheet("扫码人查询结果").doRead();




    }
}
