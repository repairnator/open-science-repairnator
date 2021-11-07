# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by AstorJKali |
|--------------|-------------------|----------------------------|-------------------|
| java.lang.AssertionError | - | [NakedSubsetTest.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/a6fb43806c5e0d1be616fed9f90e609e0f8987f0/src/test/java/NakedSubsetTest.java#L15) | [NakedSubset.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/a6fb43806c5e0d1be616fed9f90e609e0f8987f0/src/main/java/org/mistahmilla/sudoku/solvers/NakedSubset.java#L37)|

- **Human Patch**:

```diff
From 229c47bc92a84129548f5e079de9c10126dde064 Mon Sep 17 00:00:00 2001
From: Matt <miller@cycop.org>
Date: Sat, 28 Apr 2018 15:19:20 -0400
Subject: NakedSubset bug fix

---
 .../java/org/mistahmilla/sudoku/solvers/NakedSubset.java | 9 +++++----
 1 file changed, 5 insertions(+), 4 deletions(-)

diff --git a/src/main/java/org/mistahmilla/sudoku/solvers/NakedSubset.java b/src/main/java/org/mistahmilla/sudoku/solvers/NakedSubset.java
index 1fed9fb..cc7f3c7 100644
--- a/src/main/java/org/mistahmilla/sudoku/solvers/NakedSubset.java
+++ b/src/main/java/org/mistahmilla/sudoku/solvers/NakedSubset.java
@@ -19,13 +19,14 @@ public void eliminate(){
         //go through each square and identify squares that have the same
         for (int a = 1; a <=9; a++){
             for(int b = 1; b<=9; b++){
+
                 if (a!=b) {
                     for (int i = 0; i < bs.length; i++) {
                         squares = new char[9][9];
                         section = bs[i];
                         count = 0;
-                        for (int x = section.getMinX(); x < section.getMaxX(); x++) {
-                            for (int y = section.getMinY(); y < section.getMaxY(); y++) {
+                        for (int x = section.getMinX(); x <= section.getMaxX(); x++) {
+                            for (int y = section.getMinY(); y <= section.getMaxY(); y++) {
                                 if (board.getSquare(x, y).getPossibleValues().contains(Integer.valueOf(a))
                                         && board.getSquare(x, y).getPossibleValues().contains(Integer.valueOf(b))
                                         && board.getSquare(x, y).getPossibleValues().size() == 2) {
@@ -35,8 +36,8 @@ public void eliminate(){
                             }
                         }
                         if (count >= 2) {
-                            for (int x = section.getMinX(); x < section.getMaxX(); x++) {
-                                for (int y = section.getMinY(); y < section.getMaxY(); y++) {
+                            for (int x = section.getMinX(); x <= section.getMaxX(); x++) {
+                                for (int y = section.getMinY(); y <= section.getMaxY(); y++) {
                                     if (squares[x][y] != 'x') {
                                         board.getSquare(x, y).removePossibleValue(a);
                                         board.getSquare(x, y).removePossibleValue(b);
```
