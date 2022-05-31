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
import toes.zfbTrack;

public class zfbTrackListener extends AnalysisEventListener<zfbTrack> {

    private String caseID;
    private RestHighLevelClient restHighLevelClient;

    public zfbTrackListener(String caseID, RestHighLevelClient restHighLevelClient) {
        this.caseID = caseID;
        this.restHighLevelClient = restHighLevelClient;
    }


    @SneakyThrows
    @Override
    public void invoke(zfbTrack zfbTrack, AnalysisContext analysisContext) {


        JSONObject toJSON = (JSONObject) JSONObject.toJSON(zfbTrack);

        //增加两个key
        toJSON.put("caseID", "zfb从mysql读任务id"); //从mysql库查询添加
        toJSON.put("ownerID", "zfb从mysql读任务id");

        toJSON.put("startTime", toJSON.getString("zfb_pay_date"));
        toJSON.put("addressSource", "支付宝");
        //toJSON.put("addressFromTable","");


        IndexRequest request = new IndexRequest("flow_test2");

        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON, XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
//System.out.println(toJSON);