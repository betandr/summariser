# The following dependencies were calculated from:
#
# generate_workspace --artifact=junit:junit:4.12 --artifact=org.mockito:mockito-core:2.7.22 --artifact=javax.json:javax.json-api:1.1.4 --artifact=org.glassfish:javax.json:1.1.4 --repositories=https://jcenter.bintray.com


def generated_maven_jars():
  # org.mockito:mockito-core:jar:2.7.22
  native.maven_jar(
      name = "net_bytebuddy_byte_buddy_agent",
      artifact = "net.bytebuddy:byte-buddy-agent:1.6.11",
      repository = "https://jcenter.bintray.com/",
      sha1 = "0200d9c012befccd211ff91082a151257b1dc084",
  )


  native.maven_jar(
      name = "org_mockito_mockito_core",
      artifact = "org.mockito:mockito-core:2.7.22",
      repository = "https://jcenter.bintray.com/",
      sha1 = "fcf63bc8010ca77991e3cadd8d33ad1a40603404",
  )


  # junit:junit:jar:4.12
  native.maven_jar(
      name = "org_hamcrest_hamcrest_core",
      artifact = "org.hamcrest:hamcrest-core:1.3",
      repository = "https://jcenter.bintray.com/",
      sha1 = "42a25dc3219429f0e5d060061f71acb49bf010a0",
  )


  # org.mockito:mockito-core:jar:2.7.22
  native.maven_jar(
      name = "net_bytebuddy_byte_buddy",
      artifact = "net.bytebuddy:byte-buddy:1.6.11",
      repository = "https://jcenter.bintray.com/",
      sha1 = "8a8f9409e27f1d62c909c7eef2aa7b3a580b4901",
  )


  native.maven_jar(
      name = "javax_json_javax_json_api",
      artifact = "javax.json:javax.json-api:1.1.4",
      repository = "https://jcenter.bintray.com/",
      sha1 = "c8efa3cfaeee2b05c2dfd54cba21548a081b1746",
  )


  # org.mockito:mockito-core:jar:2.7.22
  native.maven_jar(
      name = "org_objenesis_objenesis",
      artifact = "org.objenesis:objenesis:2.5",
      repository = "https://jcenter.bintray.com/",
      sha1 = "612ecb799912ccf77cba9b3ed8c813da086076e9",
  )


  native.maven_jar(
      name = "junit_junit",
      artifact = "junit:junit:4.12",
      repository = "https://jcenter.bintray.com/",
      sha1 = "2973d150c0dc1fefe998f834810d68f278ea58ec",
  )


  native.maven_jar(
      name = "org_glassfish_javax_json",
      artifact = "org.glassfish:javax.json:1.1.4",
      repository = "https://maven.java.net/content/repositories/releases/",
      sha1 = "943f240a509d3c70b448a55c6735591ecbd37c88",
  )




def generated_java_libraries():
  native.java_library(
      name = "net_bytebuddy_byte_buddy_agent",
      visibility = ["//visibility:public"],
      exports = ["@net_bytebuddy_byte_buddy_agent//jar"],
  )


  native.java_library(
      name = "org_mockito_mockito_core",
      visibility = ["//visibility:public"],
      exports = ["@org_mockito_mockito_core//jar"],
      runtime_deps = [
          ":net_bytebuddy_byte_buddy",
          ":net_bytebuddy_byte_buddy_agent",
          ":org_objenesis_objenesis",
      ],
  )


  native.java_library(
      name = "org_hamcrest_hamcrest_core",
      visibility = ["//visibility:public"],
      exports = ["@org_hamcrest_hamcrest_core//jar"],
  )


  native.java_library(
      name = "net_bytebuddy_byte_buddy",
      visibility = ["//visibility:public"],
      exports = ["@net_bytebuddy_byte_buddy//jar"],
  )


  native.java_library(
      name = "javax_json_javax_json_api",
      visibility = ["//visibility:public"],
      exports = ["@javax_json_javax_json_api//jar"],
  )


  native.java_library(
      name = "org_objenesis_objenesis",
      visibility = ["//visibility:public"],
      exports = ["@org_objenesis_objenesis//jar"],
  )


  native.java_library(
      name = "junit_junit",
      visibility = ["//visibility:public"],
      exports = ["@junit_junit//jar"],
      runtime_deps = [
          ":org_hamcrest_hamcrest_core",
      ],
  )


  native.java_library(
      name = "org_glassfish_javax_json",
      visibility = ["//visibility:public"],
      exports = ["@org_glassfish_javax_json//jar"],
  )
