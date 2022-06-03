package Demo;

import Utils.CreatEs;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.common.util.CollectionUtils;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import toes.SearchDsl;

import java.io.IOException;
import java.util.Map;

public class EsTest {
    public static void main(String[] args) throws IOException {
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("172.16.10.49", 9200, "http"));
        //new HttpHost("192.168.4.15", 9200, "http"));
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);

        RestClient build = restClientBuilder.build();

        RestClient lowLevelClient = restHighLevelClient.getLowLevelClient();
        String method = "POST";
        String endpoint = "flow_investigation/_search";
        String json = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"caseID\": {\n" +
                "        \"query\":\"281ecda3c4784375a63502e83a61b680\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        NStringEntity nStringEntity = new NStringEntity(json);
        Request request = new Request("POST", "flow_investigation/_doc/_search");

        request.setEntity(nStringEntity);
        Response response = build.performRequest(request);
        //Response response = lowLevelClient.performRequest(request);
        HttpEntity entity = response.getEntity();

        String resultS = EntityUtils.toString(entity);
        System.out.println(JSON.parseObject(resultS));


    }


   /* public static void queryByField(RestClient client) {
        try{
            String method = "POST";
            String endpoint = "/book/it/_search";
            HttpEntityentity = new NStringEntity("{\n" +
                    "  \"query\":{\n" +
                    "    \"match\":{\n" +
                    "      \"name\":\"三\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}", ContentType.APPLICATION_JSON);
            Requestrequest = new Request(method, endpoint);
            request.setEntity(entity);
            Responseresponse = client.performRequest(request);
            System.out.println(EntityUtils.toString(response.getEntity()));
            // 返回结果
            // {"took":3,"timed_out":false,"_shards":{"total":5,"successful":5,"skipped":0,"failed":0},"hits":{"total":1,"max_score":0.5753642,"hits":[{"_index":"book","_type":"novel","_id":"1","_score":0.5753642,"_source":{"count":10,"name":"三国演义","publishDate":1555825698934,"writer":"张飞"}}]}}
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                client.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/


}
