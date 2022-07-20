package animals;

import java.util.ListResourceBundle;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class ResBundle_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"goodMorning","Good morning"},
                {"goodAfternoon","Good afternoon"},
                {"goodEvening","Good evening"},
                {"error", "Error!"},
                {"clarification", new String[]{
                        "I'm not sure I caught you: was it yes or no?",
                        "Funny, I still don't understand, is it yes or no?",
                        "Oh, it's too complicated for me: just tell me yes or no.",
                        "Could you please simply say yes or no?",
                        "Oh, no, don't try to confuse me: say yes or no."
                }},
                {"sayBye", new String[]{
                        "Bye Bye!",
                        "See you.",
                        "Goodbye."
                }},
                {"isYes", new String[]{
                        "y",
                        "yes",
                        "yeah",
                        "yep",
                        "sure",
                        "right",
                        "affirmative",
                        "correct",
                        "indeed",
                        "you bet",
                        "exactly",
                        "you said it"
                }},
                {"isNo", new String[]{
                        "n",
                        "no",
                        "no way",
                        "nah",
                        "nope",
                        "negative",
                        "i don't think so",
                        "yeah no"
                }},
                {"I want to learn about animals.", "I want to learn about animals."},
                {"Which animal do you like most?","Which animal do you like most?"},
                {"Welcome to the animal expert system!","Welcome to the animal expert system!"},
                {"What do you want to do:","What do you want to do:"},
                {"1. Play the guessing game","1. Play the guessing game"},
                {"2. List of all animals","2. List of all animals"},
                {"3. Search for an animal","3. Search for an animal"},
                {"4. Calculate statistics","4. Calculate statistics"},
                {"5. Print the Knowledge Tree","5. Print the Knowledge Tree"},
                {"0. Exit","0. Exit"},
                {"Here are the animals I know:","Here are the animals I know:"},
                {"Enter the animal:","Enter the animal:"},
                {"Facts about the","Facts about the"},
                {"The Knowledge Tree stats","The Knowledge Tree stats"},
                {"root node","root node"},
                {"total number of nodes", "total number of nodes"},
                {"total number of animals", "total number of animals"},
                {"total number of statements", "total number of statements"},
                {"height of the tree", "height of the tree"},
                {"minimum animal's depth", "minimum animal's depth"},
                {"average animal's depth", "average animal's depth"},
                {"There are no facts about","There are no facts about %s%n"},
                {"Please enter the number from the list above.","Please enter the number from the list above."},
                {"Input must be a number!","Input must be a number!"},
                {"You think of an animal, and I guess it.","You think of an animal, and I guess it."},
                {"Press enter when you're ready.","Press enter when you're ready."},
                {"I win","I win"},
                {"Would you like to play again?","Would you like to play again?"},
                {"I give up. What animal do you have in mind?","I give up. What animal do you have in mind?"},
                {"Specify a fact that distinguishes %s from %s.%nThe sentence should be of the format: 'It can/has/is ...'.","Specify a fact that distinguishes %s from %s.%nThe sentence should be of the format: 'It can/has/is ...'."},
                {"Is the statement correct for %s?","Is the statement correct for %s?"},
                {"I have learned the following facts about animals:","I have learned the following facts about animals:"},
                {"I can distinguish these animals by asking the question:","I can distinguish these animals by asking the question:"},
                {"Nice! I've learned so much about animals!","Nice! I've learned so much about animals!"},
                {"parseAnimal", (UnaryOperator<String>) input -> {
                        String name = input.replaceAll("(a|an|the) \\b","");
                        String article = input.replace(name, "").trim();
                        if (article.contains("the") || article.length() == 0) {
                            article = input.matches("[aeouiy].+") ? "an" : "a";
                        }
                        return article + " " + name;
                }},
                {"makeSentence", (BinaryOperator<String>) (value, fact) -> value.replaceAll("(a|an) \\b","The ") + fact.replace("it", "") + "."},
                {"makeNotSentence", (BinaryOperator<String>) (value, fact) -> value
                        .replaceAll("(a|an) \\b","The ") + fact
                        .replaceFirst("it", "")
                        .replaceFirst("can", "can't")
                        .replaceFirst("has", "doesn't have")
                        .replaceFirst("is", "isn't") + "."},
                {"makeQuestion", (UnaryOperator<String>) text -> {
                        if (text.matches("^(a|an) .+")) {
                            return "Is it " + text + "?";
                        }
                        return text.replace("it is", "Is it")
                                .replace("it has", "does it have")
                                .replace("it can", "Can it") + "?";
                }}
        };
    }
}
