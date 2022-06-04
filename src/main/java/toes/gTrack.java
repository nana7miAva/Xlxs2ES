package toes;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class gTrack {



    @ExcelProperty(value = "起始时间")
    private String begin_date;
    @ExcelProperty(value = "结束时间")
    private String end_date;
    @ExcelProperty(value = "位置")
    private String location;

   /* @ExcelProperty(value = "序号")
    private String a_num;*/
    @ExcelProperty(value = "手机号")
    private String g_phone;


    @ExcelProperty(value = "起始地址")
    private String begin_location;
    @ExcelProperty(value = "起始经度")
    private String begin_location_x;
    @ExcelProperty(value = "起始纬度")
    private String begin_location_y;

    /*@ExcelProperty(value = "结束地址")
    private String end_location;
    @ExcelProperty(value = "结束经度")
    private String end_location_x;
    @ExcelProperty(value = "结束纬度")
    private String end_location_y;*/



}
