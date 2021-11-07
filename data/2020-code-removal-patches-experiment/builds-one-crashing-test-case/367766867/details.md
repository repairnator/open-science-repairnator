Human patch not found (too many changes)

Patch 1, Patch 2 and Patch 3 are equivalent
Patch 4 removes only one istruction from the set of instructions removed by the other patches: `metadataMap.incrementFeatureValue(rightNodeInternalId, edgeType)`;

The instruction that makes the program throw the exception: https://github.com/dginelli/jkali-gzoltar-one-crashing/blob/1f6fe3faaa10aeb15de35438b2ffa5ee9283b255/graphjet-core/src/test/java/com/twitter/graphjet/bipartite/RightNodeMetadataLeftIndexedMultiSegmentBipartiteGraphTest.java#L89

The code-removal patch works because of a weak test suite.
