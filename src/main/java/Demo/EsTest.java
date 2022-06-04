package Demo;

import Utils.CreatEs;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.util.CollectionUtils;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import toes.SearchDsl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EsTest {
    public static void main(String[] args) throws IOException {
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("172.16.10.49", 9200, "http"));
        //new HttpHost("192.168.4.15", 9200, "http"));
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);


        //selectData(restHighLevelClient);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("flow_investigation");

        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.termQuery("caseID","281ecda3c4784375a63502e83a61b680"));
        //SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        searchRequest.source(query);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();


        System.out.println(hits.getTotalHits());
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        restHighLevelClient.close();
    }



    public static void selectData(RestHighLevelClient client) throws IOException {
        int i=1,size=2;
        SearchRequest searchRequest = new SearchRequest("flow_investigation");
        Scroll scroll = new Scroll(TimeValue.timeValueMinutes(5L));
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchSourceBuilder.query(matchAllQueryBuilder);
        searchSourceBuilder.size(size);

        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        SearchHit[] searchHits = response.getHits().getHits();

        System.out.println(response.getHits().getTotalHits());
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getSourceAsString());
        }
        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            response = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = response.getScrollId();
            searchHits = response.getHits().getHits();

            for (SearchHit searchHit : searchHits) {
                i++;
                System.out.println(searchHit.getSourceAsString());
            }
            if (i > 10) {
                break;
            }
        }
    }

    public static boolean clearScrollIds(RestHighLevelClient client,String... scrollIds){
        List<String> sIds = new ArrayList<>();
        for (String scrollId : scrollIds) {
            sIds.add(scrollId);
        }
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        //添加单个id
        clearScrollRequest.addScrollId("滚动id");
        //添加多个id
        clearScrollRequest.setScrollIds(sIds);
        try {
            client.clearScroll(clearScrollRequest,RequestOptions.DEFAULT);
            return true;
        } catch (IOException e) {
            return false;
        }

    }


}
