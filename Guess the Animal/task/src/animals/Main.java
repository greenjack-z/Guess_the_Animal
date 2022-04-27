package animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().run();

        Tree tree = new Tree();
        Node catNode = new Node("cat");
        tree.setRoot(catNode);

        Node dogNode = new Node("dog");
        Node newRoot = new Node("it can climb trees", tree.root, dogNode);
        tree.setRoot(newRoot);

        Node wolfNode = new Node("wolf");
        newRoot = new Node("it is living in forest", wolfNode, tree.root);
        tree.setRoot(newRoot);

        tree.showTree();
    }

    private void run() {
        try(Scanner scanner = new Scanner(System.in)) {

            String input = scanner.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
