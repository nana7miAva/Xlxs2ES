package listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import toes.bTrack;
import toes.b_lwTrack;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class b_lwTrackListener extends AnalysisEventListener<b_lwTrack> {


    private String caseID;
    private RestHighLevelClient restHighLevelClient;

    public b_lwTrackListener(String caseID, RestHighLevelClient restHighLevelClient) {
        this.caseID = caseID;
        this.restHighLevelClient = restHighLevelClient;
    }

    @SneakyThrows
    @Override
    public void invoke(b_lwTrack b_lwTrack, AnalysisContext analysisContext) {
        JSONObject toJSON = (JSONObject) JSONObject.toJSON(b_lwTrack);


        JSONObject jsonObject = new JSONObject();


        //增加两个key
        toJSON.put("caseID", caseID); //从mysql库查询添加
        toJSON.put("ownerID", caseID);
        toJSON.put("startTime", toJSON.getString("jkb_scan_date"));
        toJSON.put("addressSource", "b表_落位");
        toJSON.put("addressFromTable", toJSON.getString("jkb_code_location"));

        jsonObject.put("颜色原因", toJSON.getString("b_color_why"));
        jsonObject.put("是否白名单", toJSON.getString("is_w"));
        jsonObject.put("新补姓名", toJSON.getString("xb_name"));
        jsonObject.put("新补身份证", toJSON.getString("xb_id"));
        jsonObject.put("新补手机号1", toJSON.getString("xb_phone1"));
        jsonObject.put("新补手机号2", toJSON.getString("xb_phone2"));
        jsonObject.put("地址(落位)", toJSON.getString("lw_location"));
        jsonObject.put("地址关联字段(落位)", toJSON.getString("lw_loca_gl"));
        jsonObject.put("时间(落位)", toJSON.getString("lw_date"));
        jsonObject.put("区划(落位)", toJSON.getString("lw_qh"));
        jsonObject.put("地址来源(落位)", toJSON.getString("lw_ly"));
        jsonObject.put("人员状态(落位)", toJSON.getString("lw_zt"));
        jsonObject.put("旅店名称(落位)", toJSON.getString("lw_ld"));
        jsonObject.put("入住时间(落位)", toJSON.getString("lw_rz_date"));
        jsonObject.put("离店时间(落位)", toJSON.getString("lw_ld_date"));
        jsonObject.put("所属派出所(落位)", toJSON.getString("lw_pcs"));

        toJSON.put("remark", jsonObject.toJSONString());



        //标准化地址是否解析状态 1未解析3解析完成
        toJSON.put("flow_map_status", 1);

        IndexRequest request = new IndexRequest("flow_test5");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON, XContentType.JSON);
        //remark里面有的 tojson里面就不要了
        toJSON.remove("b_color_why");
        toJSON.remove("is_w");
        toJSON.remove("xb_name");
        toJSON.remove("xb_id");
        toJSON.remove("xb_phone1");
        toJSON.remove("xb_phone2");
        toJSON.remove("lw_location");
        toJSON.remove("lw_loca_gl");
        toJSON.remove("lw_date");
        toJSON.remove("lw_qh");
        toJSON.remove("lw_ly");
        toJSON.remove("lw_zt");
        toJSON.remove("lw_ld");
        toJSON.remove("lw_rz_date");
        toJSON.remove("lw_ld_date");
        toJSON.remove("lw_pcs");





        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(toJSON);
        Thread.sleep(1000*100);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
