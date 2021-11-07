# Information about the failure

| Error type   | Detail | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|---------|
| java.lang.IllegalArgumentException | Cannot find the FxCop report | [FxCopSensorTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/e9f68e4603f05bd9d6429bf63a65951a0ed4085e/src/test/java/org/sonar/plugins/fxcop/FxCopSensorTest.java#L192)| [FxCopSensor.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/e9f68e4603f05bd9d6429bf63a65951a0ed4085e/src/main/java/org/sonar/plugins/fxcop/FxCopSensor.java#L82)|

**Human fix**:

```diff
[From 8bb8cccd253f73d81a3120ec0f42bd6bd32f7a90 Mon Sep 17 00:00:00 2001
From: Daniel Wehrle <daniel.wehrle@haufe-lexware.com>
Date: Wed, 30 May 2018 15:20:32 +0200
Subject: Extend UnitTests

---
 src/test/java/org/sonar/plugins/fxcop/FxCopSensorTest.java | 5 ++---
 1 file changed, 2 insertions(+), 3 deletions(-)

diff --git a/src/test/java/org/sonar/plugins/fxcop/FxCopSensorTest.java b/src/test/java/org/sonar/plugins/fxcop/FxCopSensorTest.java
index b1d5143..489610d 100644
--- a/src/test/java/org/sonar/plugins/fxcop/FxCopSensorTest.java
+++ b/src/test/java/org/sonar/plugins/fxcop/FxCopSensorTest.java
@@ -173,10 +173,9 @@ public void testExecute() {
   
   @Test
   public void testExecuteImpl() {  
-	  if (System.getProperty("os.name").startsWith("Windows")) {
-		  thrown.expect(IllegalArgumentException.class);
+	    thrown.expect(IllegalArgumentException.class);
 	    thrown.expectMessage("Cannot find the FxCop report");
-	  }
+	  
 	  
     FxCopSensor sensor = new FxCopSensor(new FxCopConfiguration("foo", "foo-fxcop", "", "", "sonar.cs.fxcop.slnFile", "", "", "", "", "", "sonar.cs.fxcop.report"));
     SensorContext context = mock(SensorContext.class);]
```

- **Overview**: The problem is related to the fact that the program throws an `IllegalArgumentException` because a file is missing. However, this is the intended behavior, but the test case have not the assert to verify that the program throws the exception.

- **Reason why the patch has been generated**: The code-removal patch removed the instruction that makes the program throw the exception. jKali managed to create this patch because of an error in the test case. Indeed, looking at the [commit history of the project](https://github.com/DanielHWe/sonar-fxcop/compare/b6e30c4a50b9...8bb8cccd253f), the developer changed the test case, to fix the error.

- **Useful information for the developer**: Since the expected behavior is the throw of the exception, and jKali removed the instruction that throws the exception, th developer can understand that the error in is not in the program, but it the test case itself.
