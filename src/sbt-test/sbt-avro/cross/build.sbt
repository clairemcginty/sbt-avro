lazy val commonSettings = Seq(
  organization := "com.github.sbt",
  publishTo := Some(Opts.resolver.sonatypeReleases),
  scalaVersion := "2.13.14",
  libraryDependencies ++= Seq(
    "org.apache.avro" % "avro" % avroCompilerVersion
  )
)

lazy val `external`: Project = project
  .in(file("external"))
  .settings(commonSettings)
  .settings(
    name := "external",
    version := "0.0.1-SNAPSHOT",
    crossPaths := false,
    autoScalaLibrary := false,
    Compile / packageAvro / publishArtifact := true
  )

lazy val root: Project = project
  .in(file("."))
  .settings(commonSettings)
  .settings(
    name := "publishing-test",
    crossScalaVersions := Seq("2.13.14", "2.12.20"),
    libraryDependencies ++= Seq(
      "com.github.sbt" % "external" % "0.0.1-SNAPSHOT" classifier "avro"
    )
  )
