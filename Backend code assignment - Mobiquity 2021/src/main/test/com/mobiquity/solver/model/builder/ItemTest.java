package com.mobiquity.solver.model.builder;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private static final int INDEX = 1;

    private static final double WEIGHT = 50.05;

    private static final double VALUE = 51;

    @Test
    public void builder() throws APIException {
        Item item = Item.builder()
                        .index(INDEX)
                        .weight(WEIGHT)
                        .value(VALUE)
                        .build();
        assertEquals(item.getIndex(), INDEX);
        assertEquals(item.getWeight(), WEIGHT);
        assertEquals(item.getValue(), VALUE);
    }

    @Test
    public void shouldThrowExceptionIfItemCostNotInLimits() {
        assertThrows(APIException.class, () -> Item.builder()
                                                    .index(INDEX)
                                                    .weight(WEIGHT)
                                                    .value(2 * VALUE)
                                                    .build(),
                "LOG : SLV-02 : The cost of the object is greater than 100\n");
    }

    @Test
    public void shouldThrowExceptionIfItemWeightNotInLimits() {
        assertThrows(APIException.class, () -> Item.builder()
                                                    .index(INDEX)
                                                    .weight(2 * WEIGHT)
                                                    .value(VALUE)
                                                    .build(),
                "LOG : SLV-03 : The weight of the object is greater than 100\n");
    }
}
