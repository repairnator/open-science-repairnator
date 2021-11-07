# Information about the failure

| Failure type | Failure details |Failing test case | Changed file by jKali |
|--------------|--------------- |----|----------------------------|
| java.lang.AssertionError | expected:<12> but was:<13> |[ItemTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/22fa712df21b7215a3596cfc42e56cc85154f309/src/test/java/fr/esiea/ItemTest.java#L35) | [BackstagePassesItem.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/22fa712df21b7215a3596cfc42e56cc85154f309/src/main/java/fr/esiea/BackstagePassesItem.java#L19)|

- **Human Patch**

```diff
diff --git a/src/test/java/fr/esiea/ItemTest.java b/src/test/java/fr/esiea/ItemTest.java
index c9d6737..ec41dfa 100644
--- a/src/test/java/fr/esiea/ItemTest.java
+++ b/src/test/java/fr/esiea/ItemTest.java
@@ -32,7 +32,7 @@ public void updateItemTest(){
      		items[i].updateQuality();
      	assertEquals(12, items[0].quality);
     	assertEquals(22, items[1].quality);
-   		assertEquals(12, items[2].quality);
+   		assertEquals(13, items[2].quality);
     	assertEquals(0, items[3].quality);
     	assertEquals(0, items[4].quality);
     }
```

- **Overview**: the problem is related to the test case that checks the correct behavior of the method [updateQuality](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/22fa712df21b7215a3596cfc42e56cc85154f309/src/main/java/fr/esiea/BackstagePassesItem.java#L11). Indeed, in a [later commit](https://github.com/ryhita/gilded-rose/compare/9dbd8ad8bcd8...11f8e4370ec5), the developer changed the test case to fix the problem. In the commit associated with the failing build, the developer changed two values of the test case that failed, and one of this has been changed in the commit that fixed the bug.
- **Reason why the patches have been generated**: jKali manages to create the patches because the failing test case has an error in the comparison of the `quality` values. Since the patches avoid the execution of a piece of code that increments the value of that property, during the comparison the value got by the method `updateQuality` is the same of the one that the test case expects, and so the test case passes. 
- **Useful information for the developer**: the developer can focus on the method `updateQuality` and checks if it is correct or not, and she can also check if the test that verifies its correctness is correct. Moreover, since the failing commit had only changes in the test case that then started to fail, she knows directly which is the test case that should be verified.
