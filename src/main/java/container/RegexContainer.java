package container;

import java.util.regex.Pattern;
import model.Record;

/***
 * utility class in charge of containing the regular expressions
 * necessary to recognize the type of input (transaction, account)
 */
public final class RegexContainer {
    private RegexContainer() {
    }

    private static final Pattern accountRegex = Pattern.compile("\\{\"account\":\\s?\\{\"(?=\\S*['-])([a-zA-Z'-]+)\":.*}}");
    private static final Pattern txRegex = Pattern.compile("\\{\"transaction\":\\s?\\{\"(?=\\S*)([a-zA-Z'-]+)\":.*}}");

    public static boolean isTxMatches(Record input) {
        return txRegex.matcher(input.getInput()).matches();
    }

    public static boolean isTxMatches(String input) {
        return txRegex.matcher(input).matches();
    }

    public static boolean isAccountMatches(Record input) {
        return accountRegex.matcher(input.getInput()).matches();
    }

    public static boolean isAccountMatches(String input) {
        return accountRegex.matcher(input).matches();
    }
}
