name:="Potera"

version:="1.0"

scalaVersion:="2.11.8"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.102-R11"

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar")) 