package toes;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.apache.poi.hssf.record.DVALRecord;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Map;

public class jzTrackListener extends AnalysisEventListener<jzTrack> {


    //一行行处理数据
    @SneakyThrows
    @Override
    public void invoke(jzTrack jzTrack, AnalysisContext analysisContext) {

        CreatEs creatEs = new CreatEs();
        JSONObject toJSON = (JSONObject) JSONObject.toJSON(jzTrack);

        RestHighLevelClient esClient = creatEs.createEsClient();
        IndexRequest request = new IndexRequest("flow_test");

        toJSON.put("caseID","jz从mysql读任务id"); //从mysql库查询添加
        toJSON.put("ownerID","jz从mysql读任务id");
        toJSON.put("addressSource","基站");
        toJSON.put("addressFromTable",toJSON.getString("jz_location"));

        toJSON.put("jz_begin_date", toJSON.getString("begin_date"));
        toJSON.put("jz_end_date", toJSON.getString("end_date"));
        toJSON.put("jz_location", toJSON.getString("jz_location"));

        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON, XContentType.JSON);

        IndexResponse indexResponse = esClient.index(request, RequestOptions.DEFAULT);

        esClient.close();
        //System.out.println(toJSON);
    }

    //读取表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //System.out.println("表头" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {


    }


}
