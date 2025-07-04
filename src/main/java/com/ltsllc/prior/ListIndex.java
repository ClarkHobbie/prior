package com.ltsllc.prior;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * A class that maintains a position in a list.
 *
 * @param <T> The type of the items in the {@link List}.
 */
public class ListIndex<T> {
    /**************************************************************************
     * The {@link List}.
     */
    public List<T> list;

    /**************************************************************************
     * The index within the {@link List}, not within the item.
     */
    public int index;

    public ListIndex (List<T> theList, int theIndex) {
        this.list = theList;
        this.index = theIndex;
    }

    public ListIndex(ListIndex<T> listIndex) {
        list = listIndex.list;
        index = listIndex.index;
    }

    public ListIndex() {
        list = new ArrayList<>();
        index = -1;
    }
}

