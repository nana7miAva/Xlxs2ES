package toes;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class b_lwTrack {



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
    @ExcelProperty(value = "场所id")
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

    @ExcelProperty(value = "是否白名单")
    private String is_w;
    @ExcelProperty(value = "新补姓名")
    private String xb_name;
    @ExcelProperty(value = "新补身份证")
    private String xb_id;




    @ExcelProperty(value = "新补手机号1")
    private String xb_phone1;
    @ExcelProperty(value = "新补手机号2")
    private String xb_phone2;
    @ExcelProperty(value = "地址(落位)")
    private String lw_location;
    @ExcelProperty(value = "地址关联字段(落位)")
    private String lw_loca_gl;
    @ExcelProperty(value = "时间(落位)")
    private String lw_date;
    @ExcelProperty(value = "区划(落位)")
    private String lw_qh;
    @ExcelProperty(value = "地址来源(落位)")
    private String lw_ly;
    @ExcelProperty(value = "人员状态(落位)")
    private String lw_zt;

    @ExcelProperty(value = "旅店名称(落位)")
    private String lw_ld;
    @ExcelProperty(value = "入住时间(落位)")
    private String lw_rz_date;

    @ExcelProperty(value = "离店时间(落位)")
    private String lw_ld_date;
    @ExcelProperty(value = "所属派出所(落位)")
    private String lw_pcs;

}
