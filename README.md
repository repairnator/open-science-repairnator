# Data about failing Travis builds collected in the Repairnator project

This is an open-science repository which intends to aggregate the data of the paper published at ICSE-SEIP 2018 entitled ["How to Design a Program Repair Bot? Insights from the Repairnator Project"](https://hal.inria.fr/hal-01691496/document)

For any information, please contact the authors of the paper.

## Structure

The repository contains 2 directories:

1. `data` contains all the raw data that are exploited in the paper
2. `scripts` contains all the scripts used to gather the provided data. They are intended for documentation not for use.

## Data

The data are organized in three directories.

 
### 1-all-builds

This directory contains all data about section 3.2 in the paper. 
It concerns the list of examinated projects, and the raw data of all tentatively reproduced bugs. 
It also contains aggregated data about number of bugs per projects and overall number of statuses. 

* `all-data.json` and `all-data.tsv` contains the same data, the 11523 build identifiers of Java projects with test failures observed on Travis
* `failures-and-pr-by-projects.tsv` gives the data of Table 1
* `inspected-projects-list.txt` gives the list of considered github projects
* `status-results.tsv` is the data behind Figure 4


### 2-reproduced-builds

This directory contains all data about section 4.2 of the paper.
It contains raw data about bugs successfully reproduced as well as aggregated data about kinds of test failures, and reproduced bug by project.
A special file also link the id of a reproduced bug to the URL containing the data of the reproduction.

* `reproduced-builds.json` and `reproduced-builds.tsv` contains the data about the 3551 locally reproduced builds.
* `bug-associated-branch.tsv` points to a copy of the project with failing build
* `test-failures-kinds.tsv` contains the data about Table 3
* `reproduced-bug-by-project.tsv` ontains the data about Table 2

### 3-patched-builds

This directory contains all data about section 5.2 of the paper. 
It contains raw data about automatically patched bugs.
A special file also link the id of a patched bug to the URL containing the data of the reproduction and patches.

* `patched-builds.json` and `patched-builds.tsv` contains the data about the 15 builds with at least one test-suite adequate patch.
* `bug-associated-branch.tsv` points to a copy of the project with failing build
* `patch-stats.tsv` contains statistics about the patches

## Aknowledgement

This research has been supported by InriaHub program and the Wallenberg Autonomous Systems and Software Program (WASP)
