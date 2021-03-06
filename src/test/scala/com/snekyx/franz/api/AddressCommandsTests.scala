package com.snekyx.franz.api

import com.snekyx.franz.api.addresses.{Address, NewAddressResponse}
import com.snekyx.franz.api.util.MultiChainSetup
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterAll
import org.specs2.matcher.MatcherMacros

import scala.language.experimental.macros
import scala.concurrent.{Await}
import scala.concurrent.duration._

class AddressCommandsTests extends Specification with MultiChainSetup with BeforeAfterAll with MatcherMacros {

  sequential

  override val multiChainName = "addressTestsBlockChain"

  override def beforeAll(): Unit = {
    deleteBlockChain()
    createBlockChain()
    startBlockChainDaemon

    Thread.sleep(3000)
  }

  override def afterAll(): Unit = {
    stopBlockChain()
    Thread.sleep(3000)
    deleteBlockChain()
  }

  "Multichain" should {
    "create a new Address" in {
      val result = Await.result(multiChainCommands.getNewAddress(), 2 seconds)
      println(result)
      result match {
        case x: NewAddressResponse => success
        case _ => failure("expected Result is NewAddressResponse")
      }
    }


    "get all address list" in {
      val result = Await.result(multiChainCommands.getAddressList(), 2 seconds)
      println(result)
      result match {
        case x: List[String] => success
        case _ => failure("expected Result is NewAddressResponse")
      }
    }

    "get all addresses" in {
      val result = Await.result(multiChainCommands.getAddresses(), 2 seconds)
      println(result)
      result match {
        case x: List[Address] => success
        case _ => failure("expected Result is addresses")
      }
    }
  }
}
