ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "scala-crud",
    libraryDependencies += "com.opencsv" % "opencsv" % "5.9",
    libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.10.1",
    libraryDependencies += "io.circe" %% "circe-yaml" % "1.15.0",
    libraryDependencies += "io.circe" %% "circe-generic" % "0.14.7",
    libraryDependencies += "io.circe" %% "circe-parser" % "0.14.7"
  )
