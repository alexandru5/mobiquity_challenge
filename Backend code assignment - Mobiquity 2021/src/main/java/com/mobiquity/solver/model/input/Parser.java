package com.mobiquity.solver.model.input;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.enums.LogEvent;
import com.mobiquity.solver.model.Item;
import com.mobiquity.solver.model.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parser for the content of the file.
 */
public class Parser {
    /**
     * Regex patter to split test case.
     */
    private static final String REGEX_PATTER = "[ \\-:(,)\\p{Sc}\\p{javaLetter}]+";
    /**
     * Number of item's elements.
     */
    private static final int NO_OF_ITEM_FIELDS = 3;
    /**
     * Package max weight.
     */
    private static double maxWeight;
    /**
     * List of items to pe parsed.
     */
    private static List<Item> items;

    /**
     * Method to load a test case from string to class.
     * @param   testCase        string line from the file
     * @return                  test case object
     * @throws  APIException    exception with logged code and message
     */
    public static TestCase parseTestCase(String testCase) throws APIException {

        items = new ArrayList<>();
        List<String> content = new ArrayList<>(Arrays.asList(testCase.split(REGEX_PATTER)));

        if (content.size() % NO_OF_ITEM_FIELDS != 1) {
            throw new APIException(LogEvent.INVALID_FILE_DATA.toString());
        }

        maxWeight = Double.valueOf(content.remove(0));

        for (int i = 0; i < (content.size() * NO_OF_ITEM_FIELDS - NO_OF_ITEM_FIELDS)
                                    / NO_OF_ITEM_FIELDS; i += NO_OF_ITEM_FIELDS) {

            items.add(parseObject(content.subList(i, i + NO_OF_ITEM_FIELDS)));

        }

        return TestCase.builder()
                        .maxWeight(maxWeight)
                        .items(items)
                        .build();
    }

    /**
     * Method to load item from string to class.
     * @param   object  object as string from file
     * @return          item as an object
     */
    private static Item parseObject(List<String> object) throws APIException {

        int index = Integer.valueOf(object.get(0));
        double weight = Double.valueOf(object.get(1));
        double value = Double.valueOf(object.get(2));

        return Item.builder().index(index)
                .weight(weight)
                .value(Double.valueOf(value))
                .build();
    }

}

