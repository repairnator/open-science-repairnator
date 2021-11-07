# Information about the failure

Human fix: Removed the test case (https://api.travis-ci.org/v3/build/358284751)

```diff
diff --git a/src/test/java/classification/IncidentClassificationTest.java b/src/test/java/classification/IncidentClassificationTest.java
index 1800375..708b55f 100644
--- a/src/test/java/classification/IncidentClassificationTest.java
+++ b/src/test/java/classification/IncidentClassificationTest.java
@@ -1,28 +1,27 @@
 package classification;
 
-import static org.junit.Assert.assertEquals;
-
 import org.junit.Test;
 
-import com.uniovi.clasification.IncidentsClassifier;
-import com.uniovi.clasification.NotificationManager;
-import com.uniovi.entitites.Incident;
-import com.uniovi.serializer.InciDeserializer;
-
 public class IncidentClassificationTest {
 
 	@SuppressWarnings("resource")
 	@Test
 	public void test() {
-		String json = "{\"name\":\"fuego en uniovi\",\"description\":\"se ha producido un incendio en la Escuela de Ingeniería Informática\",\"location\":\"41.5N35.99W\",\"tags\":[\"fuego\",\"informática\"],\"property_value\":{\"fire\":true,\"temperature\":40}}";
-		InciDeserializer deserializer = new InciDeserializer();
-		Incident i = deserializer.deserialize("", json.getBytes());
-		assertEquals(i.getDescription(), "se ha producido un incendio en la Escuela de Ingeniería Informática");
-		System.out.println(i.toString());
+		// String json = "{\"name\":\"fuego en uniovi\",\"description\":\"se ha
+		// producido un incendio en la Escuela de Ingeniería
+		// Informática\",\"location\":\"41.5N35.99W\",\"tags\":[\"fuego\",\"informática\"],\"property_value\":{\"fire\":true,\"temperature\":40}}";
+		// InciDeserializer deserializer = new InciDeserializer();
+		// Incident i = deserializer.deserialize("", json.getBytes());
+		// assertEquals(i.getDescription(), "se ha producido un incendio en la
+		// Escuela de Ingeniería Informática");
+		// System.out.println(i.toString());
+		//
+		// IncidentsClassifier classifier = new IncidentsClassifier();
+		// classifier.classify(i);
+		// assertEquals(NotificationManager.getInstance().getNotifications().size(),
+		// 1);
 
-		IncidentsClassifier classifier = new IncidentsClassifier();
-		classifier.classify(i);
-		assertEquals(NotificationManager.getInstance().getNotifications().size(), 1);
+		// OPERATORS SERVICE IS NULL, AND CANNOT BE TESTED
 	}
 
 }
\ No newline at end of file
```

The code-removal patch works because of an error in the test case.
