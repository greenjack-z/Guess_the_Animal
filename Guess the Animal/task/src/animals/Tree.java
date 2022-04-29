package animals;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

public class Tree {
    private Node root;
    private Node current;
    private Node parent;
    private ObjectMapper mapper;
    private String fileName;

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

    public void setMapper(String fileType) {
        if(fileType != null) {
            switch (fileType) {
                case "yaml":
                    fileName = "animals.yaml";
                    mapper = new YAMLMapper();
                    break;
                case "xml":
                    fileName = "animals.xml";
                    mapper = new XmlMapper();
                    break;
                case "json":
                default:
                    fileName = "animals.json";
                    mapper = new JsonMapper();
            }
        }
    }

    public Boolean loadTree() {
        try {
            root = mapper.readValue(new File(fileName), Node.class);
            return true;
        } catch (JsonMappingException | JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public void saveTree() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}