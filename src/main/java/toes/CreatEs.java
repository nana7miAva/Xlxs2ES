package toes;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;


import java.io.IOException;

public class CreatEs {


    public RestHighLevelClient createEsClient() throws IOException {

        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("172.16.10.49", 9200, "http"));
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        GetIndexRequest getIndexRequest = new GetIndexRequest("flow_test2");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);


        return restHighLevelClient;
    }


}
