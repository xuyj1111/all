package xu.all.dataStructure.linkedList.singlyLinkedList;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Node {
    private int value;
    private Node next;

    public Node(int value) {
        this.value = value;
    }
}