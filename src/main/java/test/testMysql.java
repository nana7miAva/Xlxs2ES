package test;

import Utils.CreatEs;
import Utils.MinioUtil;
import cache.MemoryCache;
import com.alibaba.excel.EasyExcel;
import entity.CaseDetailInfo;
import listener.*;
import mysql.MysqlDao;
import org.elasticsearch.client.RestHighLevelClient;
import thread.loadCaseInfoThread;
import toes.*;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author：WeiPengCheng&Z
 * @Date：2022-05-31 16:39
 **/
public class testMysql {
    public static void main(String[] args) throws Exception {

        MysqlDao mysqlDao = new MysqlDao();

        new Thread(new loadCaseInfoThread()).start();


        RestHighLevelClient esClient = new CreatEs().createEsClient();
        MinioUtil minioUtil = new MinioUtil();
        while (true) {


            for (Map.Entry<String, CaseDetailInfo> stringCaseDetailInfoEntry : MemoryCache.schemeCache.entrySet()) {

                System.out.println("当前案例" + stringCaseDetailInfoEntry.getValue().getName());

                CaseDetailInfo value = stringCaseDetailInfoEntry.getValue();
                String caseId = value.getCaseId();
                String wosName = value.getWosName();
                String name = value.getName();//文件名称
                String caseDetailId = value.getCaseDetailId();

                if (value.getAnalysisStatus() != 1) {
                    continue;
                }

               /* if (name.contains("支付宝") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, zfbTrack.class, new zfbTrackListener(caseId, esClient)).sheet("18701457955").doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }

                if (name.contains("JZ") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, jzTrack.class, new jzTrackListener(caseId, esClient)).sheet("压缩结果").doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);

                }


                if (name.contains("公交") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, BusFlow.class, new BusFlowListener(caseId, esClient)).headRowNumber(4).sheet().doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }

                if (name.startsWith("健康宝") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, jkbTrack.class, new jkbTrackListener(caseId, esClient)).sheet("扫码人查询结果").doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }


                if (name.contains("微信") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, WxTrade.class, new WxTrackListener(caseId, esClient)).sheet().doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }

                if (name.contains("健康宝扫码") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, b_jkbTrack.class, new b_jkbTrackListener(caseId, esClient)).sheet("被扫码人查询结果").doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }*/

                if (name.contains("B") && name.contains("落位") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, b_lwTrack.class, new b_lwTrackListener(caseId, esClient)).sheet().doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }

                if (name.contains("B") && name.contains("落位") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, bTrack.class, new bTrackListener(caseId, esClient)).sheet().doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }

                if (name.contains("A") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, aTrack.class, new aTrackListener(caseId, esClient)).sheet().doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }

                if (name.contains("G") && name.contains("xls")) {
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 2);
                    InputStream mediaByObjectName = minioUtil.getMediaByObjectName(wosName, "");
                    EasyExcel.read(mediaByObjectName, gTrack.class, new gTrackListener(caseId, esClient)).sheet().doRead();
                    mysqlDao.updateAnalysisStatusBtId(value.getCaseDetailId(), 3);
                }


                //根据caseId 查询detail表的excel文件状态
                List<CaseDetailInfo> caseDetailInfo = mysqlDao.getAllDetailDataByAnalysis(caseId);

                if (caseDetailInfo.size() == 0 || caseDetailInfo.isEmpty()) {
                    mysqlDao.updateExcelStatusBtId(caseId, 3);
                } else {
                    mysqlDao.updateExcelStatusBtId(caseId, 2);
                }


                System.out.println("案例:" + stringCaseDetailInfoEntry);
                MemoryCache.schemeCache.remove(caseDetailId);
                System.out.println("正在跑:" + stringCaseDetailInfoEntry.getValue());

            }
            Thread.sleep(1000 * 30);
        }

        //esClient.close();
    }
}
