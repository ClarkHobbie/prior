package com.ltsllc.prior;

import java.util.List;

public class ListIndex<T> {
    public List<T> list;
    public int index;

    public ListIndex (List<T> theList, int theIndex) {
        this.list = theList;
        this.index = theIndex;
    }

    public ListIndex(ListIndex<T> listIndex) {
        list = listIndex.list;
        index = listIndex.index;
    }
}

