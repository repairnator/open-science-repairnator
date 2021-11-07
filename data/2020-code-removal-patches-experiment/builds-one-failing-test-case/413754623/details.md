| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|-----------------|
| java.lang.AssertionError | Expected: (an instance of java.lang.UnsupportedOperationException and exception with message a string containing "string too long to be reported here" | [JAXBCodecTest.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/e0bce64b0c5ab9575e0bf9a6ed56977ca1f849d5/jaxb/src/test/java/feign/jaxb/JAXBCodecTest.java#L180) | [JAXBDecoder.java](https://github.com/repairnator/repairnator-experiments-one-failing-test-case/blob/e0bce64b0c5ab9575e0bf9a6ed56977ca1f849d5/jaxb/src/main/java/feign/jaxb/JAXBDecoder.java#L73)|

- **Human Patch**: Not found

- **Overview**: the problem is related to the fact that the program throws an incorrect exception compared to the expected one. This is part of the stack trace of the incorrect exception: `java.lang.UnsupportedOperationException <feign.codec.DecodeException: com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException: 1 counts of IllegalAnnotationExceptions java.util.Map is an interface, and JAXB can't handle interfaces.`. 
- **Reason why the patches have been generated**: the test cases do not have proper inputs to reveal the incorrect behavior of the program changed with the code-removal patches.
