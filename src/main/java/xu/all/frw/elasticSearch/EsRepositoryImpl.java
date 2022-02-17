package xu.all.frw.elasticSearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @Description: es持久层实现类
 * xu.all.config.EsClientConfiguration es配置类
 * xu.all.controller.EsController es Controller类
 * @Author: xuyujun
 * @Date: 2022/2/15
 */
@Slf4j
@Repository
public class EsRepositoryImpl implements EsRepository {

    @Autowired
    public RestHighLevelClient client;

    /**
     * @param indexName 索引名
     * @param source    配置+映射+其他，json格式
     *  例：{"settings":{"number_of_shards":1,"number_of_replicas":0},"mappings":{"properties":{"message":{"type":"text"}}},"aliases":{"twitter_alias":{}}}
     * @Description: 创建index
     */
    @Override
    public void createIndex(String indexName, String source) {
        boolean exists = indexExists(indexName);
        if (exists) {
            log.warn("the index already exists");
            return;
        }
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.source(source, XContentType.JSON);
        /*****************************其他方法1****************************************/
//        //创建的每个索引都可以有与之关联的特定设置。
//        request.settings(Settings.builder()
//                .put("index.number_of_shards", 3)
//                .put("index.number_of_replicas", 2)
//        );
//        //创建索引时创建文档类型映射
//        request.mapping(
//                "{\n" +
//                        "  \"properties\": {\n" +
//                        "    \"message\": {\n" +
//                        "      \"type\": \"text\"\n" +
//                        "    }\n" +
//                        "  }\n" +
//                        "}",
//                XContentType.JSON);
//        //设置索引别名
//        request.alias(new Alias("twitter_alias"));
        /*****************************其他方法2****************************************/
//        Map<String, Object> message = new HashMap<>();
//        message.put("type", "text");
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("message", message);
//        Map<String, Object> mapping = new HashMap<>();
//        mapping.put("properties", properties);
//        request.mapping(mapping);
        /*****************************其他方法3****************************************/
//        try {
//            XContentBuilder builder = XContentFactory.jsonBuilder();
//            builder.startObject();
//            {
//                builder.startObject("properties");
//                {
//                    builder.startObject("message");
//                    {
//                        builder.field("type", "text");
//                    }
//                    builder.endObject();
//                }
//                builder.endObject();
//            }
//            builder.endObject();
//            request.mapping(builder);
//        } catch (IOException e) {
//            log.error("系统异常", e);
//        }
        try {
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }

    /**
     * @Description: 删除索引
     */
    @Override
    public void deleteIndex(String indexName) {
        try {
            boolean exists = client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
            if (exists) {
                client.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
            }
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }

    /**
     * @Description: 判断索引是否存在
     */
    @Override
    public boolean indexExists(String indexName) {
        boolean exists = false;
        try {
            exists = client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("系统异常", e);
        }
        return exists;
    }

    /**
     * @Description: 获取所有索引名
     */
    @Override
    public Set<String> getAllIndex() {
        Set<String> indexes = null;
        try {
            indexes = client.indices().getAlias(new GetAliasesRequest(), RequestOptions.DEFAULT).getAliases().keySet();
        } catch (IOException e) {
            log.error("系统异常", e);
        }
        return indexes;
    }

    /**
     * @Description: 获取指定索引mapping
     */
    @Override
    public Map.Entry<String, MappingMetaData> getIndexMapping(String indexName) {
        Map.Entry<String, MappingMetaData> mapping = null;
        try {
            Set<Map.Entry<String, MappingMetaData>> set = client.indices().getMapping(new GetMappingsRequest().indices(indexName), RequestOptions.DEFAULT).mappings().entrySet();
            if (!CollectionUtils.isEmpty(set)) {
                mapping = set.iterator().next();
            }
        } catch (IOException e) {
            log.error("系统异常", e);
        }
        return mapping;
    }

    /**
     * @param indexName 索引名
     * @param source
     *  例：{"properties":{"age":{"type":"integer"}}}
     * @Description: 设置指定索引mapping（es只需添加，不可修改）
     */
    @Override
    public void putMapping(String indexName, String source) {
        boolean exists = indexExists(indexName);
        if (!exists) {
            log.warn("the index does not exists");
            return;
        }
        PutMappingRequest request = new PutMappingRequest(indexName);
        request.source(source, XContentType.JSON);
        try {
            client.indices().putMapping(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }

    /**
     * @param indexName 索引名
     * @param key 配置名
     *  number_of_shards 分片数量，必须在索引创建时指定，创建后无法修改
     * @param value 配置值
     * @Description: 设置指定索引settings
     */
    @Override
    public void putSettings(String indexName, String key, String value) {
        boolean exists = indexExists(indexName);
        if (!exists) {
            log.warn("the index does not exists");
            return;
        }
        UpdateSettingsRequest request = new UpdateSettingsRequest(indexName);
        Settings settings = Settings.builder()
                .put(key, value)
                .build();
        request.settings(settings);
        try {
            client.indices().putSettings(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }
}
