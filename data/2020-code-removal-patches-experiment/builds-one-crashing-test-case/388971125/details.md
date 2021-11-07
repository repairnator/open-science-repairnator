# Information about the failure

| Error type   | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
|  java.lang.ClassCastException| [ToDoTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/cdb1e93489f4dc2d22a711c21602ff63ad3f0350/src/test/java/ToDoTest.java#L102)| [ToDo.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/cdb1e93489f4dc2d22a711c21602ff63ad3f0350/src/main/java/de/swtproject/doit/core/ToDo.java#L276)|

- **Overview**: The problem is related to the fact that there is cast to String in the test case, but it is not allowed to cast the object Priority to a String. This generates the exception.

- **Reason why the patch has been generated**: jKali managed to create a patch because the crashing test case is a rotten green test. Since the code-removal patch removed a property from the tested object and since the test checks the property only if the object has the property, this is the reason why the program with the code-removal patch passes all test cases.

- **Useful information for the developer**: Since the code-removal patch removes a specific property from the object, she can investigate if the value of the property is correct or not.
