# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|-----------------|
| java.lang.AssertionError | expected:<2017-07-03T08:00+02:00[Europe/Stockholm]> but was:<2017-07-01T08:00+02:00[Europe/Stockholm]> | [Issue215Test.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/1e713a7c013058d5e930ce89b6f83b94251c3b29/src/test/java/com/cronutils/Issue215Test.java#L29) | [BetweenDayOfWeekValueGenerator.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/1e713a7c013058d5e930ce89b6f83b94251c3b29/src/main/java/com/cronutils/model/time/generator/BetweenDayOfWeekValueGenerator.java#L64)|

- **Human Patch**

```diff
diff --git a/src/main/java/com/cronutils/model/time/generator/BetweenDayOfWeekValueGenerator.java b/src/main/java/com/cronutils/model/time/generator/BetweenDayOfWeekValueGenerator.java
index dc392e0..1b71cc0 100644
--- a/src/main/java/com/cronutils/model/time/generator/BetweenDayOfWeekValueGenerator.java
+++ b/src/main/java/com/cronutils/model/time/generator/BetweenDayOfWeekValueGenerator.java
@@ -125,6 +125,19 @@ public int generatePreviousValue(int reference) throws NoSuchValueException {
 
 	@Override
 	public boolean isMatch(int value) {
-        return dowValidValues.contains(LocalDate.of(year, month, value).getDayOfWeek().getValue());
+        // DayOfWeek getValue returns 1 (Monday) - 7 (Sunday),
+        // so we should factor in the monday DoW used to generate
+        // the valid DoW values
+        int localDateDoW = LocalDate.of(year, month, value).getDayOfWeek().getValue();
+
+        // Sunday's value is mondayDoWValue-1 when generating the valid values
+        // Ex.
+        // cron4j 0(Sun)-6(Sat), mondayDoW = 1
+        // quartz 1(Sun)-7(Sat), mondayDoW = 2
+
+        // modulo 7 to convert Sunday from 7 to 0 and adjust to match the mondayDoWValue
+        int cronDoW = localDateDoW % 7 + (mondayDoWValue.getMondayDoWValue() - 1);
+
+        return dowValidValues.contains(cronDoW);
 	}
 }
```

- **Overview**: the problem is related to the implementation of the method [`isMatch`](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/1e713a7c013058d5e930ce89b6f83b94251c3b29/src/main/java/com/cronutils/model/time/generator/BetweenDayOfWeekValueGenerator.java#L127). Indeed, looking at the [commit history of the project](https://github.com/pangyikhei/cron-utils/commit/bdd688a3fb01b7120c240fdd7bb2a96410a0881d), the developer changed this method to fix the bug. The test case fails because the expected date and the next date generated with the method [`nextExecution`](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/1e713a7c013058d5e930ce89b6f83b94251c3b29/src/main/java/com/cronutils/model/time/ExecutionTime.java#L142) are not equal.
