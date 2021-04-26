package com.mobiquity.solver.model.input;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private static final String TEST_CASE = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

    private static final String TEST_CASE_FAILURE = "81 : (1,53.38,€45)  (S,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

    @Test
    public void parseTestCaseSuccess() throws APIException {
        assertDoesNotThrow(() -> Parser.parseTestCase(TEST_CASE));
    }

    @Test
    public void parseTestCaseShouldThrowException() throws APIException {
        assertThrows(APIException.class, () -> Parser.parseTestCase(TEST_CASE_FAILURE));
    }
}