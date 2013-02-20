package scalaz.contrib
package geo

import scalaz._
import std.tuple._

sealed trait Position {
  val coord: Coord
  val elevation: Elevation
}

trait Positions {
  def position(c: Coord, e: Elevation) = new Position {
    val coord = c
    val elevation = e
  }
}

object Position {
  implicit val positionInstances = new Show[Position] with Order[Position] {
    override def shows(p: Position) = Show[(Coord, Elevation)].shows((p.coord, p.elevation))
    def order(p1: Position, p2: Position) =
      Order[(Coord, Elevation)].order((p1.coord, p1.elevation), (p2.coord, p2.elevation))
  }
}
