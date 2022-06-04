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
import toes.aTrack;
import toes.bTrack;

public class aTrackListener extends AnalysisEventListener<aTrack> {


    private String caseID;
    private RestHighLevelClient restHighLevelClient;

    public aTrackListener(String caseID, RestHighLevelClient restHighLevelClient) {
        this.caseID = caseID;
        this.restHighLevelClient = restHighLevelClient;
    }

    @SneakyThrows
    @Override
    public void invoke(aTrack aTrack, AnalysisContext analysisContext) {

        JSONObject toJSON = (JSONObject) JSONObject.toJSON(aTrack);


        JSONObject jsonObject = new JSONObject();

        //增加两个key
        toJSON.put("caseID", caseID); //从mysql库查询添加
        toJSON.put("ownerID", caseID);
        toJSON.put("startTime", toJSON.getString("jkb_scan_date"));
        toJSON.put("addressSource", "A表");
        toJSON.put("addressFromTable", toJSON.getString("jkb_code_location"));

        jsonObject.put("颜色原因", toJSON.getString("b_color_why"));
        toJSON.put("remark", jsonObject.toJSONString());

        //标准化地址是否解析状态 1未解析3解析完成
        toJSON.put("flow_map_status", 1);

        IndexRequest request = new IndexRequest("flow_test5");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(toJSON);
        //Thread.sleep(100*100);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
