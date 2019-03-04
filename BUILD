java_binary(
    name = "ProjectRunner",
    srcs = glob([
        "src/main/java/uk/co/bbc/mediaservices/summariser/*.java",
        "src/main/java/uk/co/bbc/mediaservices/summariser/domain/*.java"
    ]),
    deps = [
        "@javax_json_javax_json_api//jar",
        "@org_glassfish_javax_json//jar",
    ],
)

java_library(
    name = "test_lib",
    srcs = glob([
        "src/main/java/uk/co/bbc/mediaservices/summariser/*.java",
        "src/main/java/uk/co/bbc/mediaservices/summariser/domain/*.java"
    ]),
    deps = [
        "@javax_json_javax_json_api//jar",
        "@org_glassfish_javax_json//jar",
    ],
)

java_test(
    name = "AllTests",
    srcs = glob([
        "src/test/java/uk/co/bbc/mediaservices/summariser/*.java",
        "src/test/java/uk/co/bbc/mediaservices/summariser/domain/*.java",
        "src/test/java/uk/co/bbc/mediaservices/summariser/fixtures/*.java"
    ]),
    test_class = "uk.co.bbc.mediaservices.summariser.AllTests",
    deps = [
        ":test_lib",
        "@junit_junit//jar",
        "@org_mockito_mockito_core//jar",
    ],
)
