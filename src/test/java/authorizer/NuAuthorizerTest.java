package authorizer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NuAuthorizerTest {

    private static final String CASES_PATH = "src/test/resources/integration/case/";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void TestCreatingAnAccountSuccessfully() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestCreatingAnAccountSuccessfully/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestCreatingAnAccountSuccessfully/expected.json").trim();
        setIn(input);
        NuAuthorizer.main(new String[]{});
        var output = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expected, output);
    }

    @Test
    void TestAccountAlreadyInitialized() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestAccountalreadyInitialized/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestAccountalreadyInitialized/expected.json").trim();
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }


    @Test
    void TestProcessingATransactionSuccessfully() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestProcessingATransactionSuccessfully/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestProcessingATransactionSuccessfully/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }


    @Test
    void TestProcessingATransactionWhichViolatesTheAccount_not_initializedLogic() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesTheAccount-not-initializedLogic/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesTheAccount-not-initializedLogic/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }

    @Test
    void TestProcessingATransactionWhichViolatesCard_not_activeLogic() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesCard-not-activeLogic/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesCard-not-activeLogic/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }

    @Test
    void TestProcessingATransactionWhichViolatesInsufficient_limit_logic() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesInsufficient-limit-logic/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesInsufficient-limit-logic/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);

    }

    @Test
    void TestProcessingATransactionWhichViolatesTheHigh_frequency_small_intervalLogic() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesTheHigh-frequency-small-intervalLogic/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesTheHigh-frequency-small-intervalLogic/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }

    @Test
    void TestProcessingATransactionWhichViolatesTheDoubled_transactionLogic() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesTheDoubled-transactionLogic/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestProcessingATransactionWhichViolatesTheDoubled-transactionLogic/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }

    @Test
    void TestProcessingTransactionsThatViolateMultipleLogics() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestProcessingTransactionsThatViolateMultipleLogics/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestProcessingTransactionsThatViolateMultipleLogics/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }

    @Test
    void TestShouldNotTriggerTheHigh_frequency_small_interval() throws IOException {
        var input = getInputFromFile(CASES_PATH + "TestShouldNotTriggerTheHigh_frequency_small_interval/input.json");
        var expected = getInputFromFile(CASES_PATH + "TestShouldNotTriggerTheHigh_frequency_small_interval/expected.json");
        setIn(input);
        NuAuthorizer.main(new String[]{});
        checkAssert(expected);
    }


    private String getInputFromFile(String pathStr) throws IOException {
        var path = Paths.get(pathStr);
        var lines = Files.lines(path);
        String input = lines.collect(Collectors.joining("\n"));
        lines.close();
        return input;
    }

    private void setIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private void checkAssert(String expected) {
        var output = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expected.replaceAll("\r", ""), output.replaceAll("\r", ""));
    }
}