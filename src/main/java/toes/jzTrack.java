package toes;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//用于生成所有属性的Get、Set方法
@Data
//用于生成拥有全部属性做参数的有参构造方法
@AllArgsConstructor
//用于生成无参构造方法
@NoArgsConstructor


public class jzTrack {

    @ExcelProperty(value = "起始时间", index = 0)
    private String begin_date;
    @ExcelProperty(value = "结束时间", index = 1)
    private String end_date;
    @ExcelProperty(value = "位置", index = 2)
    private String location;


}