package com.mobiquity.solver.model;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.enums.LogEvent;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Class containing data related to a test case.
 */
@Getter
public class TestCase {
    /**
     * Package admitted max weight.
     */
    public static final int MAX_WEIGHT = 100;
    /**
     * Test max number of items.
     */
    public static final int MAX_NO_OF_ITEMS = 15;
    /**
     * Scenario's number of items.
     */
    private final int noOfItems;
    /**
     * Package max weight.
     */
    private final double maxWeight;
    /**
     * List of items.
     */
    private final List<Item> items;

    /**
     * Builder for test case.
     * @param   maxWeight       max weight of package
     * @param   items           items to be picked up from
     * @throws  APIException    Custom Exception
     */
    @Builder
    public TestCase(double maxWeight, List<Item> items) throws APIException {

        if (maxWeight > MAX_WEIGHT) {
            throw new APIException(LogEvent.INVALID_PACKAGE_WEIGHT.toString());
        }

        if (items.size() > MAX_NO_OF_ITEMS) {
            throw new APIException(LogEvent.INVALID_NO_OF_ITEMS.toString());
        }

        this.maxWeight = maxWeight;
        this.items = items;
        this.noOfItems = items.size();

    }

}
