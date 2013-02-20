import sbt._
import Keys._

import sbtrelease.ReleasePlugin._

object ScalazContribBuild extends Build {

  val scalazVersion = "7.0.0-M8"

  lazy val standardSettings = Defaults.defaultSettings ++ releaseSettings ++ Seq(
    organization := "org.typelevel",

    scalaVersion := "2.10.0",
    crossScalaVersions := Seq("2.9.2", "2.10.0"),
        scalacOptions <++= (scalaVersion) map { sv =>
      val versionDepOpts =
        if (sv.contains("2.10"))
          // does not contain -deprecation (because of ClassManifest)
          // contains -language:postfixOps (because 1+ as a parameter to a higher-order function is treated as a postfix op)
          Seq("-feature", "-language:implicitConversions", "-language:higherKinds", "-language:existentials", "-language:postfixOps")
        else
          Seq("-Ydependent-method-types", "-deprecation")

      Seq("-unchecked") ++ versionDepOpts
    },

    libraryDependencies ++= Seq(
      "org.scalaz" %% "scalaz-core" % scalazVersion
    ),
    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.sonatypeRepo("releases")
    ),

    sourceDirectory <<= baseDirectory(identity),

    publishTo <<= (version).apply { v =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("Snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("Releases" at nexus + "service/local/staging/deploy/maven2")
    },
    credentials += Credentials(
      Option(System.getProperty("build.publish.credentials")) map (new File(_)) getOrElse (Path.userHome / ".ivy2" / ".credentials")
    ),
    pomIncludeRepository := Function.const(false),
    pomExtra :=
      <url>http://typelevel.org/scalaz</url>
        <licenses>
          <license>
            <name>MIT</name>
            <url>http://www.opensource.org/licenses/mit-license.php</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
            <url>https://github.com/folone/scalaz-geo</url>
            <connection>scm:git:git://github.com/folone/scalaz-geo.git</connection>
            <developerConnection>scm:git:git@github.com:folone/scalaz-geo.git</developerConnection>
        </scm>
        <developers>
          <developer>
            <id>larsrh</id>
            <name>Lars Hupel</name>
            <url>https://github.com/larsrh</url>
          </developer>
          <developer>
            <id>folone</id>
            <name>George Leontiev</name>
            <url>https://github.com/folone</url>
          </developer>
        </developers>
  )

  lazy val scalazGeo = Project(
    id = "scalaz-geo",
    base = file("."),
    settings = standardSettings ++ Seq(
      publishArtifact := false
    ),
    aggregate = Seq(core)
  )

  lazy val core = Project(
    id = "core",
    base = file("core"),
    settings = standardSettings ++ Seq(
      name := "scalaz-geo-core"
    )
  )

}

// vim: expandtab:ts=2:sw=2
