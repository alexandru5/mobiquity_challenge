package com.mobiquity.solver.enums;

import lombok.Getter;

/**
 * Enum with error codes and error messages logged.
 */
@Getter
public enum LogEvent {

    /**
     * The invalid file path log event.
     */
    INVALID_FILE_PATH("IN-01", "The file path is invalid"),
    /**
     * The invalid file path log event.
     */
    INVALID_FILE_DATA("IN-02", "The data in the file is invalid"),
    /**
     * The invalid package max weight log event.
     */
    INVALID_PACKAGE_WEIGHT("SLV-01", "The max weight of the package is greater than 100"),
    /**
     * The invalid item max weight log event.
     */
    INVALID_OBJECT_WEIGHT("SLV-02", "The weight of the object is greater than 100"),
    /**
     * The invalid item max cost log event.
     */
    INVALID_OBJECT_COST("SLV-03", "The cost of the object is greater than 100"),
    /**
     * The invalid number of items log event.
     */
    INVALID_NO_OF_ITEMS("NIT-01", "The number of items is greater than 15");


    /**
     * The code event.
     */
    private String code;
    /**
     * The message event.
     */
    private String message;

    LogEvent(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "LOG : " + code + " : " + message;
    }
}
