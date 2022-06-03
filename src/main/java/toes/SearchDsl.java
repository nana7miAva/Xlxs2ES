package toes;


import lombok.Data;


import java.util.Map;

@Data
public class SearchDsl {
    //@NotEmpty
    //@ApiModelProperty(value = "请求方式", required = true, example = "GET")
    private String method = "GET";
    //@NotEmpty
    //@ApiModelProperty(value = "请求url", required = true, example = "xxx/_search")
    private String endpoint;

    //@ApiModelProperty(value = "请求体")
    private Map<String, Object> dsl;
}
