import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    name := "YambaScalaProject",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "2.9.2",
    platformName in Android := "android-9"
  )

  val proguardSettings = Seq (
    useProguard in Android := true
  )

  lazy val fullAndroidSettings =
    General.settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++ Seq (
      keyalias in Android := "change-me",
      libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"
    )
}

object AndroidBuild extends Build {
  lazy val main = Project (
    "YambaScalaProject",
    file("."),
    settings = General.fullAndroidSettings
  )

  lazy val tests = Project (
    "tests",
    file("tests"),
    settings = General.settings ++
               AndroidTest.androidSettings ++
               General.proguardSettings ++ Seq (
      name := "YambaScalaProjectTests"
    )
  ) dependsOn main
}
