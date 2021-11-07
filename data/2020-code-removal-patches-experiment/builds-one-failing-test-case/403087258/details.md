# Information about the failure

| Failure type | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.lang.AssertionError| [ContinuousSamplerParametricTest.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/57fe5c9531d90f7d21b67a302248dc1a7791eacf/commons-rng-sampling/src/test/java/org/apache/commons/rng/sampling/distribution/ContinuousSamplerParametricTest.java#L53) | [RejectionInversionZipfSampler.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/57fe5c9531d90f7d21b67a302248dc1a7791eacf/commons-rng-sampling/src/main/java/org/apache/commons/rng/sampling/distribution/RejectionInversionZipfSampler.java#L60-L62)|

- **Overview**: It's a flaky test case. The test case fails when the method `check()` of class `ContinuousSamplerParametricTest` enters in [this if branch](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/7d5cad3df578cf7467be89478ede91b74d684ed7/commons-rng-sampling/src/test/java/org/apache/commons/rng/sampling/distribution/ContinuousSamplerParametricTest.java#L127). The condition is satisfied when the ratio between the number of failures and the number of tests is greater then a certain threshold (0.05).
- **Reason why the patch has been generated**: Since the code-removal patch removes the instruction that throws an exception, even though a test case should terminate with a failure, actually it doesn't occur, and so the number of failing test cases doesn't increment. 

The test case fails only when a certain condition is satisifed. Since the code-removal patch avoids the increment of the number of exceptions thrown, the test case doesn't fail anymore.

- **Useful information for the developer**: It's a flaki test, so if the developer finds out that the implementation is correct both with and without the code-removal patch, she can try to understand how to change the test case, or remove it.
