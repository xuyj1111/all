package xu.all.frw.orika.pojo;

import java.util.HashMap;
import java.util.Map;

public class Element {

    private Map<String, Object> attributes = new HashMap<>();

    public Object getAttributes(String name) {
        return attributes.get(name);
    }

    public Element setAttributes(String name, Object value) {
        attributes.put(name, value);
        return this;
    }
}
