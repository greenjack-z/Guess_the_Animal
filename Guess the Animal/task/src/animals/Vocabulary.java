package animals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;

public class Vocabulary {
    static final Random RND = new Random();

    public String greeting() {
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.of(5, 0)) && time.isBefore(LocalTime.of(12, 1))) {
            return Main.res.getString("goodMorning");
        }
        if (time.isAfter(LocalTime.of(12, 0)) && time.isBefore(LocalTime.of(18, 1))) {
            return Main.res.getString("goodAfternoon");
        }
        if (time.isAfter(LocalTime.of(18, 0)) && time.isBefore(LocalTime.of(5, 1))) {
            return Main.res.getString("goodEvening");
        }
        return Main.res.getString("error");
    }

    public String clarification() {
        String[] strings = Main.res.getStringArray("clarification");
        return strings[RND.nextInt(strings.length)];
    }

    public String sayBye() {
        String[] strings = Main.res.getStringArray("sayBye");
        return strings[RND.nextInt(strings.length)];
    }

    public Boolean isYes(String input) {
        String[] strings = Main.res.getStringArray("isYes");
        return Arrays.stream(strings).anyMatch(s -> s.equals(input.replaceAll("\\b[.!]", "")));
    }

    public Boolean isNo(String input) {
        String[] strings = Main.res.getStringArray("isNo");
        return Arrays.stream(strings).anyMatch(s -> s.equals(input.replaceAll("\\b[.!]", "")));
    }
}