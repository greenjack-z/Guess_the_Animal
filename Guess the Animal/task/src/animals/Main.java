package animals;

import java.util.*;

public class Main {
    private final String[] args;
    private final Vocabulary vocabulary = new Vocabulary();
    private final Tree tree;
    private Boolean endGame = false;


    Main (String[] args) {
        this.args = args;
        tree = new Tree();
    }

    public static void main(String[] args) {
        new Main(args).guessAnimal();
    }

    private void guessAnimal() {
        intro();
        tree.setCurrent(tree.getRoot());
        while(Boolean.FALSE.equals(endGame)) {
            say(makeQuestion(tree.getCurrent().getText()));
            if(readYes()) {
                if(tree.getCurrent().getNodeYes() == null) {
                    say("I win");
                    playAgain();
                } else {
                    tree.setParent(tree.getCurrent());
                    tree.setCurrent(tree.getCurrent().getNodeYes());
                }
            } else {
                if (tree.getCurrent().getNodeNo() != null) {
                    tree.setParent(tree.getCurrent());
                    tree.setCurrent(tree.getCurrent().getNodeNo());
                } else {
                    addAnimal();
                    playAgain();
                }
            }
        }
    }

    private void intro() {
        String fileType = null;
        if (args.length == 0) {
            fileType = "json";
        } else if (args[0].equals("-type")) {
            fileType = args[1];
        }

        tree.setMapper(fileType);
        if (Boolean.FALSE.equals(tree.loadTree())) {
            say(vocabulary.greeting());
            say("I want to learn about animals.");
            say("Which animal do you like most?");
            tree.addNode(new Node(parseAnimal(read())));
            say("I've learned so much about the animals!");
        } else {
            say("I know a lot about animals.");
        }
        say("Let's play a game!");
        say("You think of an animal, and I guess it.");
        say("Press enter when you're ready.");
        read();
    }

    private void addAnimal() {
        say("I give up. What animal do you have in mind?");
        Node newAnimal = new Node(parseAnimal(read()));
        say(String.format("Specify a fact that distinguishes %s from %s.", tree.getCurrent().getText(), newAnimal.getText()));
        String fact = read();
        say(String.format("Is the statement correct for %s?", newAnimal.getText()));
        Node newNode;
        if(readYes()) {
            newNode = new Node(fact, newAnimal, tree.getCurrent());
        } else {
            newNode = new Node(fact, tree.getCurrent(), newAnimal);
        }
        tree.addNode(newNode);
        say("I have learned the following facts about animals:");
        say(String.format("%s", makeSentence(newNode.getNodeYes().getText(), newNode.getText())));
        say(String.format("%s", makeNotSentence(newNode.getNodeNo().getText(), newNode.getText())));
        say("I can distinguish these animals by asking the question:");
        say(makeQuestion(newNode.getText()));
        say("Nice! I've learned so much about animals!");
    }

    private void playAgain() {
        say("Would you like to play again?");
        if (!readYes()) {
            tree.saveTree();
            say(vocabulary.sayBye());
            endGame = true;
        } else {
            tree.setCurrent(tree.getRoot());
            say("You think of an animal, and I guess it.");
            say("Press enter when you're ready.");
            read();
        }
    }

    private void say(String message) {
        System.out.println(message);
    }

    private String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase().trim();
    }

    private boolean readYes() {
        String answer = read();
        while (Boolean.FALSE.equals(vocabulary.isYes(answer)) && Boolean.FALSE.equals(vocabulary.isNo(answer))) {
            say(vocabulary.clarification());
            answer = read();
        }
        return vocabulary.isYes(answer);
    }

    private String parseAnimal(String input) {
        String name = input.replaceAll("(a|an|the) \\b","");
        String article = input.replace(name, "").trim();
        if (article.contains("the") || article.length() == 0) {
            article = input.matches("[aeouiy].+") ? "an" : "a";
        }
        return article + " " + name;
    }

    private String makeSentence(String value, String fact) {
        return value.replaceAll("(a|an) \\b","The ") + fact.replace("it", "");
    }

    private String makeNotSentence(String value, String fact) {
        return value.replaceAll("(a|an) \\b","The ") + fact
                .replaceFirst("it", "")
                .replaceFirst("can", "can't")
                .replaceFirst("has", "doesn't have")
                .replaceFirst("is", "isn't");
    }

    private String makeQuestion(String text) {
        if (text.matches("^(a|an) .+")) {
            return "Is it " + text + "?";
        }
        return text.replace("it is", "Is it")
                .replace("it has", "does it have")
                .replace("it can", "Can it") + "?";
    }
}