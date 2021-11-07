# Information about the failure

| Failure type | Failing test case | Changed file by AstorJKali |
|--------------|-------------------|----------------------------|
| java.lang.AssertionError | [DirectEndPointRequestMapperTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/f1b4847020795def5ebf06ee012da718fae6b2ee/frontend/ld/src/test/java/org/dotwebstack/framework/frontend/ld/mappers/DirectEndPointRequestMapperTest.java#L145) | [DirectEndPointRequestMapper.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/f1b4847020795def5ebf06ee012da718fae6b2ee/frontend/ld/src/main/java/org/dotwebstack/framework/frontend/ld/mappers/DirectEndPointRequestMapper.java#L66-L67)|

- **Overview**: The build fails due to a [change in a test case](https://github.com/dotwebstack/dotwebstack-framework/pull/134/commits/1b00a5f10ebd6864b36919de8d66ea870559135c). Looking at the [commit history](https://github.com/dotwebstack/dotwebstack-framework/pull/134/commits/461d32c1733dbcfc979df4bd8eee28ece3f6883a#) of the project, the develoer restored the previous versione of the test case.
- **Reason why the patch has been generated**: The code-removal patch works because of an error in the test case.
- **Useful information for the developer**: The develper can exploit the code-removal patch to understand if there is a bug or not in the test case.
- **Human fix**: [https://github.com/dotwebstack/dotwebstack-framework/compare/1b00a5f10ebd...07e0e7854b35](https://github.com/dotwebstack/dotwebstack-framework/compare/1b00a5f10ebd...07e0e7854b35)
