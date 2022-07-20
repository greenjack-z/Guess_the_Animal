package animals;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
        String lang;
        if (Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
            lang = "";
        } else {
            lang = "_" + Locale.getDefault().getLanguage();
        }
        if(fileType != null) {
            switch (fileType) {
                case "yaml":
                    fileName = String.format("animals%s.yaml", lang);
                    mapper = new YAMLMapper();
                    break;
                case "xml":
                    fileName = String.format("animals%s.xml", lang);
                    mapper = new XmlMapper();
                    break;
                case "json":
                default:
                    fileName = String.format("animals%s.json", lang);
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

    public void listAnimals() {
        List<String> list = collectAnimalsRecursive(new ArrayList<>(), root);
        list.sort(Comparator.naturalOrder());
        for (String s : list) {
            System.out.println("- " + s);
        }
    }

    public void searchAnimal(String animal) {
        Deque<String> deque = new ArrayDeque<>();
        if (Boolean.TRUE.equals(getPathRecursive(deque, root, animal))) {
            for (String s : deque) {
                System.out.println("- " + s);
            }
        } else {
            System.out.printf((Main.res.getString("There are no facts about")), animal);
        }
    }

    public void printStats() {
        System.out.printf("- %s                   %s%n", Main.res.getString("root node"), root.getText());
        List<Node> nodes = getAllNodesRecursive(new ArrayList<>(), root);
        System.out.printf("- %s       %d%n", Main.res.getString("total number of nodes"), nodes.size());
        System.out.printf("- %s     %d%n", Main.res.getString("total number of animals"), countAnimals(nodes));
        System.out.printf("- %s  %d%n", Main.res.getString("total number of statements"), (nodes.size() - countAnimals(nodes)));
        int height = getHeightRecursive(root);
        System.out.printf("- %s          %d%n", Main.res.getString("height of the tree"), height);
        System.out.printf("- %s      %d%n", Main.res.getString("minimum animal's depth"), getMinDepth(getDepth(nodes)));
        System.out.printf("- %s      %.1f%n", Main.res.getString("average animal's depth"), getAverageDepth(getDepth(nodes)));

    }

    public void printTree() {
        List<String> list = new ArrayList<>();
        collectNodesRecursive(list, root, "");
        for (String s : list) {
            System.out.println(s);
        }
    }

    private List<String> collectAnimalsRecursive(List<String> list, Node node) {
        if (Boolean.TRUE.equals(node.isAnimal())) {
            list.add(node.getText().replaceFirst("(a|an)\\s", ""));
        } else {
            list = collectAnimalsRecursive(list, node.getNodeYes());
            list = collectAnimalsRecursive(list, node.getNodeNo());
        }
        return list;
    }

    private Boolean getPathRecursive(Deque<String> deque, Node node, String animal) {
        if (node == null) {
            return false;
        }
        if (node.getText().replaceFirst("(a|an)\\s", "").equals(animal)) {
            return true;
        }
        if (Boolean.TRUE.equals(getPathRecursive(deque, node.getNodeYes(), animal))) {
            deque.addFirst(node.getText() + ".");
            return true;
        } else if (Boolean.TRUE.equals(getPathRecursive(deque, node.getNodeNo(), animal))) {
            deque.addFirst(node.getText()
                    .replaceFirst(" is ", " isn't ")
                    .replaceFirst(" can ", " can't ")
                    .replaceFirst(" has ", " doesn't have ") + ".");
            return true;
        } else {
            deque.pollFirst();
            return false;
        }
    }

    private List<String> collectNodesRecursive(List<String> list, Node node, String tab) {
        if (node == null) {
            return list;
        }
        String text = node.getText()
                .replaceFirst("it is", "Is it")
                .replaceFirst("it can", "Can it")
                .replaceFirst("it has", "Does it have");
        String prefix;
        if (Boolean.TRUE.equals(node.isAnimal())) {
            prefix = "└";
        } else {
            prefix = "├┐";
            text = text + "?";
        }
        list.add(tab + prefix + text);

        list = collectNodesRecursive(list, node.getNodeNo(), tab + "│");
        list = collectNodesRecursive(list, node.getNodeYes(), tab);


        return list;
    }

    private List<Node> getAllNodesRecursive(List<Node> list, Node node) {
        if (node == null) {
            return list;
        }
        list.add(node);
        getAllNodesRecursive(list, node.getNodeYes());
        getAllNodesRecursive(list, node.getNodeNo());
        return list;
    }

    private int countAnimals(List<Node> nodes) {
        int animals = 0;
        for (Node n : nodes) {
            if (Boolean.TRUE.equals(n.isAnimal())) {
                animals++;
            }
        }
        return animals;
    }

    private int getHeightRecursive(Node node) {
        if(node == null) {
            return -1;
        }
        return Math.max(getHeightRecursive(node.getNodeYes()), getHeightRecursive(node.getNodeNo())) + 1;
    }

    private int getDepthRecursive(Node node, Node target) {
        if (node == null) {
            return -1;
        }
        int depth = -1;
        if  (node == target ||
                (depth = getDepthRecursive(node.getNodeYes(), target)) >= 0 ||
                (depth = getDepthRecursive(node.getNodeNo(), target)) >= 0) {
            return depth + 1;
        }
        return depth;
    }

    private List<Integer> getDepth(List<Node> nodes) {
        List<Integer> list = new ArrayList<>();
        for (Node node : nodes) {
            if (Boolean.TRUE.equals(node.isAnimal())) {
                list.add(getDepthRecursive(root, node));
            }
        }
        return list;
    }

    private int getMinDepth(List<Integer> list) {
        list.sort(Comparator.naturalOrder());
        return list.get(0);
    }

    private double getAverageDepth(List<Integer> list) {
        double sum = 0;
        for (int i : list) {
            sum = sum + i;
        }
        return sum / list.size();
    }
}