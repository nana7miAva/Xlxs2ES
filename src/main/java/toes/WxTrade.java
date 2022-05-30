package toes;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//用于生成所有属性的Get、Set方法
@Data
//用于生成拥有全部属性做参数的有参构造方法
@AllArgsConstructor
//用于生成无参构造方法
@NoArgsConstructor
public class WxTrade {
    /**
     * 微信交易数据
     */
    @ExcelProperty(value = "用户ID", index = 0)
    private String wx_user_id;
    @ExcelProperty(value = "交易单号", index = 1)
    private String wx_order_num;
    @ExcelProperty(value = "借贷类型", index = 2)
    private String wx_borr_type;
    @ExcelProperty(value = "交易业务类型", index = 3)
    private String wx_pay_type;
    @ExcelProperty(value = "交易用途类型", index = 4)
    private String wx_payto_type;
    @ExcelProperty(value = "交易时间", index = 5)
    private String wx_pay_date;
    @ExcelProperty(value = "交易金额(分)", index = 6)
    private String wx_pay_number;
    @ExcelProperty(value = "账户余额(分)", index = 7)
    private String wx_user_yu;
    @ExcelProperty(value = "用户银行卡号", index = 8)
    private String wx_user_banknum;
    @ExcelProperty(value = "用户侧网银联单号", index = 9)
    private String wx_user_bank_id;
    @ExcelProperty(value = "网联/银联", index = 10)
    private String wx_bank_id;
    @ExcelProperty(value = "第三方账户名称", index = 11)
    private String wx_acc_name;
    @ExcelProperty(value = "对手方ID", index = 12)
    private String wx_face_id;
    @ExcelProperty(value = "对手方银行卡号", index = 13)
    private String wx_face_banknum;
    @ExcelProperty(value = "对手侧网银联单号", index = 14)
    private String wx_face_user_bank_id;
    @ExcelProperty(value = "网联/银联", index =15)
    private String wx_face_bank_id;
    @ExcelProperty(value = "第三方账户名称", index = 16)
    private String wx_acc3_name;
    @ExcelProperty(value = "对手方接收时间", index = 17)
    private String wx_face_indate;
    @ExcelProperty(value = "对手方接收金额(分)", index = 18)
    private String wx_face_innum;
    @ExcelProperty(value = "备注1", index = 19)
    private String wx_remark1;
    @ExcelProperty(value = "备注2", index = 20)
    private String wx_remark2;
}
