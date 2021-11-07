# Information about the Failure

The code-removal patches work because the test case is flaky. Indeed, there are code-removal patches (e.g., [Patch 1](https://github.com/dginelli/code-removal-patches-analysis/blob/master/builds-one-failing-test-case/374587117/code-removal-patches/patch_1.patch) and [Patch 2](https://github.com/dginelli/code-removal-patches-analysis/blob/master/builds-one-failing-test-case/374587117/code-removal-patches/patch_2.patch)) that change the program in a opposite way.


When such a situation occurs, this could be seen by the developer as a sign that the result of a test case is independent of the change applied to the if condition.
