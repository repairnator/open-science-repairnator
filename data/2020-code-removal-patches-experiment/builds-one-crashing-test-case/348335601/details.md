# Information about the failure

| Error type   | Erroring test case | Changed file by AstorJKali |
|--------------|-------------------|----------------------------|
| java.lang.NullPointerException | [ConfigDataTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/fb3a63d240bfa762dc244bee6a77d3801d24f5f6/src/test/java/uk/ac/cam/groupprojects/bravo/config/ConfigDataTest.java#L24)| [ConfigData.json](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/fb3a63d240bfa762dc244bee6a77d3801d24f5f6/src/main/java/uk/ac/cam/groupprojects/bravo/config/ConfigData.java#L56)|

**Human fix**:

```diff
diff --git a/src/test/resources/testConfig.json b/src/test/resources/testConfig.json
index 2737f0f..b363d95 100644
--- a/src/test/resources/testConfig.json
+++ b/src/test/resources/testConfig.json
@@ -61,77 +61,77 @@
 				"width": 10,
 				"height": 20
 			},
-			"text_box_1": {
+			"lcd_text_1": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_2": {
+			"lcd_text_2": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_3": {
+			"lcd_text_3": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_4": {
+			"lcd_text_4": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_5_top": {
+			"lcd_text_5_top": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_5_bottom": {
+			"lcd_text_5_bottom": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_6": {
+			"lcd_text_6": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_7_top": {
+			"lcd_text_7_top": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_7_bottom": {
+			"lcd_text_7_bottom": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_8": {
+			"lcd_text_8": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_9_top": {
+			"lcd_text_9_top": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_9_bottom": {
+			"lcd_text_9_bottom": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_10": {
+			"lcd_text_10": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_11": {
+			"lcd_text_11": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
 			},
-			"text_box_12": {
+			"lcd_text_12": {
 				"corner": [20, 12],
 				"width": 10,
 				"height": 20
```

The code-removal patch works because of an error in the data used by the test case.
