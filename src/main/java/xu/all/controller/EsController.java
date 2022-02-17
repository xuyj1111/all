package xu.all.controller;


import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xu.all.frw.elasticSearch.EsRepository;

import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private EsRepository esRepository;

    @PostMapping("/index")
    public void createIndex(@RequestParam("indexName") String indexName,
                            @RequestBody String source) {
        log.info(">>> request create index in es, indexName[{}], source[{}]", indexName, source);
        esRepository.createIndex(indexName, source);
    }

    @DeleteMapping("/index")
    public void deleteIndex(@RequestParam("indexName") String indexName) {
        log.info(">>> request delete index in es, indexName[{}]", indexName);
        esRepository.deleteIndex(indexName);
    }

    @GetMapping("/index/exists")
    public boolean indexExists(@RequestParam("indexName") String indexName) {
        log.info(">>> request index exists in es, indexName[{}]", indexName);
        return esRepository.indexExists(indexName);
    }

    @GetMapping("/indexes")
    public Set<String> getAllIndex() {
        log.info(">>> request get all index name in es");
        return esRepository.getAllIndex();
    }

    @GetMapping("/index/mapping")
    public Map.Entry<String, MappingMetaData> getIndexMapping(@RequestParam("indexName") String indexName) {
        log.info(">>> request get index mapping in es, indexName[{}]", indexName);
        return esRepository.getIndexMapping(indexName);
    }

    @PutMapping("/index/mapping")
    public void putIndexMapping(@RequestParam("indexName") String indexName,
                                @RequestBody String source) {
        log.info(">>> request put index mapping in es, indexName[{}], source[{}]", indexName, source);
        esRepository.putMapping(indexName, source);
    }

    @PutMapping("/index/settings")
    public void putIndexSettings(@RequestParam("indexName") String indexName,
                                 @RequestParam("key") String key,
                                 @RequestParam("value") String value) {
        log.info(">>> request put index settings in es, indexName[{}], key[{}], value[{}]", indexName, key, value);
        esRepository.putSettings(indexName, key, value);
    }
}
