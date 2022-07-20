package animals;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Main {
    private final String[] args;
    private final Vocabulary vocabulary = new Vocabulary();
    private final Tree tree;
    private Boolean game = true;

    public static final ResourceBundle res = ResourceBundle.getBundle("animals.ResBundle");

    Main (String[] args) {
        this.args = args;
        tree = new Tree();
    }

    public static void main(String[] args) {
        new Main(args).start();
    }

    private void start() {
        String fileType = null;
        if (args.length == 0) {
            fileType = "json";
        } else if (args[0].equals("-type")) {
            fileType = args[1];
        }
        tree.setMapper(fileType);
        say(vocabulary.greeting());
        say("");
        if (Boolean.FALSE.equals(tree.loadTree())) {
            say(res.getString("I want to learn about animals."));
            say(res.getString("Which animal do you like most?"));
            tree.addNode(new Node(parseAnimal(read())));
        }
        say(res.getString("Welcome to the animal expert system!"));
        say("");
        chooseAction();
    }

    private void chooseAction() {
        int action = -1;
        while (action != 0) {
            say("");
            say(res.getString("What do you want to do:"));
            say("");
            say(res.getString("1. Play the guessing game"));
            say(res.getString("2. List of all animals"));
            say(res.getString("3. Search for an animal"));
            say(res.getString("4. Calculate statistics"));
            say(res.getString("5. Print the Knowledge Tree"));
            say(res.getString("0. Exit"));
            try {
                action = Integer.parseInt(read());
                switch (action) {
                    case 1:
                        game = true;
                        guessAnimal();
                        break;
                    case 2:
                        say(res.getString("Here are the animals I know:"));
                        tree.listAnimals();
                        break;
                    case 3:
                        say(res.getString("Enter the animal:"));
                        String animal = read();
                        say(String.format("%s %s:", res.getString("Facts about the"), animal));
                        tree.searchAnimal(animal);
                        break;
                    case 4:
                        say(res.getString("The Knowledge Tree stats"));
                        tree.printStats();
                        break;
                    case 5:
                        tree.printTree();
                        break;
                    case 0:
                        say(vocabulary.sayBye());
                        break;
                    default:
                        say(res.getString("Please enter the number from the list above."));
                }
            } catch (NumberFormatException e) {
                say(res.getString("Input must be a number!"));
            }
        }
    }

    private void guessAnimal() {
        say(res.getString("You think of an animal, and I guess it."));
        say(res.getString("Press enter when you're ready."));
        read();
        tree.setCurrent(tree.getRoot());
        while(Boolean.TRUE.equals(game)) {
            say(makeQuestion(tree.getCurrent().getText()));
            if(readYes()) {
                if(tree.getCurrent().getNodeYes() == null) {
                    say(res.getString("I win"));
                    playAgainPrompt();
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
                    playAgainPrompt();
                }
            }
        }
    }

    private void addAnimal() {
        say(res.getString("I give up. What animal do you have in mind?"));
        Node newAnimal = new Node(parseAnimal(read()));
        say(String.format(res.getString("Specify a fact that distinguishes %s from %s.%nThe sentence should be of the format: 'It can/has/is ...'."), tree.getCurrent().getText(), newAnimal.getText()));
        String fact = read();
        say(String.format(res.getString("Is the statement correct for %s?"), newAnimal.getText()));
        Node newNode;
        if(readYes()) {
            newNode = new Node(fact, newAnimal, tree.getCurrent());
        } else {
            newNode = new Node(fact, tree.getCurrent(), newAnimal);
        }
        tree.addNode(newNode);
        say(res.getString("I have learned the following facts about animals:"));
        say(String.format("%s", makeSentence(newNode.getNodeYes().getText(), newNode.getText())));
        say(String.format("%s", makeNotSentence(newNode.getNodeNo().getText(), newNode.getText())));
        say(res.getString("I can distinguish these animals by asking the question:"));
        say(makeQuestion(newNode.getText()));
        say(res.getString("Nice! I've learned so much about animals!"));
    }

    private void playAgainPrompt() {
        say(res.getString("Would you like to play again?"));
        if (!readYes()) {
            tree.saveTree();
            game = false;
        } else {
            say(res.getString("You think of an animal, and I guess it."));
            say(res.getString("Press enter when you're ready."));
            read();
            tree.setCurrent(tree.getRoot());
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
        UnaryOperator<String> operator = (UnaryOperator) res.getObject("parseAnimal");
        return operator.apply(input);
    }

    private String makeSentence(String value, String fact) {
        BinaryOperator<String> operator = (BinaryOperator) res.getObject("makeSentence");
        return operator.apply(value, fact);
    }

    private String makeNotSentence(String value, String fact) {
        BinaryOperator<String> operator = (BinaryOperator) res.getObject("makeNotSentence");
        return operator.apply(value, fact);
    }

    private String makeQuestion(String text) {
        UnaryOperator<String> operator = (UnaryOperator) res.getObject("makeQuestion");
        return operator.apply(text);
    }
}