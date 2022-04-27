package animals;

public class Animal {
    private String name;
    private String question;

    Animal(String input) {
        this.name = makeName(input);
        this.question = makeQuestion(name);
    }

    private String makeName(String text) {
        String trimmed = text.replaceFirst("(^a|^an|^the) ", "");
        return (trimmed.matches("^[aeouyi].+") ? "an " : "a ") + trimmed;
    }

    private String makeQuestion(String text) {
        return "is it " + text + "?";
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }
}
