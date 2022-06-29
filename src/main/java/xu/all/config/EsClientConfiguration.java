package xu.all.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xu.tools.json.JsonMapper;

@Configuration
public class EsClientConfiguration {

    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT = 9200;
    private static final int KEEP_ALIVE_MS = 60 * 60 * 1000;

    /**
     * @Description: 构建客户端对象
     */
    @Bean
    public ElasticsearchClient client() {
        HttpHost host = new HttpHost(HOST_NAME, PORT);
        RestClientBuilder builder = RestClient.builder(host).setHttpClientConfigCallback(requestConfig ->
                requestConfig.setKeepAliveStrategy((response, context) -> KEEP_ALIVE_MS)
                        .setDefaultIOReactorConfig(IOReactorConfig.custom()
                                .setSoKeepAlive(true)
                                .build()));
        return new ElasticsearchClient(new RestClientTransport(
                builder.build(), new JacksonJsonpMapper(JsonMapper.getMapper())));
    }

}
