package com.mobiquity.solver.model.input;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    private static final String PATH = "src/main/test/resources/example_input";

    private static final String OUT_PATH = "src/main/test/resources/example_output";

    private static final String INVALID_PATH = "src/main/test/resources/invalid_file";

    private static final String INVALID_DATA_PATH = "src/main/test/resources/example_input_fail01";

    private static final String EMPTY_FILE_PATH = "src/main/test/resources/empty_file";

    @Test
    public void readTestCasesSuccess() {
        assertDoesNotThrow(() -> FileReader.readTestCases(PATH));
    }

    @Test
    public void shouldThrowExceptionIfInputFileMissing() {
        assertThrows(APIException.class, () -> FileReader.readTestCases(INVALID_PATH),
                    "LOG : IN-01 : The file path is invalid\n");
    }

    @Test
    public void readOutputFileSuccess() {
        assertDoesNotThrow(() -> FileReader.readOutputFile(OUT_PATH));
    }

    @Test
    public void shouldThrowExceptionIfOutputFileMissing() {
        assertThrows(APIException.class, () -> FileReader.readOutputFile(INVALID_PATH),
                    "LOG : IN-01 : The file path is invalid\n");
    }

    @Test
    public void shouldThrowExceptionIfDataNotAccurate() {
        assertThrows(APIException.class, () -> FileReader.readTestCases(INVALID_DATA_PATH),
                    "LOG : IN-02 : The data in the file is invalid\n");
    }

    @Test
    public void shouldThrowExceptionIfFileIsEmpty() {
        assertThrows(APIException.class, () -> FileReader.readTestCases(EMPTY_FILE_PATH),
                "LOG : IN-02 : The data in the file is invalid\n");
    }

}