package xu.all.frw.elasticSearch;

import org.elasticsearch.cluster.metadata.MappingMetaData;

import java.util.Map;
import java.util.Set;

public interface EsRepository {

    void createIndex(String indexName, String source);

    void deleteIndex(String indexName);

    boolean indexExists(String indexName);

    Set<String> getAllIndex();

    Map.Entry<String, MappingMetaData> getIndexMapping(String indexName);

    void putMapping(String indexName, String source);

    void putSettings(String indexName, String key, String value);
}
