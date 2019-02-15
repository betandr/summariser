# Media Services - Summariser

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

## Run the project

Firstly build the project:

```
bazel build //:ProjectRunner
```

Then run the project:

```
bazel-bin/ProjectRunner
```

## Build the action graph

The action graph represents the build artifacts, the relationships between them,
and the build actions that Bazel will perform. By using this graph Bazel can
track changes to files and actions, such as build or test commands, and know
what build work has previously been done. The action graph can be used to trace
dependencies in your code.

To generate a graph, which can be pasted into
[Webgraphviz](http://www.webgraphviz.com/):

```
bazel query  --nohost_deps --noimplicit_deps "deps(//:ProjectRunner)" --output graph
```

## Clean the project

To completely clean the project run:

```
bazel clean --expunge
```
