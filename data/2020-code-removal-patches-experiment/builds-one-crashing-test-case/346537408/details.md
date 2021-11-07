# Information about the failure

| Error type   | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.lang.NullPointerException | [OutputTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/75c3f75c62b1415c6deb5bb32636bf9d054922a6/src/test/java/mars/OutputTest.java#L35)| [TerminalOutput.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/75c3f75c62b1415c6deb5bb32636bf9d054922a6/src/main/java/mars/out/TerminalOutput.java#L42)|

The code-removal patch works because of a weak test suite that does not correclty check the coordinates.
