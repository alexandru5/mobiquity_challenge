package com.mobiquity.solver.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogEventTest {

    @Test
    void values() {

        assertEquals(6, LogEvent.values().length);

        assertEquals("IN-01", LogEvent.INVALID_FILE_PATH.getCode());
        assertEquals("The file path is invalid", LogEvent.INVALID_FILE_PATH.getMessage());

        assertEquals("IN-02", LogEvent.INVALID_FILE_DATA.getCode());
        assertEquals("The data in the file is invalid", LogEvent.INVALID_FILE_DATA.getMessage());

        assertEquals("SLV-01", LogEvent.INVALID_PACKAGE_WEIGHT.getCode());
        assertEquals("The max weight of the package is greater than 100", LogEvent.INVALID_PACKAGE_WEIGHT.getMessage());

        assertEquals("SLV-02", LogEvent.INVALID_OBJECT_WEIGHT.getCode());
        assertEquals("The weight of the object is greater than 100", LogEvent.INVALID_OBJECT_WEIGHT.getMessage());

        assertEquals("SLV-03", LogEvent.INVALID_OBJECT_COST.getCode());
        assertEquals("The cost of the object is greater than 100", LogEvent.INVALID_OBJECT_COST.getMessage());

        assertEquals("NIT-01", LogEvent.INVALID_NO_OF_ITEMS.getCode());
        assertEquals("The number of items is greater than 15", LogEvent.INVALID_NO_OF_ITEMS.getMessage());

    }
}