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
public class BusFlow {
    /**
     * 公交流调
     */
    @ExcelProperty(value = "交易时间", index = 0)
    private String bus_trad_date;
    @ExcelProperty(value = "标记时间", index = 1)
    private String bus_tag_date;
    @ExcelProperty(value = "卡类型", index = 2)
    private String bus_card_type;
    @ExcelProperty(value = "交易计数", index = 3)
    private String bus_trad_number;
    @ExcelProperty(value = "应收金额(元)", index = 4)
    private String bus_pay_ys;
    @ExcelProperty(value = "实收(元)", index = 5)
    private String bus_pay_sh;
    @ExcelProperty(value = "余额(元)", index = 6)
    private String bus_pay_yu;
    @ExcelProperty(value = "线路名", index = 7)
    private String bus_line_name;
    @ExcelProperty(value = "车辆号", index = 8)
    private String bus_number;
    @ExcelProperty(value = "交易类型", index = 9)
    private String bus_pay_type;
    @ExcelProperty(value = "司机", index = 10)
    private String bus_driver_id;
    @ExcelProperty(value = "监票员", index = 11)
    private String bus_check_id;
    @ExcelProperty(value = "乘车方向", index = 12)
    private String bus_direc;
    @ExcelProperty(value = "标记站", index = 13)
    private String bus_tag_platform_id;
    @ExcelProperty(value = "标记站名称", index = 14)
    private String bus_tag_platform_name;
/*    @ExcelProperty(value = " 标记站poi", index = 15)
    private String bus_tag_platform_poi;*/
    @ExcelProperty(value = "交易站", index = 15)
    private String bus_pay_platform_id;
    @ExcelProperty(value = "交易站名称", index = 16)
    private String bus_pay_platform_name;
/*    @ExcelProperty(value = "交易站poi", index = 0)
    private String bus_pay_platform_poi;*/
    @ExcelProperty(value = "状态", index = 17)
    private String bus_type;
    @ExcelProperty(value = "逃票线路号", index = 18)
    private String bus_ticket;
    @ExcelProperty(value = "逃票线路名", index = 19)
    private String bus_ticket_linename;
    @ExcelProperty(value = "逃票车辆号", index = 20)
    private String bus_ticket_num;
    @ExcelProperty(value = "逃票原因", index = 21)
    private String bus_ticket_note;


}
