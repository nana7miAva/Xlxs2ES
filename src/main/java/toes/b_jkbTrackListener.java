package toes;

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

public class b_jkbTrackListener extends AnalysisEventListener<b_jkbTrack> {


    @SneakyThrows
    @Override
    public void invoke(b_jkbTrack b_jkbTrack, AnalysisContext analysisContext) {

        CreatEs creatEs = new CreatEs();
        JSONObject toJSON = (JSONObject) JSONObject.toJSON(b_jkbTrack);

        //增加两个key
        toJSON.put("caseID","b_jkb从mysql读任务id"); //从mysql库查询添加
        //toJSON.put("ownerID","jkb从mysql读任务id");
        toJSON.put("startTime",toJSON.getString("jkb_scan_date"));
        toJSON.put("addressSource","点位健康宝扫描");
        //toJSON.put("addressFromTable","");

        RestHighLevelClient esClient = creatEs.createEsClient();
        IndexRequest request = new IndexRequest("flow_test");

        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON, XContentType.JSON);

        IndexResponse indexResponse = esClient.index(request, RequestOptions.DEFAULT);


        //System.out.println(toJSON);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
