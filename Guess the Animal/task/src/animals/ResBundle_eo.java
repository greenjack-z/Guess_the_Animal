package animals;

import java.util.ListResourceBundle;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResBundle_eo extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"goodMorning","Bonan matenon"},
                {"goodAfternoon","Bonan posttagmezon"},
                {"goodEvening","Bonan vesperon"},
                {"error", "Eraro!"},
                {"clarification", new String[]{
                        "Mi ne certas, ke mi kaptis vin: ĉu jes aŭ ne?",
                        "Amuze, mi ankoraŭ ne komprenas, ĉu jes aŭ ne?",
                        "Ho, ĝi estas tro komplika por mi: nur diru al mi jes aŭ ne.",
                        "Ĉu vi povus simple diri jes aŭ ne?",
                        "Ho, ne, ne provu konfuzi min: diru jes aŭ ne."
                }},
                {"sayBye", new String[]{
                        "Ĝis revido.",
                        "Ĝis la revido",
                }},
                {"isYes", new String[]{
                        "y",
                        "j",
                        "jes",
                        "certe",
                        "ĝuste",
                        "jesa",
                        "ĝusta",
                        "fakte",
                        "vi vetas",
                        "vi diris ĝin"
                }},
                {"isNo", new String[]{
                        "n",
                        "ne",
                        "neniel",
                        "nah",
                        "negativa",
                        "mi ne pensas tiel",
                        "jes ne"
                }},
                {"I want to learn about animals.", "Mi volas lerni pri bestoj."},
                {"Which animal do you like most?","Kiun beston vi plej ŝatas?"},
                {"Welcome to the animal expert system!","Bonvenon al la sperta sistemo de la besto!"},
                {"What do you want to do:","Kion vi volas fari:"},
                {"1. Play the guessing game","1. Ludi la divenludon"},
                {"2. List of all animals","2. Listo de ĉiuj bestoj"},
                {"3. Search for an animal","3. Serĉi beston"},
                {"4. Calculate statistics","4. Kalkuli statistikon"},
                {"5. Print the Knowledge Tree","5. Presu la Scion-Arbon"},
                {"0. Exit","0. Eliri"},
                {"Here are the animals I know:","Jen la bestoj, kiujn mi konas:"},
                {"Enter the animal:","Enigu la beston:"},
                {"Facts about the","Faktoj pri la"},
                {"The Knowledge Tree stats","La statistiko de la Scio-Arbo"},
                {"root node","radika nodo"},
                {"total number of nodes", "totala nombro de nodoj"},
                {"total number of animals", "totala nombro de bestoj"},
                {"total number of statements", "totala nombro de deklaroj"},
                {"height of the tree", "alteco de la arbo"},
                {"minimum animal's depth", "minimuma profundo de besto"},
                {"average animal's depth", "averaĝa profundo de besto"},
                {"There are no facts about","Ne estas faktoj pri %s%n"},
                {"Please enter the number from the list above.","Bonvolu enigi la numeron el la supra listo."},
                {"Input must be a number!","Enigo devas esti nombro!"},
                {"You think of an animal, and I guess it.","Vi pensu pri besto, kaj mi divenos ĝin."},
                {"Press enter when you're ready.","Premu enen kiam vi pretas."},
                {"I win","mi gajnas"},
                {"Would you like to play again?","Ĉu vi ŝatus ludi denove?"},
                {"I give up. What animal do you have in mind?","Mi rezignas. Kiun beston vi havas en la kapo?"},
                {"Specify a fact that distinguishes %s from %s.%nThe sentence should be of the format: 'It can/has/is ...'.","Indiku fakton, kiu distingas %s de %s.%nLa frazo devus esti de la formato: 'Ĝi povas/havas/estas...'."},
                {"Is the statement correct for %s?","Ĉu la aserto ĝustas por la %s?"},
                {"I have learned the following facts about animals:","Mi lernis la jenajn faktojn pri bestoj:"},
                {"I can distinguish these animals by asking the question:","Mi povas distingi ĉi tiujn bestojn farante la demandon:"},
                {"Nice! I've learned so much about animals!","Bela! Mi multe lernis pri bestoj!"},
                {"parseAnimal", (UnaryOperator<String>) input -> input},
                {"makeSentence", (BinaryOperator<String>) (value, fact) -> "La " + value + fact.replace("ĝi", "") + "."},
                {"makeNotSentence", (BinaryOperator<String>) (value, fact) -> "La " + value + fact.replace("ĝi", " ne") + "."},
                {"makeQuestion", (UnaryOperator<String>) text -> {
                        Pattern p = Pattern.compile("Ĝi .+", Pattern.CANON_EQ);
                        Matcher matcher = p.matcher(text);
                        if (matcher.find()) {
                            return "Ĉu " + text + "?";
                        }
                        return "Ĉu ĝi estas " + text + "?";
                }}
        };
    }
}
