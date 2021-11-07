# Information about the failure

| Failure type | Failure details   |Failing test case           | Changed file by jKali |
|--------------|-------------------|----------------------------|------------------------------|
| java.lang.AssertionError | java.lang.AssertionError: expected:<4> but was:`<5>` | [TracingTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/ccfdce406eff9cd15ae478dc1c93989f596259fc/src/test/java/io/opentracing/contrib/hazelcast/TracingTest.java#L104) | [TracingEntryBackupProcessor.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/ccfdce406eff9cd15ae478dc1c93989f596259fc/src/main/java/io/opentracing/contrib/hazelcast/TracingEntryBackupProcessor.java#L47) or [TracingEntryProcessor](https://github.com/repairnator/repairnator-experiments/blob/9edf60a2e3ab81a72c727e9eaf20db7abeb02054/src/main/java/io/opentracing/contrib/hazelcast/TracingEntryProcessor.java#L53)|

- **Human Patch**:

```diff
diff --git a/src/test/java/io/opentracing/contrib/hazelcast/TracingTest.java b/src/test/java/io/opentracing/contrib/hazelcast/TracingTest.java
index 53574a9..e44b260 100644
--- a/src/test/java/io/opentracing/contrib/hazelcast/TracingTest.java
+++ b/src/test/java/io/opentracing/contrib/hazelcast/TracingTest.java
@@ -101,7 +101,7 @@ public void testEntryProcessor() {
     map.executeOnKey("key", new TestEntryProcessor());
     System.out.println("new value:" + map.get("key"));
     List<MockSpan> spans = tracer.finishedSpans();
-    assertEquals(4, spans.size());
+    assertEquals(5, spans.size());
     checkSpans(spans);
     assertNull(tracer.activeSpan());
   }
```

- **Overview**: the problem is related to the test case that checks the size of a List of MockSpan. Indeed, looking at the commit history of the project, the developer [changed the test case](https://github.com/opentracing-contrib/java-hazelcast/compare/df979e1e40d8...c9140902f4d9) to fix the bug. In particular, looking at the commit history of the [pull request](https://github.com/opentracing-contrib/java-hazelcast/pull/1/commits), the developer restored the previous version of the test case.

- **Reason why the patches have been generated**: The patches work because of an error in the test case.

- **Useful information for the developer**: The developer can exploit the code-removal patches to understand if there is a bug in test case or not.
