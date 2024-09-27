package com.github.sbt.avro.test

object Main extends App {

  external.Avsc.newBuilder().setStringField("external").build()

  println("success")
}