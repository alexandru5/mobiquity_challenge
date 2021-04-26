package com.mobiquity.solver;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.model.input.FileReader;
import com.mobiquity.solver.model.Item;
import com.mobiquity.solver.model.TestCase;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 * Solver class with the solution of the problem.
 */
@Getter
public final class Solver {

    /**
     * Constant used to convert double to int.
     */
    private static final double ROUND = 100;
    /**
     * Solver.
     */
    private static Solver solver;
    /**
     * Input file path.
     */
    private final String filePath;
    /**
     * List of test cases.
     */
    private List<TestCase> testCases;
    /**
     * The resulted solutions for test cases.
     */
    private String result;

    private Solver(String filePath) {
        this.filePath = filePath;
        this.result = "";
    }

    /**
     * Solver Singleton.
     * @param   filePath    input file path for solver
     * @return              Solver
     */
    public static Solver getSolver(String filePath) {
        if (solver == null) {
            solver = new Solver(filePath);
        }
        return solver;
    }

    /**
     * Method that computes solution for each test case.
     * @throws APIException
     */
    public void solve() throws APIException {
        testCases = FileReader.readTestCases(filePath);

        for (TestCase testCase : testCases) {
            Collections.sort(testCase.getItems());
            result += solveTestCase(testCase) + "\n";
        }
    }

    /**
     * Solve dynamically the test case.
     * @param   testCase    the test case
     * @return              solution
     */
    public String solveTestCase(TestCase testCase) {

        int capacity = (int) (testCase.getMaxWeight() * ROUND);

        List<Item> items = testCase.getItems();
        int[][] board = new int[testCase.getNoOfItems() + 1][capacity + 1];

        for (int i = 1; i < testCase.getNoOfItems() + 1; i++) {
            for (int j = 0; j <= capacity; j++) {

                int weight = (int) (items.get(i - 1).getWeight() * ROUND);

                if (weight > j) {
                    board[i][j] = board[i - 1][j];
                } else {
                    int value = (int) items.get(i - 1).getValue();

                    board[i][j] = Math.max(board[i - 1][j],
                                            board[i - 1][j - weight] + value);
                }
            }
        }
        return computeItems(testCase, board, capacity);
    }

    /**
     * Compute the solution for the test case from processed data.
     * @param   testCase    the test case resolved
     * @param   board       the dynamic matrix of the solution
     * @param   capacity    the maximum capacity of the package
     * @return              computed solution
     */
    public String computeItems(TestCase testCase, int[][] board, int capacity) {
        String result = "";
        List<Item> items = testCase.getItems();
        int result_capacity = board[testCase.getNoOfItems()][capacity];
        int weight = capacity;

        // we go back through items and check if they were picked
        for (int i = testCase.getNoOfItems(); i > 0
                                                && result_capacity > 0; i--) {
            // picked if weight matches result capacity
            if (result_capacity != board[i - 1][weight]) {

                result += items.get(i - 1).getIndex() + ",";
                result_capacity -= (int) items.get(i - 1).getValue();
                weight -= (int) (items.get(i - 1).getWeight() * ROUND);

            }
        }

        return result != "" ? result.substring(0, result.length() - 1) : "-";
    }


}
