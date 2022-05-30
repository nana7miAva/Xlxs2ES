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

import java.text.SimpleDateFormat;
import java.util.Date;

public class BusFlowListener extends AnalysisEventListener<BusFlow> {


    @SneakyThrows
    @Override
    public void invoke(BusFlow busFlow, AnalysisContext analysisContext) {

        CreatEs creatEs = new CreatEs();
        JSONObject toJSON1 = (JSONObject) JSONObject.toJSON(busFlow);
        JSONObject toJSON2 = (JSONObject) JSONObject.toJSON(busFlow);
        JSONObject toJSON3 = (JSONObject) JSONObject.toJSON(busFlow);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = simpleDateFormat.parse(toJSON1.getString("bus_trad_date"));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat1.format(parse);
        toJSON1.put("bus_trad_date",format);
        toJSON2.put("bus_trad_date",format);
        toJSON3.put("bus_trad_date",format);

        Date parse1 = simpleDateFormat.parse(toJSON1.getString("bus_tag_date"));
        String format1 = simpleDateFormat1.format(parse1);

        toJSON1.put("bus_tag_date",format1);
        toJSON2.put("bus_tag_date",format1);
        toJSON3.put("bus_tag_date",format1);


        //增加两个key
        toJSON1.put("caseID","bus从mysql添加"); //从mysql库查询添加
        //toJSON1.put("ownerID","");
        toJSON1.put("startTime",toJSON1.getString("bus_trad_date"));
        toJSON1.put("addressSource","公交");
        //
        toJSON2.put("caseID","bus从mysql添加");
        //toJSON2.put("ownerID","");
        toJSON2.put("startTime",toJSON2.getString("bus_trad_date"));
        toJSON2.put("addressSource","公交");

        toJSON3.put("caseID","bus从mysql添加");
        //toJSON3.put("ownerID","");
        toJSON3.put("startTime",toJSON3.getString("bus_trad_date"));
        toJSON3.put("addressSource","公交");



        toJSON1.put("addressFromTable",toJSON1.getString("bus_number"));
        toJSON2.put("addressFromTable",toJSON2.getString("bus_tag_platform_name"));
        toJSON3.put("addressFromTable",toJSON3.getString("bus_pay_platform_name"));




        RestHighLevelClient esClient = creatEs.createEsClient();
        IndexRequest request = new IndexRequest("flow_test");

        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(toJSON1, XContentType.JSON);
        request.source(toJSON2, XContentType.JSON);
        request.source(toJSON3, XContentType.JSON);

        IndexResponse indexResponse = esClient.index(request, RequestOptions.DEFAULT);


        //System.out.println(toJSON);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
