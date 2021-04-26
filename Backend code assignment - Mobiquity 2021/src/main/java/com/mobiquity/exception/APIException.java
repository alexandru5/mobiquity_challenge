package com.mobiquity.exception;

/**
 * Custom API Exception.
 */
public class APIException extends Exception {

  /**
   * Custom Constructor.
   * @param   message   a custom message to be displayed
   * @param e           general exception caught
   */
  public APIException(String message, Exception e) {
    super(message, e);
  }

  /**
   * Custom Message Constructor.
   * @param   message   a custom message to be displayed
   */
  public APIException(String message) {
    super(message);
  }
}
