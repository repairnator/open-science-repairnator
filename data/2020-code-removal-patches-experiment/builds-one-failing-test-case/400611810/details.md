# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|-----------------|
| org.opentest4j.AssertionFailedError | expected: <200> but was: <302> | [TestRequest.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/12171dada351fd2bfe999f8dd10cb0931829b5fb/src/test/java/com/http/TestRequest.java#L17) | [Request.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/12171dada351fd2bfe999f8dd10cb0931829b5fb/src/main/java/com/http/Request.java)|

- **Human Patch**:

```diff
From 35e068c4c53f25ee8df2108b02ee4b8b9cba27f9 Mon Sep 17 00:00:00 2001
From: jguyet <jguyet@student.42.fr>
Date: Thu, 5 Jul 2018 22:52:53 +0200
Subject: Update google test return code 302

---
 src/test/java/com/http/TestRequest.java | 2 +-
 1 file changed, 1 insertion(+), 1 deletion(-)

diff --git a/src/test/java/com/http/TestRequest.java b/src/test/java/com/http/TestRequest.java
index a0d5b94..b1ada21 100644
--- a/src/test/java/com/http/TestRequest.java
+++ b/src/test/java/com/http/TestRequest.java
@@ -14,7 +14,7 @@ public void testStatusOK() {
  		
  		r.setGET().setUrl("http://www.google.com").setProtocolHttp().setDefaultHeader();
  		r.execute();
- 		assertEquals(200, r.getStatusCode());
+ 		assertEquals(302, r.getStatusCode());
  	}
 	
 	@Test
```

- **Overview**: The problem is related to a failure in the test case. Indeed, looking at the commit history of the project, the developer [changed the test case](https://github.com/jguyet/HttpRequest/compare/81927710850c...35e068c4c53f) to fix the problem.
- **Reason why the patches have been generated**: jKali managed to create the patches because the test case doesn't check if the header is empty or not. Indeed, adding for example this instruction `assertFalse(r.getHeader().isEmpty());` in the test method [`testStatusOK`](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/12171dada351fd2bfe999f8dd10cb0931829b5fb/src/test/java/com/http/TestRequest.java#L11) the patch generated from jKali do not work.
- **Useful information for the developer**: The patches indicate that there is something strange with the header tested in the test case. If the developer knows that the test case is correct, she can focus on the test case to understand if it is correct or not.
