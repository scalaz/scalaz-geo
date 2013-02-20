package scalaz.contrib
package geo

import scalaz._
import std.tuple._

sealed trait Vector {
  val coord: Coord
  val bearing: Bearing
}

trait Vectors {
  def vector(c: Coord, b: Bearing) = new Vector {
    val coord = c
    val bearing = b
  }
}

object Vector {
  implicit val vectorInstances = new Show[Vector] with Order[Vector] {
    override def shows(v: Vector) = Show[(Coord, Bearing)].shows((v.coord, v.bearing))
    def order(v1: Vector, v2: Vector) =
      Order[(Coord, Bearing)].order((v1.coord, v1.bearing), (v2.coord, v2.bearing))
  }
}
