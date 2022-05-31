package toes;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class zfbTrack {

    @ExcelProperty(value = "交易号")
    private String zfb_id;
    @ExcelProperty(value = "商户订单号")
    private String zfb_order_id;
    @ExcelProperty(value = "交易创建时间")
    private String zfb_create_date;
    @ExcelProperty(value = "付款时间")
    private String zfb_pay_date;
    @ExcelProperty(value = "最近修改时间")
    private String zfb_Modify_date;
    @ExcelProperty(value = "交易来源地")
    private String zfb_source_address;
    @ExcelProperty(value = "类型")
    private String zfb_type;
    /*@ExcelProperty(value = "用户信息",   7)
    private String zfb_user_info;*/
    @ExcelProperty(value = "交易对方信息")
    private String zfb_face_info;
    @ExcelProperty(value = "消费名称")
    private String zfb_con_name;
    @ExcelProperty(value = "金额（元）")
    private String zfb_money;
    @ExcelProperty(value = "支付方式")
    private String zfb_way;
    @ExcelProperty(value = "收/支")
    private String zfb_in_or_out;
    @ExcelProperty(value = "交易状态")
    private String zfb_pay_type;
    @ExcelProperty(value = "备注")
    private String zfb_note;
    @ExcelProperty(value = "对应的协查数据")
    private String zfb_inv;


}
