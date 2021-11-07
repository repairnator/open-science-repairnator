The five code-removal patches change the same file (DubboProtocol) and work because of a weak test suite.

[Patch 4](https://github.com/dginelli/code-removal-patches-analysis/blob/master/builds-one-crashing-test-case/418325841/code-removal-patches/patch_4.patch) and [Patch 5](https://github.com/dginelli/code-removal-patches-analysis/blob/master/builds-one-crashing-test-case/418325841/code-removal-patches/patch_5.patch) are equivalent since the first one changes the if condition to false to avoid its execution, while the second one delete the body of the if-statement.
