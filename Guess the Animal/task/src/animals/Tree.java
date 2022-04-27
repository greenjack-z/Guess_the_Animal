package animals;

public class Tree {
    Node root;

    Tree() {
        root = null;
    }

    public void setRoot(Node node) {
        root = node;
    }

    public void showTree() {
        show(root);
    }

    public void show(Node node) {
        if (node != null) {
            System.out.println(" " + node.value);
            show(node.left);
            show(node.right);
        }
    }
}
