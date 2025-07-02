# Introduction

prior prioritzes lists.  Any ASCII file is considered a list and prior will prioritize it and write the file out in
priority order.


It prioritizes items by asking the user to choose which item is more important.  It does this for items one and two,
then one and tree, etc.  For each comparison it asks if there is a reason that one item is more important than
another.  It prints out any existing reasons and allows the user to enter a new reason or to hit return, signifying that 
an existing reason pertains or that there is no reason.

prior does this for all items and then writes out the file in priority order.  The order of an item is determined
by tbe number of comparisons in which it is chosen.  If two items have the same "scores," then the item that was chosen 
in the comparison between the two items comes first.

# Details

## Item

Any line that doesn't start with whitespace is considered an item or something you want to prioritze in prior.  Examples
include "one" or "Go to the supermarket."

## Reasons

Lines that do start with whitespace such as "\\tBecause we need the groceries" or "\\tBecause it's number one" are what
prior refers to as reasons.

When prior compares two items, the user can enter a new reason (without a whitespace character), or hit return to
signify an existing reason or no reason at all.

## Writing the file

The file is written in the new, priority order,  "Priority order" is in the order of which item "won" the most
comparisons (ie the item that was chosen as more important the most number of times).  If two items have the same
"score" (ie two items were chosen as more important the same number of times) then the item the user designated as
more important in their own comparison is judged as having a higher priority.

The original file is renamed to *<file name\>.backup*
during the operation and discarded if everything goes well. 