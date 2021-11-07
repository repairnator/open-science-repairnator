# Information about the failure

| Error type   | Erroring test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| java.lang.NullPointerException | [AvantageRepositoryTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/dc9b199592973111c3d3c668a13e6c9520efad5b/src/test/java/dev/paie/repository/AvantageRepositoryTest.java#L36)| [Avantage.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/dc9b199592973111c3d3c668a13e6c9520efad5b/src/main/java/dev/paie/entite/Avantage.java#L57)|

**Human fix**:

```diff
From d81b4ef14fe1137c6c087d18b436fffe03c7cd72 Mon Sep 17 00:00:00 2001
From: Gauthier Puertas <gauthier.puertas@gmail.com>
Date: Mon, 28 May 2018 16:11:56 +0200
Subject: =?UTF-8?q?Suppression=20g=C3=A9n=C3=A9ration=20ID=20pour?=
 =?UTF-8?q?=20avantage?=
MIME-Version: 1.0
Content-Type: text/plain; charset=UTF-8
Content-Transfer-Encoding: 8bit

---
 src/main/java/dev/paie/entite/Avantage.java | 3 ---
 1 file changed, 3 deletions(-)

diff --git a/src/main/java/dev/paie/entite/Avantage.java b/src/main/java/dev/paie/entite/Avantage.java
index febeacf..506ed08 100644
--- a/src/main/java/dev/paie/entite/Avantage.java
+++ b/src/main/java/dev/paie/entite/Avantage.java
@@ -4,15 +4,12 @@
 
 import javax.persistence.Column;
 import javax.persistence.Entity;
-import javax.persistence.GeneratedValue;
-import javax.persistence.GenerationType;
 import javax.persistence.Id;
 
 @Entity
 public class Avantage {
 
 	@Id
-	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(name = "id_avantage", unique = true, nullable = false)
 	private Integer id;
```

- **Overview**: The problem is related to a `NullPointerException` that is thrown by the program during the execution of a test case. This `NullPointerExcption` is generated because Hibernate save a new record with a different ID from what the developer expects in the test case.

- **Reason why the patch has been generated**: jKali managed to create a patch because it removes the instruction to assign the ID. Since the test case doesn't check if the ID of the new record is not null, jKali is able to create the patch. Adding for example this assertion `assertNotNull(avantage.getId());`, it allows to avoid the creation of the code-removal patch.

- **Useful information for the developer**: The developer can understand that there is a problem with the variable `id` used to assign the primary key to the new records. Indeed, to fix the error, the developer removed the annotation `@GeneratedValue(strategy = GenerationType.IDENTITY)` associated with the variable `id`. `GenerationType.IDENTITY` is associated with `auto_increment` of MySQL (https://stackoverflow.com/a/20605392/4255576).
