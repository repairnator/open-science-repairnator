# R-Hero - Builds & Patches Analysis

## Content of the repository

## SequenceR Patches Categorization

### Summary

|                           | Change in a method call | Change in if-condition | Change in return expression | Change in assignments | Empty | Try removal |
|---------------------------|:-----------------------:|:----------------------:|:---------------------------:|:---------------------:|:-----:|:-----------:|
| **Number of Patches**     | 25                      | 24                     | 19                          | 12                    | 10    | 1           |

### Details

|Build ID  |Project                    |Patch ID|Category                                                                                                    |One line human fix|Correct|
|------------|---------------------------|--------|------------------------------------------------------------------------------------------------------------|------------------|-------|
|*[723539132]*(https://travis-ci.org/github/clebertsuconic/activemq-artemis/builds/723539132
) |*clebertsuconic*           |        |                                                                                                            |                  |       |
|            |                           |1       |update return expression to static method invocation                                                        |                  |       |
|            |                           |2       |update return expression to static field                                                                    |                  |       |
|            |                           |3       |update return expression to new array of strings                                                            |                  |       |
|            |                           |4       |update return expression to null                                                                            |                  |       |
|            |                           |5       |empty                                                                                                       |                  |       |
|            |                           |6       |update return expression to method call                                                                     |                  |       |
|            |                           |7       |update return expression to null                                                                            |                  |       |
|            |                           |8       |replace a method call with a new method call                                                                |                  |       |
|            |                           |9       |empty                                                                                                       |                  |       |
|            |                           |10      |replace a method call with a new method call                                                                |                  |       |
|            |                           |11      |replace a method call with a new method call (same method of a different object)                            |                  |       |
|            |                           |12      |replace a method call with a new method call (same object, a different method)                              |                  |       |
|            |                           |13      |replace a method call with a new method call (same object, a different method)                              |                  |       |
|            |                           |14      |replace a method call with a new method call (same method, a different object)                              |                  |       |
|            |                           |15      |empty                                                                                                       |                  |       |
|            |                           |16      |replace a method call with a new method call (everything the same, "this" added)                            |                  |       |
|            |                           |17      |replace a method call with a new method call (argument changed)                                             |                  |       |
|            |                           |18      |replace a method call with a new method call (everything the same, "this" removed)                          |                  |       |
|            |                           |19      |replace a method call with a new method call (same method, different object)                                |                  |       |
|            |                           |20      |replace a method call with a new method call (same method, different object)                                |                  |       |
|            |                           |21      |empty                                                                                                       |                  |       |
|            |                           |22      |replace a method call with a new method call (everything the same, "this" removed)                          |                  |       |
|            |                           |23      |replace a method call with a new method call (same object, a different method)                              |                  |       |
|            |                           |24      |replace a method call with a new method call (argument changed)                                             |                  |       |
|            |                           |25      |replace a method call with a new method call (same method, different object)                                |                  |       |
|            |                           |26      |replace a method call with a new method call (same object, a different method)                              |                  |       |
|            |                           |27      |replace a method call with a new method call (same method, different object)                                |                  |       |
|            |                           |28      |replace a method call with a new method call (same object, a different method)                              |                  |       |
|            |                           |29      |replace a method call with a new method call (everything the same, "this" removed)                          |                  |       |
|            |                           |30      |empty                                                                                                       |                  |       |
|            |                           |31      |replace a method call with a new method call (argument changed)                                             |                  |       |
|            |                           |32      |replace a method call with a new method call (same method, different object)                                |                  |       |
|            |                           |33      |replace a method call with a new method call (everything the same, "this" added)                            |                  |       |
|            |                           |34      |assignment replaced with a new assignment (right hand object changed to null)                               |                  |       |
|            |                           |35      |empty                                                                                                       |                  |       |
|            |                           |36      |assignemt replaced with a new assignment (right hand object cast to the same thing)                         |                  |       |
|            |                           |37      |assignemt replaced with a new assignment (a method of the same right hand is called)                        |                  |       |
|            |                           |38      |replace an assignment with a method call (same object used for the method call)                             |                  |       |
|            |                           |39      |assignemt replaced with a new assignment (everything the same, "this" removed)                              |                  |       |
|            |                           |40      |assignemt replaced with a new assignment (everything the same, "this" added)                                |                  |       |
|            |                           |41      |return expression replaced with null                                                                        |                  |       |
|            |                           |42      |empty                                                                                                       |                  |       |
|            |                           |43      |return expression replaced with the same method call, the argument is changed to null                       |                  |       |
|            |                           |44      |empty                                                                                                       |                  |       |
|            |                           |45      |return expression changed. a "-1" is added to the expression                                                |                  |       |
|            |                           |46      |return expression changed. method call changed (same method, different object)                              |                  |       |
|            |                           |47      |return expression changed. a "&255" is added to the expression                                              |                  |       |
|            |                           |48      |return expression changed. the same expression is added to itself                                           |                  |       |
|            |                           |49      |return expression changed. a "+1" is added to the expression                                                |                  |       |
|            |                           |50      |return expression changed. "? :" added, condition and else are null and 0 respectively.                     |                  |       |
|            |                           |51      |return expression changed. the same expression is reduced from itself                                       |                  |       |
|            |                           |52      |if condition changed. same expression, a classname is added before reading the static field                 |                  |       |
|            |                           |53      |if condition changed. oprator is changed, a classname is added before reading the static field              |                  |       |
|            |                           |54      |if condition changed, right operand changed to the same thing as the left operand                           |                  |       |
|            |                           |55      |if condition changed. same expression, left operand changed from a field read to call a method of that field|                  |       |
|            |                           |56      |if condition changed. same expression, same expression "&&" with itself                                     |                  |       |
|*719254693* |*featurecat*                 |        |                                                                                                            |Fix               |       |
|            |                           |1       |if condition changed, left operand changed from a variable to a new variable                                |                  |       |
|            |                           |2       |if condition changed, left operand changed from a variable to a method call. Operator changed from != to >  |                  |       |
|            |                           |3       |if condition changed, left operand changed from a variable to a method call.                                |                  |       |
|            |                           |4       |if condition changed, left operand changed from a variable to a new variable                                |                  |       |
|            |                           |5       |if condition changed, left operand changed from a variable to "-1"                                          |                  |       |
|            |                           |6       |if condition changed, left operand changed from a variable to "-1". Operator also changed                   |                  |       |
|            |                           |7       |if condition changed, left operand changed from a variable to a new variable                                |                  |  Yes  |
|            |                           |8       |if condition changed, left operand changed from a variable to a method call.                                |                  |       |
|            |                           |9       |if condition changed, left operand changed from a variable to a method call.                                |                  |       |
|*724421582* |*java-group-blr*             |        |                                                                                                            |                  |       |
|            |                           |1       |if condition changed, operator changed from == to !=                                                        |                  |       |
|            |                           |2       |if condition changed, operator changed from == to !=                                                        |                  |       |
|            |                           |3       |return expression changed, same variable is used in the new expression                                      |                  |       |
|*663865418* |*Luki42*                     |        |                                                                                                            |No fix yet        |       |
|            |                           |1       |assignment replaced with a new assignment (right hand is changed from method call to "1")                   |                  |       |
|            |                           |2       |assignment replaced with a new assignment (right hand is changed from method call to a new method call)     |                  |       |
|*723426709* |*RBMHTechnology*             |        |                                                                                                            |                  |       |
|            |                           |1       |if condition changed (a lot!)                                                                               |                  |       |
|            |                           |2       |if condition changed (a lot!)                                                                               |                  |       |
|            |                           |3       |if condition changed (argument of a function is changed from a constant to a variable previously used)      |                  |       |
|            |                           |4       |if condition changed (a lot!)                                                                               |                  |       |
|            |                           |5       |if condition changed (same method, object changed)                                                          |                  |       |
|            |                           |6       |return expression changed (a lot!)                                                                          |                  |       |
|            |                           |7       |return expression changed (same object and arguments, method changed)                                       |                  |       |
|*721491569* |*gattie-823-bescoto98_topush*|        |                                                                                                            |Fix               |       |
|            |                           |1       |try removed                                                                                                 |                  |       |
|*721498606* |*gattie-823-Tanisi90*        |        |                                                                                                            |No fix yet        |       |
|            |                           |1       |assignemnt changed (same thing, "this" removed")                                                            |                  |       |
|            |*taljmars*                   |        |                                                                                                            |                  |       |
|            |                           |1       |method call replaced with a new method call (a call is added to the end of expression)                      |                  |       |
|*723371918* |*vert-x3*                    |        |                                                                                                            |                  |       |
|            |                           |1       |assignment changed. Right hand changed to the same thing as the left one                                    |                  |       |
|            |                           |2       |assignment changed. Left hand changed to the same thing as the right one                                    |                  |       |
|            |                           |3       |empty                                                                                                       |                  |       |
|            |                           |4       |assignment changed. Left hand has become final. A static field is used by adding the class name before it   |                  |       |
|            |                           |5       |empty                                                                                                       |                  |       |
|            |                           |6       |if condition changed. not equal to null && before the old condition                                         |                  |       |
|            |                           |7       |if condition changed. "&&" with a variable added                                                            |                  |       |
|            |                           |8       |if condition changed. expression "&&" with itself                                                           |                  |       |
|            |                           |        |                                                                                                            |                  |       |
|*721436099* |*vlingo*                     |        |                                                                                                            |No fix yet        |       |
|            |                           |1       |return expression changed (argument changed from false to true)                                             |                  |       |
|*733093459* |*miso-lims*                  |        |                                                                                                            |No fix yet        |       |
|            |                           |1       |method call changed (arguments are swapped)                                                                 |                  |       |
|            |                           |2       |method call changed (second argument is also used as the first one)                                         |                  |       |
