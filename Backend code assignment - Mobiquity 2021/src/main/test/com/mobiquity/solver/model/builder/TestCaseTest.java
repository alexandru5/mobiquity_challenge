package com.mobiquity.solver.model.builder;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.model.Item;
import com.mobiquity.solver.model.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCaseTest {

    private static double MAX_WEIGHT = 51;

    private List<Item> items;

    @BeforeEach
    public void setUp() throws APIException {
        items = new ArrayList<>();
        items.add(Item.builder()
                        .index(1)
                        .weight(23.5)
                        .value(30)
                        .build());
        items.add(Item.builder()
                        .index(2)
                        .weight(38.5)
                        .value(50)
                        .build());
        items.add(Item.builder()
                        .index(3)
                        .weight(41.32)
                        .value(55)
                        .build());
    }

    @Test
    public void builderShouldReturnTestCase() throws APIException {
        TestCase testCase = TestCase.builder()
                                        .maxWeight(MAX_WEIGHT)
                                        .items(items)
                                        .build();

        assertEquals(MAX_WEIGHT, testCase.getMaxWeight());
        assertEquals(items.size(), testCase.getNoOfItems());

        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i).getIndex(), testCase.getItems().get(i).getIndex());
            assertEquals(items.get(i).getWeight(), testCase.getItems().get(i).getWeight());
            assertEquals(items.get(i).getValue(), testCase.getItems().get(i).getValue());
        }
    }

    @Test
    public void shouldThrowExceptionIfMaxWeightNotInLimits() {
        assertThrows(APIException.class, () -> TestCase.builder()
                                                        .maxWeight(2 * MAX_WEIGHT)
                                                        .items(items)
                                                        .build(),
                "LOG : SLV-01 : The max weight of the package is greater than 100\n");
    }

    @Test
    public void shouldThrowExceptionIfNumberOfItemsNotInLimits() {

        for (int i = 0; i < 15; i++) {
            items.add(items.get(0));
        }

        assertThrows(APIException.class, () -> TestCase.builder()
                                                        .maxWeight(MAX_WEIGHT)
                                                        .items(items)
                                                        .build(),
                "LOG : NIT-01 : The number of items is greater than 15\n");
    }

}
