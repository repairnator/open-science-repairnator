# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|------------------|
| org.junit.ComparisonFailure | expected:<...ss.a()', '1', '2'); []> but was:<...ss.a()', '1', '2'); [ #Failing test case: de.tum.in.niedermr.ta.sample.junit.SampleJUnitTestClass;testCase2]> | [SurefireTestListenerTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/1b63f1b13f9ceae558c20cb8752636502f2846e8/test-analyzer-sdist/src/test/java/de/tum/in/niedermr/ta/extensions/analysis/workflows/stackdistance/maven/AbstractSurefireTestListenerTest.java#L48) | [AbstractSurefireTestListener.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/1b63f1b13f9ceae558c20cb8752636502f2846e8/test-analyzer-sdist/src/main/java/de/tum/in/niedermr/ta/extensions/analysis/workflows/stackdistance/maven/AbstractSurefireTestListener.java#L128)|

- **Human Patch**:
[https://github.com/cqse/test-analyzer/compare/901b08fede34...c3fee55007d9](https://github.com/cqse/test-analyzer/compare/901b08fede34...c3fee55007d9)

```diff
diff --git a/test-analyzer-sdist/src/main/java/de/tum/in/niedermr/ta/extensions/analysis/workflows/stackdistance/maven/SurefireSqlTestListener.java b/test-analyzer-sdist/src/main/java/de/tum/in/niedermr/ta/extensions/analysis/workflows/stackdistance/maven/SurefireSqlTestListener.java
index d3022047..ef086412 100644
--- a/test-analyzer-sdist/src/main/java/de/tum/in/niedermr/ta/extensions/analysis/workflows/stackdistance/maven/SurefireSqlTestListener.java
+++ b/test-analyzer-sdist/src/main/java/de/tum/in/niedermr/ta/extensions/analysis/workflows/stackdistance/maven/SurefireSqlTestListener.java
@@ -27,7 +27,7 @@ protected String getDefaultOutputFileExtension() {
 	/** {@inheritDoc} */
 	@Override
 	protected void writeCommentToResultFile(IResultReceiver resultReceiver, String comment) {
-		resultReceiver.append("#" + comment);
+		resultReceiver.append("# " + comment);
 	}
 
 	/** {@inheritDoc} */
diff --git a/test-analyzer-sdist/src/test/data/SurefireTestListenerTest/expected-output.txt b/test-analyzer-sdist/src/test/data/SurefireTestListenerTest/expected-output.txt
index 6d3a65bd..2536406b 100644
--- a/test-analyzer-sdist/src/test/data/SurefireTestListenerTest/expected-output.txt
+++ b/test-analyzer-sdist/src/test/data/SurefireTestListenerTest/expected-output.txt
@@ -1,5 +1,6 @@
-#INFO Stack distance setup successful.
+# INFO Stack distance setup successful.
 INSERT INTO Stack_Info_Import (execution, testcase, method, minStackDistance, invocationCount) VALUES ('????', 'de.tum.in.niedermr.ta.sample.junit.SampleJUnitTestClass;testCase1', 'de.tum.in.niedermr.ta.sample.SampleClass.c(java.lang.String)', '4', '2'); 
 INSERT INTO Stack_Info_Import (execution, testcase, method, minStackDistance, invocationCount) VALUES ('????', 'de.tum.in.niedermr.ta.sample.junit.SampleJUnitTestClass;testCase1', 'de.tum.in.niedermr.ta.sample.SampleClass.b(boolean)', '3', '1'); 
 INSERT INTO Stack_Info_Import (execution, testcase, method, minStackDistance, invocationCount) VALUES ('????', 'de.tum.in.niedermr.ta.sample.junit.SampleJUnitTestClass;testCase1', 'de.tum.in.niedermr.ta.sample.SampleClass.a(int,int)', '2', '1'); 
 INSERT INTO Stack_Info_Import (execution, testcase, method, minStackDistance, invocationCount) VALUES ('????', 'de.tum.in.niedermr.ta.sample.junit.SampleJUnitTestClass;testCase1', 'de.tum.in.niedermr.ta.sample.SampleClass.a()', '1', '2'); 
+# Failing test case: de.tum.in.niedermr.ta.sample.junit.SampleJUnitTestClass;testCase2
\ No newline at end of file
```

- **Overview**: The problem is related to the comparison between the value returned by a test case and a .txt file with the expected output.
- **Reason why the patch has been generated**: jKali managed to create the patch because it removes the instruction that adds the line missing in the expected output. In this way, the content of the file and the expected output are equal. Adding a new test case that checks if the output contains a line with an exception, it would avoid the generation of the code-removal patch.

- **Travis CI Fixed Build information**: [https://api.travis-ci.org/v3/build/397848105](https://api.travis-ci.org/v3/build/397848105)
