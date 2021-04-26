package com.mobiquity.solver.model;

import com.mobiquity.exception.APIException;
import com.mobiquity.solver.enums.LogEvent;
import lombok.Builder;
import lombok.Getter;

/**
 * Class containing data related to an object from the package.
 */
@Getter
public class Item implements Comparable {
    /**
     * Item's index.
     */
    private final int index;
    /**
     * Item's weight.
     */
    private final double weight;
    /**
     * Item's value.
     */
    private final double value;

    /**
     * Item Builder.
     * @param   index           Item index
     * @param   weight          Item weight
     * @param   value           Item value
     * @throws  APIException    Custom Exception
     */
    @Builder
    public Item(int index, double weight, double value) throws APIException {

        if (!(weight <= TestCase.MAX_WEIGHT && weight > 0)) {
            throw new APIException(LogEvent.INVALID_OBJECT_WEIGHT.toString());
        }

        if (!(value <= TestCase.MAX_WEIGHT && value > 0)) {
            throw new APIException(LogEvent.INVALID_OBJECT_COST.toString());
        }

        this.index = index;
        this.weight = weight;
        this.value = value;

    }

    /**
     * Compare to items by their weights.
     * @param   object  object to compare to
     * @return          int value of comparison
     */
    @Override
    public int compareTo(Object object) {

        Item item = (Item)object;
        return Double.compare(this.getWeight(), item.getWeight());

    }

}

