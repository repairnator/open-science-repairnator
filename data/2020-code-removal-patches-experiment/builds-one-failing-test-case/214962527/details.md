# Information about the failure

| Failure type | Failing test case | Changed file by AstorJKali |
|--------------|-------------------|----------------------------|
| java.lang.AssertionError | [TestSearchesNfa.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/c1030c0d5a7d02ec040004b75ef067afe51d0f2f/core/src/test/java/nl/inl/blacklab/search/fimatch/TestSearchesNfa.java#L64) | [BLSpanOrQuery.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/c1030c0d5a7d02ec040004b75ef067afe51d0f2f/core/src/main/java/nl/inl/blacklab/search/lucene/BLSpanOrQuery.java#L586)|

The code-removal patch works because the test case is flaky.
