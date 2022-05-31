package mapper;

import entity.CaseInfo;

import java.util.Date;
import java.util.List;

/**
 * @Author：WeiPengCheng
 * @Date：2022-05-31 10:02
 **/
public interface CaseInfoMapper {

    //任务表

    //获取明细表全字段
    List<CaseInfo> getAllCaseInfo();


    //根据创建时间获取明细表全字段
    List<CaseInfo> getAllCaseInfoByTime(Date updateTime);

}
