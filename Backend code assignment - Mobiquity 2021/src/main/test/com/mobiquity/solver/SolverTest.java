package com.mobiquity.solver;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.model.input.FileReader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private static final String PATH = "src/main/test/resources/example_input";

    private static final String OUT_PATH = "src/main/test/resources/example_output";

    @Test
    void getSolver() {
        Solver solver = Solver.getSolver(PATH);
        assertNotNull(solver);
    }

    @Test
    void solve() throws APIException {

        Solver solver = Solver.getSolver(PATH);
        solver.solve();
        String result = solver.getResult();
        List<String> solutions = FileReader.readOutputFile(OUT_PATH);
        checkMatch(Arrays.asList(result.split("\n")), solutions);
    }

    // check match between test results and solutions
    private void checkMatch(List<String> result, List<String> solutions) {

        assertEquals(solutions.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            List<String> resultItems = Arrays.asList(result.get(i).split(","));
            List<String> solutionItems = Arrays.asList(solutions.get(i).split(","));

            solutionItems.forEach(solutionItem -> assertTrue(resultItems.contains(solutionItem)));
        }
    }
}