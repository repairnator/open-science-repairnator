# Information about the failure

| Failure type | Failing test case | Changed file by AstorJKali |
|--------------|-------------------|----------------------------|
| java.lang.AssertionError | [HTTPServerTest.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/71c0bd6a1787ecf0b75534b73ddda4046cb6d220/api-server/src/test/java/io/enmasse/api/server/HTTPServerTest.java#L75) | [APIGroupVersion.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/71c0bd6a1787ecf0b75534b73ddda4046cb6d220/api-server/src/main/java/io/enmasse/api/v1/types/APIGroupVersion.java#L25)|

The code-removal patch works because the test is flaky.
