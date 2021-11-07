# Information about the failure

| Error type   | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.lang.NullPointerException | [ConfigDataTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/5b498de0cfb7600a9f341d8efad2f1ee49a66eae/src/test/java/uk/ac/cam/groupprojects/bravo/config/ConfigDataTest.java#L24)| [ConfigData.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/5b498de0cfb7600a9f341d8efad2f1ee49a66eae/src/main/java/uk/ac/cam/groupprojects/bravo/config/ConfigData.java#L56)|


- **Overview**: The problem is related to an example config file that has to be parsed in the crashing test case. Indeed, looking at the commit history, there is [this commit](https://github.com/AudibleAppliances/AudibleAppliances/compare/e4a1a2b2f001...fd05ac127efd) that changes the file content, so that the program passes all test cases. There other changes associated with previous failing builds. Applying only the change related to the test data, the program passes all the test cases.

- **Reason why the patch has been generated**: jKali managed to create the code-removal patch because of the test suite. Indeed, adding for example an assert to check the value of attribute `width` related to the Box `ScreenBox.GRAPH`, the program patched with the code-removal doesn't pass the test cases. Indeed, jKali removed the instructions to parse the section of file related to the `ScreenBox`, and so these properties are not available.

- **Useful information for the developer**: The developer can understand that there is some problem with the for-cycle removed by jKali. If after checking that the parsing is correct, she can check the input file test data.
