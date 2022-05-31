package thread;

import cache.MemoryCache;
import entity.CaseDetailInfo;
import entity.CaseInfo;
import mysql.MysqlDao;

import java.util.Date;
import java.util.List;

/**
 * @Author：WeiPengCheng
 * @Date：2022-05-31 16:10
 **/
public class loadCaseInfoThread implements Runnable {

    private MysqlDao mysqlDao;
    private Date lastUpdateTime;

    public loadCaseInfoThread(){
        this.mysqlDao = new MysqlDao();
        loadCaseInfo();
        this.lastUpdateTime = getBeforeTime(5);
    }
    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(10 * 1000);
                loadCaseInfo();
            } catch (InterruptedException e) {

            }
        }
    }

    //加载caseInfo中的任务到内存中
    public void loadCaseInfo() {

        while (true){
            List<CaseInfo> caseInfos = mysqlDao.getCaseInfo(lastUpdateTime);

            if (caseInfos.size() == 0 || caseInfos.isEmpty()){
                return;
            }

            for (CaseInfo caseInfo : caseInfos) {
                CaseDetailInfo caseDetailInfo = mysqlDao.getCaseDetailInfo(caseInfo.getCaseId());
                MemoryCache.schemeCache.put(caseInfo.getCaseId(),caseDetailInfo);
            }

        }
    }


    private Date getBeforeTime(int min) {
        return new Date(System.currentTimeMillis() - 1000L * 60 * min);
    }
}
