package xu.all.dataStructure.linkedList.singlyLinkedList;

import java.util.Objects;

public class Builder {

    private Node node;

    public Builder() {
    }

    public static Builder create() {
        return new Builder();
    }

    public Node add(Node nnode) {
        if (Objects.isNull(node)) {
            node = nnode;
        } else {
            node.setNext(nnode);
            node = node.getNext();
        }
        return node;
    }
}
