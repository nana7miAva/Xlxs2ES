package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei
 * @since 2022-05-31
 */

public class CaseDetailInfo implements Serializable {

    /**
     * 任务明细ID
     */
    private String caseDetailId;

    /**
     * 任务ID
     */
    private String caseId;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 对象存储名称
     */
    private String wosName;

    /**
     * 文件md5值
     */
    private String md5;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 解析状态 1 未解析 2解析中
     */
    private int analysisStatus;

    public String getCaseDetailId() {
        return caseDetailId;
    }

    public void setCaseDetailId(String caseDetailId) {
        this.caseDetailId = caseDetailId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWosName() {
        return wosName;
    }

    public void setWosName(String wosName) {
        this.wosName = wosName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public int getAnalysisStatus() {
        return analysisStatus;
    }

    public void setAnalysisStatus(int analysisStatus) {
        this.analysisStatus = analysisStatus;
    }

    @Override
    public String toString() {
        return "TaskDetailInfo{" +
                "caseDetailId='" + caseDetailId + '\'' +
                ", caseId='" + caseId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", wosName='" + wosName + '\'' +
                ", md5='" + md5 + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", analysisStatus=" + analysisStatus +
                '}';
    }
}
