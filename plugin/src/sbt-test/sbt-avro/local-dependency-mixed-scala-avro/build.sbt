def exists(f: File): Unit = assert(f.exists(), s"$f does not exist")
def absent(f: File): Unit = assert(!f.exists(), s"$f does exists")

lazy val commonSettings = Seq(
  organization := "com.github.sbt",
  scalaVersion := "2.13.15",
  Compile / avroDependencyIncludeFilter := (
    (
      _: ConfigRef,
      _: ModuleID,
      artifact: Artifact
    ) => artifact.classifier.contains("avro")
  )
)

lazy val avroOnlySettings = Seq(
  crossScalaVersions := Seq.empty,
  crossPaths := false,
  autoScalaLibrary := false
)

lazy val `external`: Project = project
  .in(file("external"))
  .enablePlugins(SbtAvro)
  .settings(commonSettings)
  .settings(avroOnlySettings)
  .settings(
    name := "external-schemas",
    version := "0.0.1-SNAPSHOT",
  )

lazy val `transitive`: Project = project
  .in(file("transitive"))
  .enablePlugins(SbtAvro)
  .settings(commonSettings)
  .settings(
    name := "transitive",
    version := "0.0.1-SNAPSHOT",
    libraryDependencies ++= Seq(
      "com.github.sbt" % "external" % "0.0.1-SNAPSHOT" classifier "avro"
    )
  )

lazy val root: Project = project
  .in(file("."))
  .enablePlugins(SbtAvro)
  .dependsOn(`transitive`)
  .settings(commonSettings)
  .settings(
    name := "local-dependency",
    crossScalaVersions := Seq("2.13.15", "2.12.20")
  )
