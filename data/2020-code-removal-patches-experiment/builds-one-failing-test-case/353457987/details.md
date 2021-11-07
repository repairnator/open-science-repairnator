# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|---------------------|
| org.mockito.exceptions.verification.TooLittleActualInvocations | processor.process("k1"); Wanted 2 times:-> at io.enmasse.k8s.api.cache.FifoQueueTest.testRemove(FifoQueueTest.java:64) But was 1 time: -> at io.enmasse.k8s.api.cache.FifoQueue.pop(FifoQueue.java:65)|[FifoQueueTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/733c76e58890cea2d4ce004760de719ae04ca826/k8s-api/src/test/java/io/enmasse/k8s/api/cache/FifoQueueTest.java#L64) | [FifoQueue.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/733c76e58890cea2d4ce004760de719ae04ca826/k8s-api/src/main/java/io/enmasse/k8s/api/cache/FifoQueue.java#L54)|

- **Human Patch**

```diff
From 848ff42b0ed3fa5888778957ac8daca909c98072 Mon Sep 17 00:00:00 2001
From: Ulf Lilleengen <lulf@redhat.com>
Date: Wed, 14 Mar 2018 20:29:54 +0100
Subject: Fix test to handle drain

---
 .../src/test/java/io/enmasse/k8s/api/cache/FifoQueueTest.java   | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)

diff --git a/k8s-api/src/test/java/io/enmasse/k8s/api/cache/FifoQueueTest.java b/k8s-api/src/test/java/io/enmasse/k8s/api/cache/FifoQueueTest.java
index 213c76fef1..ec7b7fda7f 100644
--- a/k8s-api/src/test/java/io/enmasse/k8s/api/cache/FifoQueueTest.java
+++ b/k8s-api/src/test/java/io/enmasse/k8s/api/cache/FifoQueueTest.java
@@ -61,7 +61,7 @@ public void testRemove() throws Exception {
         assertTrue(queue.list().isEmpty());
 
         queue.pop(mockProc, 0, TimeUnit.SECONDS);
-        verify(mockProc, times(2)).process(eq("k1"));
+        verify(mockProc).process(eq("k1"));
         assertTrue(queue.listKeys().isEmpty());
         assertTrue(queue.list().isEmpty());
     }
```

- **Overview**: the problem is related to the test case that checks the correct behavior of the [method](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/733c76e58890cea2d4ce004760de719ae04ca826/k8s-api/src/main/java/io/enmasse/k8s/api/cache/FifoQueue.java#L46) changed by jKali. Indeed, looking at the commit history of the project, the developer [changed the test case](https://github.com/EnMasseProject/enmasse/pull/1058/commits/848ff42b0ed3fa5888778957ac8daca909c98072) to handle the failure associated with the use of method `draintTo`, that is the method removed by jKali to create the patch.
- **Reason why the patch has been generated**: jKali managed to generate a patch because the test case only checks if the FifoQueue object is empty or not, but it doesn't check the inner properties of that object. In particular, it doesn't check the size of the private member variable `queue`. Thus, removing the call to the method `drainTo` in the method `pop` (as jKali did), the queue `queue` is not really emptied, and so the method `pop` is executed two times (even if the queue should have been emptied the first time) satisfying the test case that checks how many times that method is called.
- **Useful information for the developer**: the code-removal patch suggests that there is a problem with the method `drainTo` or with the test case `testRemove` that checks the behavior of that method. In this case, since the method `drainTo` is a method of the JDK, there is more probability that the error is not related to that method, but in the way used to test it. Thus, the developer can start to analyze the problem focusing on the test case, checking if it is correct or not.
