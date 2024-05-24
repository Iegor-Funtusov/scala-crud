ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "scala-crud"
  )

libraryDependencies += "com.opencsv" % "opencsv" % "5.6"