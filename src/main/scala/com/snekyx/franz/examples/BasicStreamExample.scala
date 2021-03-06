package com.snekyx.franz.examples

import akka.actor.ActorSystem
import akka.stream.scaladsl._
import akka.stream._
import com.snekyx.franz.api
import com.snekyx.franz.api.{Credentials, MultiChainCommands}

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

object BasicStreamExample extends MultiChainCommands {

  private val host = "localhost"
  private val rpcPort = 6834
  private val rpcUser = "multichainrpc"
  private val rpcPassword = "Ghmt3dmWv6TfFz3vVnDYhnyH2ZpLEdkrsUPJ5xYcHwAK"

  override implicit val system: ActorSystem = ActorSystem("BasicStreamExample")
  override implicit val materializer: ActorMaterializer = ActorMaterializer()
  override implicit val credentials: api.Credentials = Credentials(host, rpcPort, rpcUser, rpcPassword)
  override implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  val timeout = 1 minutes

  def main(args: Array[String]): Unit = {

//    val result = Await.result(create("stream21", true), 2 seconds)

//    Await.result(subscribe("stream21"), 2 seconds)

//    Await.result(publish("stream21", "Key1", "This is my stream data"), 2 seconds)
    val items = Await.result(listStreamItems("stream21"), 2 seconds)

    println(items)

    //Await.result(system.whenTerminated, timeout)
  }
}