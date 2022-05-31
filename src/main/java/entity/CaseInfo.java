package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei
 * @since 2022-05-31
 */
public class CaseInfo implements Serializable {


    /**
     * 任务ID
     */
    private String caseId;

    /**
     * 任务名
     */
    private String caseName;

    /**
     * 报告解析状态（1未解析 2解析中 3解析完成）
     */
    private Integer wordStatus;

    /**
     * excel解析状态（1未解析 2解析中 3解析完成）
     */
    private Integer excelStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 碰撞状态
     * @return
     */
    private int collisionStatus;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public Integer getWordStatus() {
        return wordStatus;
    }

    public void setWordStatus(Integer wordStatus) {
        this.wordStatus = wordStatus;
    }

    public Integer getExcelStatus() {
        return excelStatus;
    }

    public void setExcelStatus(Integer excelStatus) {
        this.excelStatus = excelStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCollisionStatus() {
        return collisionStatus;
    }

    public void setCollisionStatus(int collisionStatus) {
        this.collisionStatus = collisionStatus;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
                "caseId='" + caseId + '\'' +
                ", caseName='" + caseName + '\'' +
                ", wordStatus=" + wordStatus +
                ", excelStatus=" + excelStatus +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", updateTime=" + updateTime +
                ", collisionStatus=" + collisionStatus +
                '}';
    }
}
