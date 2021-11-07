# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|-----------------|
| java.lang.AssertionError | String too long to be reported here | [UnmarshallingTest.java](https://github.com/repairnator/repairnator-experiments/blob/2382030cdde4c67035a9eac698109fdf190f4ea4/src/test/java/fr/vidal/oss/jaxb/atom/UnmarshallingTest.java#L83) | [SimpleElement.java](https://github.com/repairnator/repairnator-experiments/blob/2382030cdde4c67035a9eac698109fdf190f4ea4/src/main/java/fr/vidal/oss/jaxb/atom/core/SimpleElement.java#L75)|

- **Human Patch**

```diff
diff --git a/src/test/java/fr/vidal/oss/jaxb/atom/UnmarshallingTest.java b/src/test/java/fr/vidal/oss/jaxb/atom/UnmarshallingTest.java
index ad9eae9..4dcd1d2 100644
--- a/src/test/java/fr/vidal/oss/jaxb/atom/UnmarshallingTest.java
+++ b/src/test/java/fr/vidal/oss/jaxb/atom/UnmarshallingTest.java
@@ -170,11 +170,12 @@ public void unmarshall_feed_with_vendor_specific_element() throws Exception {
                     .addAttribute(XMLNS_ATTRIBUTE)
                     .addAttribute(Attribute.builder("vidal", "http://api.vidal.net/-/spec/vidal-api/1.0/").withNamespace(XMLNS_NAMESPACE).build())
                     .build(),
-                  ExtensionElements.simpleElement("galenicForm", "solution injectable")
-                        .withNamespace(Namespace.builder("http://api.vidal.net/-/spec/vidal-api/1.0/").withPrefix("vidal").build())
-                        .addAttribute(XMLNS_ATTRIBUTE)
-                        .addAttribute(Attribute.builder("vidal", "http://api.vidal.net/-/spec/vidal-api/1.0/").withNamespace(XMLNS_NAMESPACE).build())
-                        .build()
+                ExtensionElements.simpleElement("galenicForm", "solution injectable")
+                    .withNamespace(Namespace.builder("http://api.vidal.net/-/spec/vidal-api/1.0/").withPrefix("vidal").build())
+                    .addAttribute(XMLNS_ATTRIBUTE)
+                    .addAttribute(Attribute.builder("vidal", "http://api.vidal.net/-/spec/vidal-api/1.0/").withNamespace(XMLNS_NAMESPACE).build())
+                    .addAttribute(Attribute.builder("vidalId", "476").build())
+                    .build()
             );
     }
 
@@ -185,6 +186,7 @@ public void unmarshall_feed_with_extended_elements() throws Exception {
         String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
             "<feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
             "    <extension:simple xmlns:extension=\"http://foo.bar.org/extension/\">Text content</extension:simple>\n" +
+            "    <extension:withattribute xmlns:extension=\"http://foo.bar.org/extension/\" customAttribute=\"attribute-value\">Text content</extension:withattribute>\n" +
             "    <extension:wrapper xmlns:extension=\"http://foo.bar.org/extension/\">\n" +
             "        <extension:item>\n" +
             "            <extension:name>Item name</extension:name>\n" +
@@ -205,11 +207,20 @@ public void unmarshall_feed_with_extended_elements() throws Exception {
                         .withNamespace(XMLNS_NAMESPACE)
                         .build())
                     .build(),
+                ExtensionElements.simpleElement("withattribute", "Text content")
+                    .withNamespace(EXTENSION_NAMESPACE)
+                    .addAttribute(XMLNS_ATTRIBUTE)
+                    .addAttribute(Attribute.builder("extension", "http://foo.bar.org/extension/")
+                        .withNamespace(XMLNS_NAMESPACE)
+                        .build())
+                    .addAttribute(Attribute.builder("customAttribute", "attribute-value")
+                        .build())
+                    .build(),
                 ExtensionElements.structuredElement("wrapper",
                     ExtensionElements.structuredElement("item", ExtensionElements.simpleElement("name", "Item name")
-                            .withNamespace(EXTENSION_NAMESPACE)
-                            .build())
-                        .addChild(ExtensionElements.simpleElement("content","Item content")
+                        .withNamespace(EXTENSION_NAMESPACE)
+                        .build())
+                        .addChild(ExtensionElements.simpleElement("content", "Item content")
                             .withNamespace(EXTENSION_NAMESPACE)
                             .build())
                         .withNamespace(EXTENSION_NAMESPACE)
```

- **Reason why the patch has been generated**: The code-removal patch works because of an error in the test case.
- **Useful information for the developer**: The develper can exploit the code-removal patch to understand if there is an bug in the test case or not.
