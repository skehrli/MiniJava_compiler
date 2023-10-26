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


}
