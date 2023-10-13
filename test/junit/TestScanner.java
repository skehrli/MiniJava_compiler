import Scanner.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

import Parser.sym;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.util.function.Consumer;

import static org.junit.Assert.*;
import org.junit.Test;

/*
    This class shows one way to use JUnit for testing your compiler.

    NOTE: The single provided test case here is not designed for MiniJava!
    Use this as a starting point, but you will want to create tests
    that fit the MiniJava grammar and match whatever output format you choose
    (e.g. your chosen token names, parse table formats, etc).
    In later phases of the project, you may find it helpful to write test
    cases for Minijava.java itself rather than the underlying modules as is
    shown here.
*/
public class TestScanner {

    public static final String SAMPLE_FILES_LOCATION = "SamplePrograms/";
    public static final String JUNIT_FILES_LOCATION = "test/resources/";
    public static final String PROGRAMS_SUBDIR = "Programs/";
    public static final String OUTPUT_SUBDIR = "Outputs/";
    public static final String EXPECTED_SUBDIR = "Expected/";
    public static final String FAILURES_SUBDIR = "Failures/";
    public static final String INPUT_EXTENSION = ".java";
    public static final String OUTPUT_EXTENSION = ".out";
    public static final String EXPECTED_EXTENSION = ".expected";

    /*
     * Produces an output file for a test case given by (dir)/Programs/(caseName).java
     * to (dir)/Outputs/(caseName).out
     */
    private void produceOut(String dir, String caseName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(
                dir + OUTPUT_SUBDIR + caseName + OUTPUT_EXTENSION
            ));
            drive(dir + PROGRAMS_SUBDIR, caseName, writer::println, s->{});
        } catch (IOException e) { fail(e.getMessage()); }
    }

    /*
     * Compares the expected output for a case found in (dir)/Expected/(caseName).expected
     * to the tokenization of the case.
     */
    private void compareExpected(String dir, String caseName) {
        try {
            Iterator<String> fin = Arrays.stream(Files.readString(
                Paths.get(dir, EXPECTED_SUBDIR, caseName + EXPECTED_EXTENSION),
                Charset.defaultCharset()
            ).split("[ \n\t]")).iterator();
            drive(dir + PROGRAMS_SUBDIR, caseName, (String str) -> assertEquals(fin.next(), str), s->{});
        } catch (IOException e) { fail(e.getMessage()); }
    }

    /*
     * Assert that we can't actually tokenize the given case.
     */
    private void assertFails(String dir, String caseName) {
        // The idea here: we throw an IllegalStateException if there's
        // an error, which gets expected by the test. Messy way of
        // returning to the enclosing function, basically.
        try {
            System.err.println(caseName);
            drive(dir + FAILURES_SUBDIR, caseName,
                    s->{}, s->{System.err.println(s); throw new IllegalStateException();}
            );
        } catch (IOException e) { fail(e.getMessage()); }
    }

    /**
     * The test driver.
     * @param dir The directory to test in, with at least a subdirectory Programs.
     * @param caseName The test case to run.
     * @param action The action to perform with each token of the test case.
     * @throws IOException Potentially fails to open the FileInputStream input.
     */
    private void drive(String dir, String caseName, Consumer<String> action, Consumer<String> onErr) throws IOException {
        FileInputStream input = new FileInputStream(dir + caseName + INPUT_EXTENSION);
        ComplexSymbolFactory sf = new ComplexSymbolFactory();
        Reader in = new BufferedReader(new InputStreamReader(input));
        scanner s = new scanner(in, sf);
        Symbol t = s.next_token();
        while (t.sym != sym.EOF) {
            if (t.sym == sym.error) onErr.accept(s.symbolToString(t));
            action.accept(s.symbolToString(t));

            t = s.next_token();
        }
    }

    private Stream<String> fileNames(String dir) {
        try {
            return Files.walk(Paths.get(dir)).map(Path::toFile)
                    .filter(File::isFile)
                    .map(File::getName)
                    .map(x -> x.substring(0, x.length() - 5));
        } catch (IOException e) {
            fail(e.getMessage()); return Stream.empty();
        }
    }

    /*
     * Produces outputs for all the programs in the MiniJava sample programs
     * directory to be examined.
     */
    @Test
    public void samplePrograms() {
        fileNames(SAMPLE_FILES_LOCATION + PROGRAMS_SUBDIR)
                .forEach(x->produceOut(SAMPLE_FILES_LOCATION, x));
    }

    /*
     * Tests all the MiniJava files in the JUNIT resources against
     * their expected outputs.
     */
    @Test
    public void successfulPrograms() {
        fileNames(JUNIT_FILES_LOCATION + PROGRAMS_SUBDIR)
                .forEach(x->compareExpected(JUNIT_FILES_LOCATION, x));
    }

    @Test//(expected=IllegalStateException.class)
    public void failurePrograms() {
        fileNames(JUNIT_FILES_LOCATION + FAILURES_SUBDIR)
                .forEach(x-> {
                    try {
                        assertFails(JUNIT_FILES_LOCATION, x);
                        throw new RuntimeException("Did not fail.");
                    } catch (IllegalStateException ignored) {
                    }
                });
    }
}
