# Information about the failure

| Error type   | Details | Crashing test case | Changed file by jKali |
|--------------|----------|---------|----------------------------|
| java.lang.Exception | test timed out after 120000 milliseconds | [ZKClientTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/b810d2347ec6054de28ef2d7637222047f1b1cec/twill-zookeeper/src/test/java/org/apache/twill/zookeeper/ZKClientTest.java#L347)| [InMemoryZKServer.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/b810d2347ec6054de28ef2d7637222047f1b1cec/twill-zookeeper/src/main/java/org/apache/twill/internal/zookeeper/InMemoryZKServer.java#L105)|

**Human fix**: [https://github.com/apache/twill/compare/8f70aa4d4924...ee4d13701b21](https://github.com/apache/twill/compare/8f70aa4d4924...ee4d13701b21)

The human fix and the code-removal patch are partially related. In this case, the code-removal patch avoids the execution of the if branch that is changed by developers.

The code-removal patch works because of a weak test suite.
