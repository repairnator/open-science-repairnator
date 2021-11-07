# Information about the failure

| Error type   | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| org.springframework.boot.context.embedded.<br>tomcat.ConnectorStartFailedException | [MainControllerTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-erroring-test-case/blob/742d559025c5ae3085be6b91de987d3743064be9/src/test/java/agent/MainControllerTest.java#L56) | [Application.java](https://github.com/repairnator/repairnator-experiments-jkali-one-erroring-test-case/blob/742d559025c5ae3085be6b91de987d3743064be9/src/main/java/agent/Application.java#L14)|

**Human fix**: 

```diff
From 48d727bc10387132621bc51aa0aff14567a2c962 Mon Sep 17 00:00:00 2001
From: PabloSuaGar <uo250924@uniovi.es>
Date: Tue, 10 Apr 2018 12:17:55 +0200
Subject: Removed invocation of Spring application on MainController test
diff --git a/src/test/java/agent/MainControllerTest.java b/src/test/java/agent/MainControllerTest.java
index 7d0ac71..2ee3c25 100644
--- a/src/test/java/agent/MainControllerTest.java
+++ b/src/test/java/agent/MainControllerTest.java
@@ -41,7 +41,6 @@
     public void setUp() throws Exception {
         this.base = new URL("http://localhost:" + port + "/");
         template = new TestRestTemplate();
-        Application.main(new String[0]);
         mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
     }
```

**Overview**: The problem is related to the invocation of Spring application in MainController test class, that calls directly the method `Application.main()`. 

**Reason why the patch has been generated**: jKali is able to generate the patch because its action (deletion of the body in the method `Application.main()`) is semantically equivalent to the human fix. Indeed, the developer removed the call to the method Application.main from the test case to fix the error. After found online some possible methods to test the Spring Boot Application.main(), the program 
with the code-removal patch still passes the test cases. Thus, the reason why jKali managed to create 
a patch is not related to a weak test suite. Moreover, the main method is not required for Spring Boot applications, but it can be used to simplify the exection of the application from within an IDE (https://stackoverflow.com/a/29791224/4255576).

**Useful information for the developer**: The developer can check if there is something wrong with the method main(). Since the method is correct, she can focus on the test case.

