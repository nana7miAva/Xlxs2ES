package toes;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class b_jkbTrack {


    @ExcelProperty(value = "扫码时间")
    private String jkb_scan_date;
    @ExcelProperty(value = "扫码人证件号码")
    private String jkb_scaner_num;
    @ExcelProperty(value = "扫码人联系电话")
    private String jkb_scaner_phone;
    @ExcelProperty(value = "被扫码人姓名")
    private String jkb_b_scaner_name;
    @ExcelProperty(value = "被扫码人证件号码")
    private String jkb_b_scaner_num;
    @ExcelProperty(value = "被扫码人联系电话")
    private String jkb_b_scaner_phone;
    @ExcelProperty(value = "二维码单位")
    private String jkb_code_unit;
    @ExcelProperty(value = "二维码地址")
    private String jkb_code_number;
    @ExcelProperty(value = "二维码详址")
    private String jkb_code_location;
    //private String jkb_code_location_poi;
    @ExcelProperty(value = "扫码颜色")
    private String jkb_code_color;
    @ExcelProperty(value = "扫码类型")
    private String jkb_code_type;
    @ExcelProperty(value = "二维码")
    private String jkb_code;


}
