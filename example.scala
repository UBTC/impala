// An example on actor

// imports
import scala.actors._  // Actor
import scala.actors.Actor._  // Actor
import scala.collection.JavaConversions._  // Java ops
import scala.collection.mutable._  // ArrayBuffer, Buffer, HashMap, Map, ...
import scala.io.Source._  // file ops
import scala.math._  // the math
import scala.sys.process._  // work with shell
import scala.util.control.Breaks._  // break loops
import scala.util.parsing._  // parsing toolbox
import scala.util.Sorting._  // quickSort etc
import scala.util.continuations._  // cont...

// http://www.cnblogs.com/ihongyan/p/4815085.html

object TestScalaActor {
  def main(args: Array[String]): Unit = {
    testActor // func: creat an actor, and send msg to it
    testActor2 // func: use actor functions, receieve msg
  } // end of main

  def testActor = {
    val actor = new HiActor
    // start the object first!!!
    actor.start()
    // send message to the actor
    actor ! "Hi"
    actor ! "Hii"
  } // end of func1: testActor

  // use Actor functions
  def testActor2 = {
    val actor2 = actor {
      while (true) {
        receive {
          case "Hi" => println("2Hello")
          case _    => println("2please say Hi")
        }
      }
    } // end of func actor2
    actor2 ! "Hi"
    actor2 ! "Hii"

    val actor3 = actor {
      while(true) {
        receive{
          case msg(types, num) => println(types + ": " + num)
          case MsgB => println("MsgB")
          case _    => println("some message")
        }
      }
    } // end of func actor3
    actor3 ! msg("PP", 12)
    actor3 ! MsgB
    actor3 ! "Hi"
  } // end of testactor2
} // end of object Test...

// rewrite the abstract func act, to redefine action of actor
class HiActor extends Actor {
  def act() {
    while (true) {
      receive {
        case "Hi" => println("Hello")
        case _    => println("please say Hi")
      }
    }
  }
}

case class MsgA()
case class MsgB()
case class msg(types:String, num:Int)
