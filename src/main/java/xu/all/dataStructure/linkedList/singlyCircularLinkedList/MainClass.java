package xu.all.dataStructure.linkedList.singlyCircularLinkedList;

public class MainClass {
    public static void main(String[] args) {

        Builder builder = Builder.create();
        Node head = new Node(1);

        builder.add(head);
        builder.add(new Node(3));
        builder.add(new Node(5));
        builder.add(new Node(7));

        foreachSingleLinkedList(head);
    }

    private static void foreachSingleLinkedList(Node head) {
        Node node = head;
        while (true) {
            System.out.println(node.getValue());
            if (node.getNext().equals(head)) {
                break;
            }
            node = node.getNext();
        }
    }
}
