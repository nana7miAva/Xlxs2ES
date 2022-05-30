package toes;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class jkbTrack {


    //可以通过value去匹配
    @ExcelProperty(value = "扫码时间", index = 0)
    private String jkb_scan_date;
    @ExcelProperty(value = "扫码人证件号码", index = 2)
    private String jkb_scaner_num;
    @ExcelProperty(value = "扫码人联系电话", index = 3)
    private String jkb_scaner_phone;
    @ExcelProperty(value = "被扫码人姓名", index = 4)
    private String jkb_b_scaner_name;
    @ExcelProperty(value = "被扫码人证件号码", index = 5)
    private String jkb_b_scaner_num;
    @ExcelProperty(value = "被扫码人联系电话", index = 6)
    private String jkb_b_scaner_phone;
    @ExcelProperty(value = "二维码单位", index = 7)
    private String jkb_code_unit;
    @ExcelProperty(value = "二维码地址", index = 8)
    private String jkb_code_number;
    @ExcelProperty(value = "二维码详址", index = 9)
    private String jkb_code_location;
    //private String jkb_code_location_poi;
    @ExcelProperty(value = "扫码颜色", index = 10)
    private String jkb_code_color;
    @ExcelProperty(value = "扫码类型", index = 11)
    private String jkb_code_type;
    @ExcelProperty(value = "二维码", index = 12)
    private String jkb_code;


}
