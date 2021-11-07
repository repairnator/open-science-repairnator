# Information about the failure

| Failure type | Failure details | Failing test case | Changed file by jKali |
|--------------|-------------------|----------------------------|------------|
| java.lang.AssertionError | expected: \<INACTIVE\> but was: \<ACTIVE\> | [DefaultClusterServiceTest.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/0d0ebd0dc2bb7f81967c94e3471208eb9cdeacf7/cluster/src/test/java/io/atomix/cluster/impl/DefaultClusterServiceTest.java#L195) | [DefaultClusterService.java](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/0d0ebd0dc2bb7f81967c94e3471208eb9cdeacf7/cluster/src/main/java/io/atomix/cluster/impl/DefaultClusterService.java#L200)|

- **Human Patch**: Not found, because there are many builds after the failing one, and none of them pass.

- **Overview**: The error is related to the status check of a `Node`.

- **Reason why the patch has been generated**: jKali managed to create a patch, because changing the `if condition`, it forces the execution of method `deactivateNode`. This method uses [another way](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/0d0ebd0dc2bb7f81967c94e3471208eb9cdeacf7/cluster/src/main/java/io/atomix/cluster/impl/DefaultClusterService.java#L289) to get the `node` object (it uses the node id, and it gets the node based on this id, checking in a Map), so that when its status is checked, it is `ACTIVE` (and not `INACTIVE`), and thus the method `deactivate(Node)` is executed entirely. In this way, the node is deactivated and the assertion is satisfied.

- **Useful information for the developer**: The developer can see how the status of Node is got in the method [`deactivateNode`](https://github.com/repairnator/repairnator-experiments-jkali-one-failing-test-case/blob/0d0ebd0dc2bb7f81967c94e3471208eb9cdeacf7/cluster/src/main/java/io/atomix/cluster/impl/DefaultClusterService.java#L288), and use the same way to check the status in the `if condition` changed by jKali. Indeed, in this way, all the test cases pass.

- **Possible fix**:

```diff
- if (node.getState() == State.ACTIVE) {
+ if (this.nodes.get(node.id()).getState() == State.ACTIVE)
```
