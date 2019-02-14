# Media Services - Summariser

`bazel test //:AllTests`

`bazel build //:ProjectRunner`

`bazel-bin/ProjectRunner`

`bazel query  --nohost_deps --noimplicit_deps "deps(//:ProjectRunner)" --output graph`

`bazel clean --expunge`
