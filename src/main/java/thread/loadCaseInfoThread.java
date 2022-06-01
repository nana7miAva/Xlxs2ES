package thread;

import cache.MemoryCache;

import entity.CaseDetailInfo;
import entity.CaseInfo;
import mysql.MysqlDao;


import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author：WeiPengCheng
 * @Date：2022-05-31 16:10
 **/
public class loadCaseInfoThread implements Runnable {

    private MysqlDao mysqlDao;
    private Date lastUpdateTime;
    private SimpleDateFormat simpleDateFormat;

    public loadCaseInfoThread() {
        this.mysqlDao = new MysqlDao();
        loadCaseInfo();
        this.lastUpdateTime = getBeforeTime(5);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(10 * 1000);
                loadCaseInfo();
                this.lastUpdateTime = getBeforeTime(5);
            } catch (InterruptedException e) {

            }
        }
    }


    //加载caseInfo中的任务到内存中
    public void loadCaseInfo() {


        while (true) {


            List<CaseInfo> caseInfos = mysqlDao.getCaseInfo();

            if (caseInfos.size() == 0 || caseInfos.isEmpty()) {
                return;
            }

            System.out.println("caseInfos:" + caseInfos.size());
            for (CaseInfo caseInfo : caseInfos) {
                System.out.println("aa");
                List<CaseDetailInfo> caseDetailInfo = mysqlDao.getCaseDetailInfo(caseInfo.getCaseId());

                if (Objects.isNull(caseDetailInfo)) {
                    continue;
                }

                System.out.println("12.----" + caseDetailInfo);

                for (CaseDetailInfo detailInfo : caseDetailInfo) {

                MemoryCache.schemeCache.put(detailInfo.getCaseDetailId(), detailInfo);
                }
            }

            break;

        }

    }


    private Date getBeforeTime(int min) {
        return new Date(System.currentTimeMillis() - 1000L * 60 * min);
    }
}
