# Information about the failure

| Error type   | Crashing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.lang.NullPointerException | [SentryAppenderFactoryTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/f84870cfada527e1516718723c3698bb6942fb89/src/test/java/org/dhatim/dropwizard/sentry/logging/SentryAppenderFactoryTest.java#L49)| [SentryAppenderFactory.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/f84870cfada527e1516718723c3698bb6942fb89/src/main/java/org/dhatim/dropwizard/sentry/logging/SentryAppenderFactory.java#L151)|

- **Human Patch**

```diff
From 7e2b50b3acc662f84b2a55aa46cfbaaecb8f840f Mon Sep 17 00:00:00 2001
From: =?UTF-8?q?Olivier=20Ch=C3=A9dru?= <ochedru@dhatim.com>
Date: Fri, 1 Jun 2018 17:40:06 +0200
Subject: [PATCH] Fix test

---
 .../sentry/logging/SentryAppenderFactoryTest.java           | 6 +++---
 1 file changed, 3 insertions(+), 3 deletions(-)

diff --git a/src/test/java/org/dhatim/dropwizard/sentry/logging/SentryAppenderFactoryTest.java b/src/test/java/org/dhatim/dropwizard/sentry/logging/SentryAppenderFactoryTest.java
index 8dee656..7b33490 100644
--- a/src/test/java/org/dhatim/dropwizard/sentry/logging/SentryAppenderFactoryTest.java
+++ b/src/test/java/org/dhatim/dropwizard/sentry/logging/SentryAppenderFactoryTest.java
@@ -42,11 +42,11 @@ public void buildSentryAppenderShouldFailWithNullContext() {
 
     @Test
     public void buildSentryAppenderShouldWorkWithValidConfiguration() {
-        final SentryAppenderFactory factory = new SentryAppenderFactory();
-        final String dsn = "https://user:pass@app.sentry.io/id";
+        SentryAppenderFactory factory = new SentryAppenderFactory();
+        factory.setDsn("https://user:pass@app.sentry.io/id");
 
         Appender<ILoggingEvent> appender
-                = factory.build(context, dsn, layoutFactory, levelFilterFactory, asyncAppenderFactory);
+                = factory.build(context, "", layoutFactory, levelFilterFactory, asyncAppenderFactory);
 
         assertThat(appender, instanceOf(AsyncAppender.class));
     }
```

- **Overview**: The problem is related to a NullPointerException that is thrown by the program during the execution of a test case.

- **Reason why the patches have been generated**: jKali manages to create the patches because it changes the if condition in which the exception is thrown, and since the test case doesn't check if the property `dns` of the object SentryAppenderFactory is not null, the patched program passes all test cases.

- **Useful information for the developer**: The developer can focus on the property `dns` of the object SentryAppenderFactory and check if it is initialized correclty or not. Since it is correct, the developer can then check if there is some errors in the test case.
