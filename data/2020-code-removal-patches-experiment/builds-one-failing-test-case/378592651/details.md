# Information about the failure

| Failure type | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.lang.AssertionError | [ArtemisTest.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/264c0730fa98673eb06a24fe0c28c8398cf77cdf/amqp-utils/src/test/java/io/enmasse/amqp/ArtemisTest.java#L57) | [Artemis.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/264c0730fa98673eb06a24fe0c28c8398cf77cdf/amqp-utils/src/main/java/io/enmasse/amqp/Artemis.java#L103)|

- **Reason why the patch has been generated**: The code-removal patch works because it avoids to execute the instruction ```promise.fail(h.cause())``` that increments the counter of failures. In this way, since the test case is rottening, the program passes the test cases.
