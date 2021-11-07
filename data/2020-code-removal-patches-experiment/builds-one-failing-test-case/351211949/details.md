# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|-----------------|
| java.lang.AssertionError | - | [DefaultCommandRunnerTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/fe9d8b810c7588a47bfe920c51118545d3e563b8/core/src/test/java/com/github/swissquote/cartnotzet/core/runtime/DefaultCommandRunnerTest.java#L36) | [DefaultCommandRunner.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/fe9d8b810c7588a47bfe920c51118545d3e563b8/core/src/main/java/com/github/swissquote/carnotzet/core/runtime/DefaultCommandRunner.java#L90)|

- **Human Patch**:

```diff
diff --git a/core/src/test/java/com/github/swissquote/cartnotzet/core/runtime/DefaultCommandRunnerTest.java b/core/src/test/java/com/github/swissquote/cartnotzet/core/runtime/DefaultCommandRunnerTest.java
index 88fb2d0..a2bf241 100644
--- a/core/src/test/java/com/github/swissquote/cartnotzet/core/runtime/DefaultCommandRunnerTest.java
+++ b/core/src/test/java/com/github/swissquote/cartnotzet/core/runtime/DefaultCommandRunnerTest.java
@@ -33,7 +33,7 @@ public void test_large_output() throws Exception {
 				2, TimeUnit.SECONDS, true);
 
 		// Then
-		Assert.assertThat(actual, Is.is(expected));
+		Assert.assertThat(actual, Is.is(expected.trim()));
 	}
 
 }
 ```
 
- **Overview**: The build fails after introducing an instruction to trim the output, but the test case has not been updtated to support this change.
- **Reason why the patch has been generated**: jKali manages to create a code-removal patch because it removes exactly the instruction that makes the build fail.
