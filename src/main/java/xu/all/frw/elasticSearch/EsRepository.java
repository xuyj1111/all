package xu.all.frw.elasticSearch;

import java.util.Map;
import java.util.Set;

public interface EsRepository {

    void createIndex(String indexName, String source);

    void deleteIndex(String indexName);

    boolean indexExists(String indexName);

    Set<String> getAllIndex();

    Map<String, String> getIndexMapping(String indexName);

    void putMapping(String indexName, String source);

    Map<String, Object> getIndexSettings(String indexName);

    void setRefreshInterval(String indexName, String interval);
}
