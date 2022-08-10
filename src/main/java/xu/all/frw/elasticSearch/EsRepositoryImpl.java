package xu.all.frw.elasticSearch;

import cn.hutool.core.io.IoUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch.indices.*;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xu.tools.json.JsonMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @Description: es持久层实现类
 * xu.all.config.EsClientConfig es配置类
 * xu.all.controller.EsController es Controller类
 * @Author: xuyujun
 * @Date: 2022/2/15
 */
@Slf4j
@Repository
public class EsRepositoryImpl implements EsRepository {

    @Autowired
    public ElasticsearchClient client;

    /**
     * @param indexName 索引名
     * @param source    配置+映射+其他，json格式
     *                  例：{"settings":{"number_of_shards":1,"number_of_replicas":0},"mappings":{"properties":{"message":{"type":"text"}}},"aliases":{"twitter_alias":{}}}
     * @Description: 创建index
     */
    @Override
    public void createIndex(String indexName, String source) {
        boolean exists = indexExists(indexName);
        if (exists) {
            log.warn("the index already exists");
            return;
        }
        CreateIndexRequest request = CreateIndexRequest.of(c -> c
                .index(indexName)
                .withJson(IoUtil.toUtf8Stream(source)));
        try {
            client.indices().create(request);
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
            boolean exists = client.indices().exists(ExistsRequest.of(e -> e.index(indexName))).value();
            if (exists) {
                DeleteIndexRequest request = DeleteIndexRequest.of(e -> e
                        .index(indexName));
                client.indices().delete(request);
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
            ExistsRequest request = ExistsRequest.of(e -> e
                    .index(indexName));
            exists = client.indices().exists(request).value();
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
            indexes = client.indices().getAlias(a -> a).result().keySet();
        } catch (IOException e) {
            log.error("系统异常", e);
        }
        return indexes;
    }

    /**
     * @Description: 获取指定索引mapping
     */
    @Override
    public Map<String, String> getIndexMapping(String indexName) {
        Map<String, String> mapping = Maps.newHashMap();
        try {
            GetMappingRequest request = GetMappingRequest.of(g -> g.index(indexName));
            Map<String, Property> properties = client.indices().getMapping(request).result().get(indexName).mappings().properties();
            properties.forEach((key, property) -> {
                mapping.put(key, property._kind().jsonValue());
            });
        } catch (IOException e) {
            log.error("系统异常", e);
        }
        return mapping;
    }

    /**
     * @param indexName 索引名
     * @param source    例：{"properties":{"age":{"type":"integer"}}}
     * @Description: 设置指定索引mapping（es只需添加，不可修改）
     */
    @Override
    public void putMapping(String indexName, String source) {
        boolean exists = indexExists(indexName);
        if (!exists) {
            log.warn("the index does not exists");
            return;
        }
        PutMappingRequest request = PutMappingRequest.of(p -> p
                .index(indexName)
                .withJson(IoUtil.toUtf8Stream(source)));
        try {
            client.indices().putMapping(request);
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }


    /**
     * @Description: 获取指定索引mapping
     */
    @Override
    public Map<String, Object> getIndexSettings(String indexName) {
        IndexSettings settings = null;
        try {
            GetIndicesSettingsRequest request = GetIndicesSettingsRequest.of(s -> s
                    .index(indexName));
            settings = client.indices().getSettings(request).result().get(indexName).settings().index();
        } catch (IOException e) {
            log.error("系统异常", e);
        }
        return JsonMapper.parseMap(settings.toString(), String.class, Object.class);
    }

    /**
     * @param indexName 索引名
     * @param interval  配置值
     * @Description: 设置 refreshInterval
     */
    @Override
    public void setRefreshInterval(String indexName, String interval) {
        boolean exists = indexExists(indexName);
        if (!exists) {
            log.warn("the index does not exists");
            return;
        }
        PutIndicesSettingsRequest request = PutIndicesSettingsRequest.of(s -> s
                .index(indexName)
                .settings(IndexSettings.of(i -> i.refreshInterval(r -> r.time(interval))))
        );
        try {
            client.indices().putSettings(request);
        } catch (IOException e) {
            log.error("系统异常", e);
        }
    }
}
