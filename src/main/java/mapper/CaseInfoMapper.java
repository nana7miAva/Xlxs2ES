package mapper;

import entity.CaseInfo;
import org.apache.ibatis.annotations.Param;

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

    //更新任务状态
    boolean updateExcelStatusBtId(@Param("caseId") String caseId, @Param("status") int status);



    //更新xlsx文件解析完成状态 end
    boolean updateEndExcelStatusBtId(@Param("caseId")String caseId, @Param("status")int analysisStatus);

}
