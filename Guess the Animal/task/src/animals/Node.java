package animals;

public class Node {
    String value;
    Node left;
    Node right;

    Node(String value) {
        this.value = value;
    }

    Node(String value, Node left, Node right) {
        this(value);
        this.left = left;
        this.right = right;
    }
}
