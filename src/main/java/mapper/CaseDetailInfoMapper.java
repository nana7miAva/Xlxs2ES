package mapper;

import entity.CaseDetailInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author：WeiPengCheng
 * @Date：2022-05-31 10:02
 **/
public interface CaseDetailInfoMapper {
    /**
     * 任务明细表
     */

    //获取明细表全字段
    List<CaseDetailInfo> getAllDetailData(@Param("caseDetailId") int caseDetailId);


    //根据创建时间获取明细表全字段
    List<CaseDetailInfo> getAllDetailDataByTime(@Param("caseDetailId") int caseDetailId, @Param("updateTime") Date updateTime);



    //
    List<CaseDetailInfo> getAllDetailDataById(@Param("caseDetailId") String caseDetailId);


    //更新任务状态
    boolean updateAnalysisStatusBtId(@Param("caseId") String caseId, @Param("status") int status);


}
