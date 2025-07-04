package com.ltsllc.prior;

import java.util.Comparator;

/******************************************************************************
 * A class for comparing two {@link Item}s.
 */
public class ItemComparator implements Comparator<Item> {
    /**************************************************************************
     * Compare two {@link Item}s, return -1 if the first should come before
     * the other, 1 if the second should come before the other.
     *
     * Technically, the method should return 0 to signal that neither item
     * should come before the other, but this should never happen.
     *
     * {@link Item}s are compared based on their respective "scores" (the
     * number of times the Item was chosen to be more important).  If two
     * items have the same score, then whichever was judged to be more
     * important, as determined by the {@link Item#comesBefore} method,
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return 1 if item two should come before one, -1 if one should come
     * before two.
     */
    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getScore() > o2.getScore()) {
            return -1;
        } else if (o2.getScore() > o1.getScore()) {
            return 1;
        } else if (o1.getScore() == o2.getScore()) {
            if (o1.isBefore(o2)) {
                return -1;
            } else {
                return 1;
            }
        } else {
            throw new RuntimeException("impossible case");
        }
    }
}
