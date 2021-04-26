package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.Solver;

/**
 * Packer class.
 */
public class Packer {

  /**
   * Solver that computes the solution.
   */
  private static Solver solver;

  private Packer() {
  }

  /**
   * Method to solve scenarios from a file.
   * @param   filePath    file path as string
   * @return              results of all test cases from file
   * @throws APIException Custom exception with error code and message
   */
  public static String pack(String filePath) throws APIException {
    solver = Solver.getSolver(filePath);
    solver.solve();
    return solver.getResult();
  }
}
