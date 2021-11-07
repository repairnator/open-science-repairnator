# Information about the failure

| Error type   | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.lang.RuntimeException | [MetaDomainTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/77892bc487b9f56c1b4129aaa42a2a631c9467a2/apollo-core/src/test/java/com/ctrip/framework/apollo/core/MetaDomainTest.java#L12)| [NetUtil.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/77892bc487b9f56c1b4129aaa42a2a631c9467a2/apollo-core/src/main/java/com/ctrip/framework/apollo/core/utils/NetUtil.java#L41)|

- **Overview**: The changes that introduce the bug have been reverted, while the corresponding code-removal patch avoids the execution of one of the paths of the new faulty method introduced by developers, indicating a partial relation between human and automated fix.
