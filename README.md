# Media Services - Summariser

## Preface

`Summariser` summarises users viewing habits to help the BBC to intelligently
recommend content. It accepts a _mapping_ between programmes and categories
supplied as a JSON file of the format:

```
{
    "childrens": [
        "Peter Rabbit",
        "Blue Peter",
        "Danger Mouse"
    ],
    "current_affairs": [
        "News",
        "Question Time",
        "The One Show"
    ],
    "drama": [
        "Eastenders",
        "Holby City",
        "Killing Eve"
    ],
    "science_fiction": [
        "Dr. Who",
        "Douglas Hill"
    ]
}
```

These mappings are matched against a list of shows that audience members have
watched—and the amount of time they spent watching them. These viewings are
supplied as a file of comma-separated values of the format:

```
<date in epoch seconds>, <user_identifier>, <programme name>, <watch time in seconds>, <device_type>
```

...such as:

```
1539268536,88888888,Holby City,3600,ip_tv
1539268599,99999999,Dr. Who,180,mobile
```

`Summariser` will then generate a JSON output file containing a summary of the
viewing data organised by identifier, month of the year, and category.

A message will be printed to `stderr` any time a customer has spent more than
15 hours watching BBC content in a rolling 7 day period. e.g.

```
WARNING: 88888888 consumed 15 hours of bbc content between 01/01/19 and 07/01/19
```

## Pre-requisites

To build this project you will need to:

### Install Bazel

[install Bazel](https://docs.bazel.build/versions/master/install.html). Bazel is
an open-source build and test tool similar to Make, Maven, and Gradle.

When running a build or a test, Bazel does the following:

- _Loads_ the `BUILD` files relevant to the target.
- _Analyzes_ the inputs and their dependencies, applies the specified build rules, and produces an action graph.
- _Executes_ the build actions on the inputs until the final build outputs are produced.

### Set `JAVA_HOME`

The following command is used to set your `JAVA_HOME` automatically _(assuming
you don't already have this set)_; you will need the command line tool
[realpath](http://man7.org/linux/man-pages/man3/realpath.3.html) which can be
installed on OS X using [Homebrew](https://brew.sh/):

```
brew install realpath
```

Then `JAVA_HOME` is set with:

```
export JAVA_HOME="$(dirname $(dirname $(realpath $(which javac))))"
```

This `export` command can be added to your `.bash_profile` (or `.zshrc` etc) to
be run automatically when starting a new interactive shell.

## Run all tests

```
bazel test //:AllTests
```

This executes all tests within the `uk.co.bbc.mediaservices.summariser.AllTests`
test suite.

## Build the project

```
bazel build //:ProjectRunner
```

## Run the project

```
bazel-bin/ProjectRunner \
    --host_jvm_args="-Xmx256m" \
    --category-mappings /path/to/mappings.json \
    --viewings /path/to/viewings.csv \
    --output /path/to/output.json
```

...where

`--category-mappings` is the path to a category_mappings.json file.

`--viewings` is the path to a viewings.csv.

`--output` is the path to an output json file.

## Build the action graph

The _action graph_ represents the build artifacts, the relationships between them,
and the build actions that Bazel will perform. By using this graph Bazel can
track changes to files and actions, such as build or test commands, and know
what build work has previously been done. This allows Bazel to complete fast and
incremental builds.

The action graph can be used to trace dependencies in your code. To generate a
graph; which can be pasted into [Webgraphviz](http://www.webgraphviz.com/):

```
bazel query  --nohost_deps --noimplicit_deps "deps(//:ProjectRunner)" --output graph
```

## Clean the project

To completely clean the project run:

```
bazel clean --expunge
```
