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
import toes.gTrack;
import toes.jzTrack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class gTrackListener extends AnalysisEventListener<gTrack> {


    private String caseID;
    private RestHighLevelClient restHighLevelClient;

    public gTrackListener(String caseID, RestHighLevelClient restHighLevelClient) {
        this.caseID = caseID;
        this.restHighLevelClient = restHighLevelClient;
    }

    //一行行处理数据
    @SneakyThrows
    @Override
    public void invoke(gTrack gTrack, AnalysisContext analysisContext) {


        JSONObject toJSON = (JSONObject) JSONObject.toJSON(gTrack);

        //System.out.println(toJSON);

        IndexRequest request = new IndexRequest("flow_test5");

        //转换日期格式

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String begin_date = toJSON.getString("begin_date") + ":00";
        String end_date = toJSON.getString("end_date") + ":00";
        Date parse_begin = simpleDateFormat.parse(begin_date);
        Date parse__end = simpleDateFormat.parse(end_date);

        String startTime = simpleDateFormat.format(parse_begin);
        String endTime = simpleDateFormat.format(parse__end);


        toJSON.put("caseID", caseID); //从mysql库查询添加
        toJSON.put("ownerID", caseID);
        toJSON.put("addressSource", "G表");
        toJSON.put("addressFromTable", toJSON.getString("begin_location"));

        toJSON.put("startTime", startTime);
        toJSON.put("endTime", endTime);

        toJSON.remove("begin_date");
        toJSON.remove("end_date");

        toJSON.put("flow_map_location_x", toJSON.getString("begin_location_x"));
        toJSON.put("flow_map_location_y", toJSON.getString("begin_location_y"));
        toJSON.remove("begin_location_x");
        toJSON.remove("begin_location_y");

        toJSON.put("jz_begin_date", startTime);
        toJSON.put("jz_end_date", endTime);
        toJSON.put("jz_location", toJSON.getString("begin_location"));

        //标准化地址是否解析状态 1未解析3解析完成
        toJSON.put("flow_map_status", 3);
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
