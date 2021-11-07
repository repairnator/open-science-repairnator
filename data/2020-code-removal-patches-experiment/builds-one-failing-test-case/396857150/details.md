# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|------------|
| org.junit.ComparisonFailure | expected:<[a comment]> but was:<[]> | [PropertiesExtractionTest.java](https://github.com/repairnator/repairnator-experiments/blob/7c636fce09c8b9c1c595f96c73063e3ff4332257/domain/src/test/java/org/hesperides/domain/modules/commands/PropertiesExtractionTest.java#L44) | [Property.java](https://github.com/repairnator/repairnator-experiments/blob/7c636fce09c8b9c1c595f96c73063e3ff4332257/domain/src/main/java/org/hesperides/domain/templatecontainers/entities/Property.java#L127) |

- **Human Patch**: Not found

- **Overview**: The if statement changed by the code-removal patch makes the program avoid the execution of an empty if statement. In the if statement, there is only a [TODO](https://github.com/repairnator/repairnator-experiments/blob/7c636fce09c8b9c1c595f96c73063e3ff4332257/domain/src/main/java/org/hesperides/domain/templatecontainers/entities/Property.java#L127-L129).
- **Reason why the patch has been generated**: The patch works because the test case expects to have a comment, and the else branch (which execution is forced by the code-removal patch) allows to get a value for the comment. The test case should check if an exception is thrown (based on the TODO).
- **Useful information for the developer**: Since the code-removal forces the execution of a specific path that contains a TODO, the developer can focus on the TODO to understand which is the problem.
