**Information about the failure**:

| Error type   | Crashing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|
| javax.persistence.PersistenceException | [CotisationServiceJpaTest.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/2ae47304966586bd5b33c3d4b6fb4e06f95c4058/src/test/java/dev/paie/service/CotisationServiceJpaTest.java#L33)| [Cotisation.java](https://github.com/repairnator/repairnator-experiments-one-erroring-test-case/blob/2ae47304966586bd5b33c3d4b6fb4e06f95c4058/src/main/java/dev/paie/entite/Cotisation.java#L31) or [CotisationServiceJpa.java](https://github.com/repairnator/repairnator-experiments/blob/ff44005896edabea527164f7b401a7278d29abda/src/main/java/dev/paie/service/CotisationServiceJpa.java#L22)|

- **Human Patch**:

The developer changed also a test case, but the fix of the error is only the deletion of the assignment to the variable `id`. Since the assignment is no longer necessary, the developers removes also the parameter from the constructor of the class `Cotisation`, and she adapts the test case.

```diff
From dc7cbf5a7f1343b49d849dae078258315a92904a Mon Sep 17 00:00:00 2001
From: Geraud Tourrilhes <geraud.tourrilhes@gmail.com>
Date: Mon, 28 May 2018 14:19:36 +0200
Subject: [PATCH] suppression id cotisation

---
 src/main/java/dev/paie/entite/Cotisation.java                | 3 +--
 src/test/java/dev/paie/service/CotisationServiceJpaTest.java | 2 +-
 2 files changed, 2 insertions(+), 3 deletions(-)

diff --git a/src/main/java/dev/paie/entite/Cotisation.java b/src/main/java/dev/paie/entite/Cotisation.java
index 5c675df..df4f35a 100644
--- a/src/main/java/dev/paie/entite/Cotisation.java
+++ b/src/main/java/dev/paie/entite/Cotisation.java
@@ -26,9 +26,8 @@
 	@Column(name = "tauxPatronal")
 	private BigDecimal tauxPatronal;
 
-	public Cotisation(Integer id, String code, String libelle, BigDecimal tauxSalarial, BigDecimal tauxPatronal) {
+	public Cotisation(String code, String libelle, BigDecimal tauxSalarial, BigDecimal tauxPatronal) {
 		super();
-		this.id = id;
 		this.code = code;
 		this.libelle = libelle;
 		this.tauxSalarial = tauxSalarial;
diff --git a/src/test/java/dev/paie/service/CotisationServiceJpaTest.java b/src/test/java/dev/paie/service/CotisationServiceJpaTest.java
index 160e012..cb165a1 100644
--- a/src/test/java/dev/paie/service/CotisationServiceJpaTest.java
+++ b/src/test/java/dev/paie/service/CotisationServiceJpaTest.java
@@ -26,7 +26,7 @@
 	@Test
 	public void test_sauvegarder_lister_mettre_a_jour() {
 
-		cotisation = new Cotisation(new Integer(1), "EP50", "l", new BigDecimal("0.54045"), new BigDecimal("0.1478"));
+		cotisation = new Cotisation("EP50", "l", new BigDecimal("0.54045"), new BigDecimal("0.1478"));
 
 		cotisationService.supprimer();
 ```
 
 - **Overview**: The problem is related to a `PersistenceException` that is thrown while the program executes the test cases.

- **Reason why the patches have been generated**: The first code-removal patch is equal to the human patch, so it's correct that it has been generated. The second code-removal patch is incorrect because it removes the functionality and it is for this reason that the exception is no longer thrown.

- **Useful information for the developer**: The developer can apply the same fix of the first code-removal patch.
