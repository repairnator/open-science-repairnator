# Information about the failure

| Error type   | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.io.FileNotFoundException | [TestParser.java]()| [FileParser.java]()|


**Human fix**:

```diff
From 9f6e0abf2210a832cbaf55b152904161bf841ac3 Mon Sep 17 00:00:00 2001
From: Robin Duda <robin.duda@idainfront.se>
Date: Sat, 28 Apr 2018 15:54:28 +0200
Subject: Renamed file used in test-case for invalid excel content.

---
 src/test/java/TestParser.java | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)

diff --git a/src/test/java/TestParser.java b/src/test/java/TestParser.java
index f632e03..f2654b0 100644
--- a/src/test/java/TestParser.java
+++ b/src/test/java/TestParser.java
@@ -18,7 +18,7 @@
 public class TestParser {
     public static final String TEST_XLSX_FILE = "src/test/java/test.xlsx";
     public static final String TEST_XLS_FILE = "src/test/java/test.xls";
-    public static final String TEST_INVALID_FILE = "src/test/java/test_invalid.xlsx";
+    public static final String TEST_INVALID_FILE = "src/test/java/invalid.xlsx";
     public static final int ROW_OFFSET = 5;
     private static final String XLSX = ".xlsx";
 ```

- **Reason why the patch has been generated**: The code removal patch works becuase of a bug in the test case.
- **Useful information for the developer**: The developer can exploit the code-removal patch to understand if there is a bug in test case or not.
