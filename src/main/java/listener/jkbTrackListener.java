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
import Utils.CreatEs;
import toes.jkbTrack;

public class jkbTrackListener extends AnalysisEventListener<jkbTrack> {

    private String caseID;
    private RestHighLevelClient restHighLevelClient;

    public jkbTrackListener(String caseID, RestHighLevelClient restHighLevelClient) {
        this.caseID = caseID;
        this.restHighLevelClient = restHighLevelClient;
    }

    @SneakyThrows
    @Override
    public void invoke(jkbTrack jkbTrack, AnalysisContext analysisContext) {


        JSONObject toJSON = (JSONObject) JSONObject.toJSON(jkbTrack);

        //增加两个key
        toJSON.put("caseID", caseID); //从mysql库查询添加
        toJSON.put("ownerID", caseID);
        toJSON.put("startTime", toJSON.getString("jkb_scan_date"));
        toJSON.put("addressSource", "阳性人员健康宝扫描");
        toJSON.put("addressFromTable", toJSON.getString("jkb_code_location"));

           //标准化地址是否解析状态 1未解析3解析完成
        toJSON.put("flow_map_status", 1);

        IndexRequest request = new IndexRequest("flow_test4");

        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON, XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);


        System.out.println(toJSON);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
