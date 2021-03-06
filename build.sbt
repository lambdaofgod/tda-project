name := "tda-project"

version := "1.0"

scalaVersion := "2.11.7"

target in assembly := file("lib/")

libraryDependencies ++= Seq(
  "org.scalanlp" %% "breeze" % "0.12" exclude("junit", "junit"),
  "org.scalanlp" %% "breeze-natives" % "0.12" exclude("junit", "junit"),
  "com.github.haifengl" % "smile-scala_2.11" % "1.2.1" excludeAll(
    ExclusionRule("slf4j", "slf4j-api"),
    ExclusionRule("xpp3", "xpp3"),
    ExclusionRule("xmlpull", "xmlpull"))
)

resolvers +=
  "Sonatype Releases" at
    "https://oss.sonatype.org/content/repositories/releases/"

