# Introduction
prior prioritzes lists.  Any ASCII file is considderd a list and prior will prioritize it and write the file out in
priority order.

It prioties items by asking the user to choose which item is more important.  It does this for items one and two, 
then one and tree, etc.  For each comparrison it asks if there is a reason that one item is more important than 
another.  It prints out any existing reason and allows the user to hit return, signifying that an existing reason
hold or that there is no reason, or the user can enter a new reason.

prior does this for all items and then writes out the file in priority order.  The order of an item is determined
by tbe number of comparrisons it is chosen.  If two items have the same "scores," then the item that was chosen in
the comparrison between the two items comes first.
