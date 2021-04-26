package com.mobiquity.solver.model.input;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.enums.LogEvent;
import com.mobiquity.solver.model.TestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *  Class that loads test cases from file.
 */
public class FileReader {

    /**
     * List of test cases.
     */
    private static List<TestCase> testCases;

    /**
     * Method that loads test cases from file into the list.
     * @param   path            path to file
     * @return                  list of test cases
     * @throws  APIException    exception with invalid file path message from log event
     */
    public static List<TestCase> readTestCases(String path) throws APIException {

        List<String> scenarios;
        Path filePath = Paths.get(path);
        testCases = new ArrayList<>();

        try {
            scenarios = Files.readAllLines(filePath);

            if (scenarios.size() == 0) {
                throw new APIException(LogEvent.INVALID_FILE_DATA.toString());
            }

            for (String scenario : scenarios) {
                testCases.add(Parser.parseTestCase(scenario));
            }
        } catch (IOException e) {
            throw new APIException(LogEvent.INVALID_FILE_PATH.toString(), e);
        }

        return testCases;

    }

    /**
     * Read data from output file.
     * @param   path            path to output file
     * @return                  content of output file
     * @throws  APIException    if invalid path received
     */
    public static List<String> readOutputFile(String path) throws APIException {

        List<String> output;
        Path filePath = Paths.get(path);

        try {
            output = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new APIException(LogEvent.INVALID_FILE_PATH.toString(), e);
        }

        return output;
    }

}
