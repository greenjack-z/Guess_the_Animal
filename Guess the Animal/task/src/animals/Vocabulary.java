package animals;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class Vocabulary {
    static final Random RND = new Random();

    public String greeting() {
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.of(5, 0)) && time.isBefore(LocalTime.of(12, 1))) {
            return "Good morning";
        }
        if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(18, 1))) {
            return "Good afternoon";
        }
        if (time.isAfter(LocalTime.of(18, 0)) && time.isBefore(LocalTime.of(5, 1))) {
            return "Good evening";
        }
        return "Error";
    }

    public String clarification() {
        List<String> sentences = List.of(
                "I'm not sure I caught you: was it yes or no?",
                "Funny, I still don't understand, is it yes or no?",
                "Oh, it's too complicated for me: just tell me yes or no.",
                "Could you please simply say yes or no?",
                "Oh, no, don't try to confuse me: say yes or no."
        );
        return sentences.get(RND.nextInt(sentences.size()));
    }

    public String sayBye() {
        List<String> sentences = List.of(
                "Have a nice day",
                "Bye Bye!",
                "See you.",
                "So long",
                "Goodbye."
        );
        return sentences.get(RND.nextInt(sentences.size()));
    }

    public Boolean isYes(String input) {
        List<String> sentences = List.of(
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
        );
        return  sentences.contains(input.replaceAll("\\b[.!]", ""));
    }

    public Boolean isNo(String input) {
        List<String> sentences = List.of(
                "n",
                "no",
                "no way",
                "nah",
                "nope",
                "negative",
                "i don't think so",
                "yeah no"
        );
        return  sentences.contains(input.replaceAll("\\b[.!]",""));
    }
}