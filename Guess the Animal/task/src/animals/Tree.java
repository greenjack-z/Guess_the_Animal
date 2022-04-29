package animals;

public class Tree {
    private Node root;
    private Node current;
    private Node parent;

    public Tree() {
        root = null;
        parent = null;
        current = null;
    }

    public Node getRoot() {
        return root;
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addNode(Node node) {
        if (current == null) {
            current = node;
        }
        if (parent == null) {
            root = node;
        } else {
            if (current == parent.getNodeYes()) {
                parent.setNodeYes(node);
            } else {
                parent.setNodeNo(node);
            }
        }
    }
}