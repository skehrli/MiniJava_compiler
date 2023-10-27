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

public class TestParser {

    public static final String SAMPLE_FILES_LOCATION = "SamplePrograms/";
    public static final String JUNIT_FILES_LOCATION = "test/resources/";
    public static final String PROGRAMS_SUBDIR = "Programs/";
    public static final String OUTPUT_SUBDIR = "Outputs/";
    public static final String EXPECTED_SUBDIR = "Expected/";
    public static final String FAILURES_SUBDIR = "Failures/";
    public static final String INPUT_EXTENSION = ".java";
    public static final String OUTPUT_EXTENSION = ".out";
    public static final String EXPECTED_EXTENSION = ".expected";

    private Stream<String> fileNames(String dir) {
        try {
            return Files.walk(Paths.get(dir)).map(Path::toFile)
                    .filter(File::isFile)
                    .map(File::getName)
                    .map(x -> x.substring(0, x.length() - 5));
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    @Test
    public void testMiniJava() throws FileNotFoundException {
        for (String file : fileNames(JUNIT_FILES_LOCATION + PROGRAMS_SUBDIR).toArray(String[]::new)) {
            String expected = JUNIT_FILES_LOCATION + EXPECTED_SUBDIR + file + EXPECTED_EXTENSION;
            String out = JUNIT_FILES_LOCATION + OUTPUT_SUBDIR + file + OUTPUT_EXTENSION;
            PrintStream fileOutputStream = new PrintStream(
                    new FileOutputStream(JUNIT_FILES_LOCATION + OUTPUT_SUBDIR + file + OUTPUT_EXTENSION));
            System.setOut(fileOutputStream);
            MiniJava.main(new String[] { "-A", JUNIT_FILES_LOCATION + PROGRAMS_SUBDIR + file + INPUT_EXTENSION });
            try {
                assertTrue(areFilesEqualTokenByToken(expected, out));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean areFilesEqualTokenByToken(String file1Path, String file2Path) throws IOException {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1Path));
                BufferedReader reader2 = new BufferedReader(new FileReader(file2Path))) {

            String line1, line2;

            while ((line1 = reader1.readLine()) != null) {
                line2 = reader2.readLine();

                if (line2 == null) {
                    return false; // File 2 has fewer lines.
                }

                // Split lines into tokens based on white spaces.
                String[] tokens1 = line1.split("\\s+");
                String[] tokens2 = line2.split("\\s+");

                // Compare tokens.
                if (!areArraysEqual(tokens1, tokens2)) {
                    return false;
                }
            }

            // Check if file 2 has any remaining lines.
            return reader2.readLine() == null;
        }
    }

    public static boolean areArraysEqual(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }

        return true;
    }
}
