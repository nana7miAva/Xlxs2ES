package toes;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class aTrack {



    @ExcelProperty(value = "扫码时间")
    private String jkb_scan_date;
    @ExcelProperty(value = "姓名")
    private String scan_name;
    @ExcelProperty(value = "身份证号")
    private String jkb_scaner_num;
    @ExcelProperty(value = "手机号码")
    private String jkb_scaner_phone;
    @ExcelProperty(value = "场所负责人")
    private String jkb_b_scaner_name;
    @ExcelProperty(value = "场所负责人证件号码")
    private String jkb_b_scaner_num;
    @ExcelProperty(value = "场所负责人电话")
    private String jkb_b_scaner_phone;
    @ExcelProperty(value = "场所名称")
    private String jkb_code_unit;
    @ExcelProperty(value = "二维码地址")
    private String jkb_code_number;
    @ExcelProperty(value = "场所地址")
    private String jkb_code_location;
    //private String jkb_code_location_poi;
    @ExcelProperty(value = "健康码颜色")
    private String jkb_code_color;
    @ExcelProperty(value = "扫码方式")
    private String jkb_code_type;
    @ExcelProperty(value = "场所id")
    private String jkb_code;
    @ExcelProperty(value = "健康码颜色原因")
    private String b_color_why;
}
