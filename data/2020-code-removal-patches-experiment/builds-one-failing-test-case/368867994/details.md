# Information about the failure**:

| Failure type | Failure details | Failing test case | Changed files by jKali |
|--------------|-------------------|----------------------------|------------|
| java.lang.AssertionError | expected:<[vehicle_report] id = v1 time = 2 speed = 10 kilometrage = 10 faulty = 2 location = (r1,10) > but was:<[vehicle_report] id = v1 time = 2 speed = 0 kilometrage = 10 faulty = 2 location = (r1,10) >| [VehicleTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/af902a319926cfcbb772fbc6913f0a8112987129/src/test/java/pr5/tmodel/VehicleTest.java#L115) | [IniSection.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/af902a319926cfcbb772fbc6913f0a8112987129/src/main/java/pr5/ini/IniSection.java) or [Vehicle](https://github.com/repairnator/repairnator-experiments/blob/Inmapg-Text-Traffic-Simulator-368867994-20180419-235104/src/main/java/pr5/model/Vehicle.java)|

- **Human Patch**

```diff
From e59608185c3f326d46eb82371614d5ee354a9ba9 Mon Sep 17 00:00:00 2001
From: Inma <Inma@eduroam162180.eduroam.ucm.es>
Date: Thu, 19 Apr 2018 23:54:13 +0200
Subject: [PATCH] Fixing VehicleTest class

---
 src/test/java/pr5/tmodel/VehicleTest.java | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)

diff --git a/src/test/java/pr5/tmodel/VehicleTest.java b/src/test/java/pr5/tmodel/VehicleTest.java
index 2248632..ca127a5 100755
--- a/src/test/java/pr5/tmodel/VehicleTest.java
+++ b/src/test/java/pr5/tmodel/VehicleTest.java
@@ -109,7 +109,7 @@ public void VehicleFaultyTest(){
         assertEquals(correct, result);
         vehicle.makeFaulty(2);
         correct.setValue("time", "2");
-        correct.setValue("speed", "10");
+        correct.setValue("speed", "0");
         correct.setValue("faulty", "2");
         result = vehicle.generateReport(2);
         assertEquals(correct, result);
```

- **Overview**: the problem is related to the test case that checks the correct behavior of the [method](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/af902a319926cfcbb772fbc6913f0a8112987129/src/main/java/pr5/ini/IniSection.java#L159) changed by jKali. Indeed, looking at the commit history of the project, the developer [changed the test case](https://github.com/Inmapg/Traffic-Simulator/commit/e59608185c3f326d46eb82371614d5ee354a9ba9) to handle the failure during the comparison of two `Vehicle` objects.
- **Reason why the patches have been generated**: jKali managed to create the patches because the test case expects that two `IniSection` objects are equal, and since the patches avoid the execution of the `return false` instruction, the objects are always equal, even if they are different. For this reason, there isn't the possibilty to recognise the error in the current test case. It is necessary to add some other checks in the test case that require `false` as expected value (e.g., a check of the individual properties of the objects, after a change to one of them, without the use of method `equals`).
- **Useful information for the developer**: the code-removal patches suggest that there is a problem with the method `equals` or with the test case `VehicleFaultyTest` that checks the behavior of that method. Since it's correct that when two values are different the result of the comparison is false (this instruction has been removed by jKali to create the fix), the developer can focus on the implementation of the test case to try to understand where the error might be.
