package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Node {
    private final String text;
    private Node nodeYes;
    private Node nodeNo;

    public Node() {
        text = null;
        nodeYes = null;
        nodeNo = null;
    }

    public Node(String text) {
        this.text = text;
        nodeYes = null;
        nodeNo = null;
    }

    public Node(String text, Node leftNode, Node rightNode) {
        this.text = text;
        this.nodeYes = leftNode;
        this.nodeNo = rightNode;
    }

    public Node getNodeYes() {
        return nodeYes;
    }

    public void setNodeYes(Node nodeYes) {
        this.nodeYes = nodeYes;
    }

    public Node getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Node nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getText() {
        return text;
    }
    @JsonIgnore
    public Boolean isAnimal() {
        return nodeYes == null && nodeNo == null;
    }

}