# Information about the failure

| Failure type | Failure details | Failure details    | Failing test case | Changed file by jKali |
|--------------|-----------|-------------------|----------------------------|---------------------------|
| java.lang.AssertionError | Expected exception: com.martiansoftware.jsap.ParseException | Expected exception: com.martiansoftware.jsap.ParseException| [ScnlParserTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/7a102271bb4afef361762612372dc520fdfc91fb/src/test/java/gov/usgs/volcanoes/core/args/parser/ScnlParserTest.java#L67) | [Scnl.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/7a102271bb4afef361762612372dc520fdfc91fb/src/main/java/gov/usgs/volcanoes/core/data/Scnl.java#L150)|

- **Human Patch**:

```diff
diff --git a/src/test/java/gov/usgs/volcanoes/core/args/parser/ScnlParserTest.java b/src/test/java/gov/usgs/volcanoes/core/args/parser/ScnlParserTest.java
index 0b603de6..2eab1105 100644
--- a/src/test/java/gov/usgs/volcanoes/core/args/parser/ScnlParserTest.java
+++ b/src/test/java/gov/usgs/volcanoes/core/args/parser/ScnlParserTest.java
@@ -2,13 +2,11 @@
 
 import static org.junit.Assert.assertEquals;
 
-import org.junit.Test;
-
 import com.martiansoftware.jsap.ParseException;
 
-import gov.usgs.volcanoes.core.args.parser.ScnlParser;
 import gov.usgs.volcanoes.core.data.Scnl;
-import gov.usgs.volcanoes.core.data.ScnlTest;
+
+import org.junit.Test;
 
 /**
  * 
@@ -65,6 +63,6 @@ public void when_givenScn_return_scn() throws ParseException {
    */
   @Test(expected = ParseException.class)
   public void when_givenBadScnl_then_throwHelpfulException() throws ParseException {
-    parser.parse("not a SCNL");
+    parser.parse("not-a-SCNL");
   }
 }
 ```
 
- **Overview**: The test case fails because it expects an execption, but it doesn't occur. The test case fails after the developer [introduced a piece of code to handle cases when delimiter is a space](https://github.com/usgs/volcano-core/pull/81/commits/1769466b7484a0b60090da003f5069cff2fb7a74). Since, she didn't update the test case to support this new feature, the test case fails. Indeed, looking at the commit history of the project, the developer [changed the test case](https://github.com/usgs/volcano-core/pull/81/commits/055a3c1f1af7db2b625e435b969123a57c7f628b) to fix the problem.
- **Reason why the patch have been generated**: jKali manages to create the patches because they avoid the execution of the `return instruction` introduced by developer in the last commit to handle the case in which the space is a delimiter. In this way, the program doesn't actually test the input with space as delimiter, but [it uses the `$` delimiter already present in the previous version of the program](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/7a102271bb4afef361762612372dc520fdfc91fb/src/main/java/gov/usgs/volcanoes/core/data/Scnl.java#L152). Since the input string doesn't contain the symbol `$`, the program enters in [this branch](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/7a102271bb4afef361762612372dc520fdfc91fb/src/main/java/gov/usgs/volcanoes/core/data/Scnl.java#L170), and it throws the exception, making passing the test case.
- **Useful information for the developer**: the code-removal patches avoid the execution of the return statement added in the commit that changed the status of build from `passing` to `failing`. Since this `return` statement contains [the call to the parsing method using space ad delimiter](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/7a102271bb4afef361762612372dc520fdfc91fb/src/main/java/gov/usgs/volcanoes/core/data/Scnl.java#L150), the developer can check if the problem is related to the logic of method that doesn't manage property this case or if the problem is in the input of the test case.
