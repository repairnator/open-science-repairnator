# Information about the ailure

Rottening test: https://github.com/dginelli/jkali-gzoltar-one-crashing/blob/e25acdd482f7bfbf1a2f9414cc85352529f0b561/src/test/java/ToDoTest.java#L101

- **Overview**: The test case checks if the object has the property interval and if the answer is yes, then it executes the assertEquals.

- **Reason why the patch has been generated**: The code-removal patch removes the instruction to add the property "interval", and thus the assert is not executed, and the test case passes.

- **Human fix**: https://api.travis-ci.org/v3/build/388978079

```diff
diff --git a/src/main/java/de/swtproject/doit/core/ToDo.java b/src/main/java/de/swtproject/doit/core/ToDo.java
index fc36c38..6fdb097 100644
--- a/src/main/java/de/swtproject/doit/core/ToDo.java
+++ b/src/main/java/de/swtproject/doit/core/ToDo.java
@@ -82,6 +82,7 @@
      */
     public ToDo(String title) {
         this.title = title;
+        this.interval = IntervalType.NONE;
         this.priority = Priority.DEFAULT;
     }
 
```
