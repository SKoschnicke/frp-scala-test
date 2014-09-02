import rx._
import rx.ops._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Main {

  def main(args: Array[String]) {
    implicit val Scheduler = new AkkaScheduler(akka.actor.ActorSystem())

    val t = Timer(2 seconds)
    println("Hello, world!")
    val a = Var(1)
    val b = Var(2)
    val c = Rx{ a() + b() }
    val updater = Obs(t) {
      a() = a() + 1
    }
    val o = Obs(c) {
      println(c())
    }
    a() = 3
    b() = 3
  }
}
