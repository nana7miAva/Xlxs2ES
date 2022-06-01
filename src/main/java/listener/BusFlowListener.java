package listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import toes.BusFlow;
import Utils.CreatEs;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BusFlowListener extends AnalysisEventListener<BusFlow> {

    private String caseID;
    private RestHighLevelClient restHighLevelClient;

    public BusFlowListener(String caseID, RestHighLevelClient restHighLevelClient) {
        this.caseID = caseID;
        this.restHighLevelClient = restHighLevelClient;
    }

    @SneakyThrows
    @Override
    public void invoke(BusFlow busFlow, AnalysisContext analysisContext) {


        JSONObject toJSON1 = (JSONObject) JSONObject.toJSON(busFlow);
        JSONObject toJSON2 = (JSONObject) JSONObject.toJSON(busFlow);
        JSONObject toJSON3 = (JSONObject) JSONObject.toJSON(busFlow);

        //先转换日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parse = simpleDateFormat.parse(toJSON1.getString("bus_trad_date"));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat1.format(parse);
        toJSON1.put("bus_trad_date", format);
        toJSON2.put("bus_trad_date", format);
        toJSON3.put("bus_trad_date", format);

        Date parse1 = simpleDateFormat.parse(toJSON1.getString("bus_tag_date"));
        String format1 = simpleDateFormat1.format(parse1);

        toJSON1.put("bus_tag_date", format1);
        toJSON2.put("bus_tag_date", format1);
        toJSON3.put("bus_tag_date", format1);


        //如果标记站和交易站相等 就：
        //标记站，+其余信息
        //车辆号+其余信息
        if (toJSON1.getString("bus_pay_platform_id").equals(toJSON1.getString("bus_tag_platform_id"))) {

            //增加两个key
            toJSON1.put("caseID", caseID); //从mysql库查询添加
            //toJSON1.put("ownerID","");
            toJSON1.put("startTime", toJSON1.getString("bus_trad_date"));
            toJSON1.put("addressSource", "公交");
            toJSON2.put("caseID", caseID);
            //toJSON2.put("ownerID","");
            toJSON2.put("startTime", toJSON2.getString("bus_trad_date"));
            toJSON2.put("addressSource", "公交");

            //toJSON3.put("caseID", "bus从mysql添加");
            //toJSON3.put("ownerID","");
            //toJSON3.put("startTime", toJSON3.getString("bus_trad_date"));
            //toJSON3.put("addressSource", "公交");


            toJSON1.put("addressFromTable", toJSON1.getString("bus_number"));
            toJSON2.put("addressFromTable", toJSON2.getString("bus_tag_platform_name"));
            //toJSON3.put("addressFromTable", toJSON3.getString("bus_pay_platform_name"));

            //标准化地址是否解析状态 1未解析3解析完成
            toJSON1.put("flow_map_status",1);
            //标准化地址是否解析状态 1未解析3解析完成
            toJSON2.put("flow_map_status",1);

            IndexRequest request1 = new IndexRequest("flow_test2");
            IndexRequest request2 = new IndexRequest("flow_test2");


            request1.timeout(TimeValue.timeValueSeconds(1));
            request1.timeout("1s");
            request1.source(toJSON1, XContentType.JSON);


            request2.timeout(TimeValue.timeValueSeconds(1));
            request2.timeout("1s");

            request2.source(toJSON2, XContentType.JSON);

            //request.source(toJSON3, XContentType.JSON);

            /*BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(request1);
            bulkRequest.add(request2);*/


            IndexResponse indexResponse1 = restHighLevelClient.index(request1, RequestOptions.DEFAULT);
            IndexResponse indexResponse2 = restHighLevelClient.index(request2, RequestOptions.DEFAULT);


            //System.out.println("111111111111111111"+toJSON1);
            //System.out.println("222222222222222222"+toJSON2);
            //System.out.println("333333333333333333"+toJSON3);


        } else {


            //如果不相等 就 标记站，+其余信息
            //交易站 +其余信息
            //车辆号+其余信息

            //增加两个key
            toJSON1.put("caseID", caseID); //从mysql库查询添加
            //toJSON1.put("ownerID","");
            toJSON1.put("startTime", toJSON1.getString("bus_trad_date"));
            toJSON1.put("addressSource", "公交");
            //
            toJSON2.put("caseID", caseID);
            //toJSON2.put("ownerID","");
            toJSON2.put("startTime", toJSON2.getString("bus_trad_date"));
            toJSON2.put("addressSource", "公交");

            toJSON3.put("caseID", caseID);
            //toJSON3.put("ownerID","");
            toJSON3.put("startTime", toJSON3.getString("bus_trad_date"));
            toJSON3.put("addressSource", "公交");


            toJSON1.put("addressFromTable", toJSON1.getString("bus_number"));
            toJSON2.put("addressFromTable", toJSON2.getString("bus_tag_platform_name"));
            toJSON3.put("addressFromTable", toJSON3.getString("bus_pay_platform_name"));

            //标准化地址是否解析状态 1未解析3解析完成
            toJSON1.put("flow_map_status",1);
            //标准化地址是否解析状态 1未解析3解析完成
            toJSON2.put("flow_map_status",1);
            //标准化地址是否解析状态 1未解析3解析完成
            toJSON3.put("flow_map_status",1);

            IndexRequest request1 = new IndexRequest("flow_investigation");
            IndexRequest request2 = new IndexRequest("flow_investigation");
            IndexRequest request3 = new IndexRequest("flow_investigation");

            request1.timeout(TimeValue.timeValueSeconds(1));
            request1.timeout("1s");
            request1.source(toJSON1, XContentType.JSON);


            request2.timeout(TimeValue.timeValueSeconds(1));
            request2.timeout("1s");

            request2.source(toJSON2, XContentType.JSON);


            request3.timeout(TimeValue.timeValueSeconds(1));
            request3.timeout("1s");
            request3.source(toJSON3, XContentType.JSON);



            IndexResponse indexResponse1 = restHighLevelClient.index(request1, RequestOptions.DEFAULT);
            IndexResponse indexResponse2 = restHighLevelClient.index(request2, RequestOptions.DEFAULT);
            IndexResponse indexResponse3 = restHighLevelClient.index(request3, RequestOptions.DEFAULT);


            //System.out.println("样例数据"+toJSON1);
            //System.out.println("2"+toJSON2);
            //System.out.println("3"+toJSON3);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
