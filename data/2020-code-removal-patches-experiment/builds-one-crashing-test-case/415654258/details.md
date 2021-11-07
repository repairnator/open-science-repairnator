# Information about the failure

| Error type   | Detail | Crashing test case | Changed file by jKali |
|--------------|--------|--------------------|----------------------------|
| java.lang.Exception | Test timed out after 1000 milliseconds | [ActorStopTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/20293c0ce2480e01a895012d696e254d0efa764c/src/test/java/io/vlingo/actors/ActorStopTest.java#L21)| [ConcurrentQueueMailbox.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/20293c0ce2480e01a895012d696e254d0efa764c/src/main/java/io/vlingo/actors/plugin/mailbox/concurrentqueue/ConcurrentQueueMailbox.java#L25)|

- **Reason why the patch has been generated**: The code-removal patch work because the test case is flaky. The flakiness is related to the timeout associated with the test case.
