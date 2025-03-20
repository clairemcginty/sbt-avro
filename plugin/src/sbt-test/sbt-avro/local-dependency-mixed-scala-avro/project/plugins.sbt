sys.props.get("plugin.version") match {
  case Some(x) => addSbtPlugin("com.github.sbt" % "sbt-avro" % x)
  case _ => addSbtPlugin("com.github.sbt" % "sbt-avro" % "4.0.0-SNAPSHOT")
  //sys.error("""|The system property 'plugin.version' is not defined.
                         //|Specify this property using the scriptedLaunchOpts -D.""".stripMargin)
}
