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
import toes.jzTrack;

import java.util.Map;

public class jzTrackListener extends AnalysisEventListener<jzTrack> {


    private String caseID;
    private RestHighLevelClient restHighLevelClient;

    public jzTrackListener(String caseID, RestHighLevelClient restHighLevelClient) {
        this.caseID = caseID;
        this.restHighLevelClient = restHighLevelClient;
    }

    //一行行处理数据
    @SneakyThrows
    @Override
    public void invoke(jzTrack jzTrack, AnalysisContext analysisContext) {


        JSONObject toJSON = (JSONObject) JSONObject.toJSON(jzTrack);


        IndexRequest request = new IndexRequest("flow_test4");


        toJSON.put("caseID", caseID); //从mysql库查询添加
        toJSON.put("ownerID", caseID);
        toJSON.put("addressSource", "基站");
        toJSON.put("addressFromTable", toJSON.getString("location"));
        toJSON.put("startTime", toJSON.getString("begin_date"));
        toJSON.put("endTime", toJSON.getString("end_date"));

        toJSON.put("jz_begin_date", toJSON.getString("begin_date"));
        toJSON.put("jz_end_date", toJSON.getString("end_date"));
        toJSON.put("jz_location", toJSON.getString("location"));
//标准化地址是否解析状态 1未解析3解析完成
        toJSON.put("flow_map_status",1);
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON, XContentType.JSON);

        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);

        System.out.println(toJSON);

    }

    //读取表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //System.out.println("表头" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {


    }


}
