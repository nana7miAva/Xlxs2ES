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

public class zfbTrackListener extends AnalysisEventListener<zfbTrack> {


    @SneakyThrows
    @Override
    public void invoke(zfbTrack zfbTrack, AnalysisContext analysisContext) {

        CreatEs creatEs = new CreatEs();
        JSONObject toJSON = (JSONObject) JSONObject.toJSON(zfbTrack);

        //增加两个key
        toJSON.put("caseID","zfb从mysql读任务id"); //从mysql库查询添加
        toJSON.put("ownerID","zfb从mysql读任务id");

        toJSON.put("startTime",toJSON.getString("zfb_pay_date"));
        //toJSON.put("addressSource","");
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
