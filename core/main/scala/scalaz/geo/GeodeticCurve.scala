package scalaz.contrib
package geo

import scalaz._
import std.tuple._

sealed trait GeodeticCurve {
  val ellipsoidalDistance: Double
  val azi: Azimuth
  val reverseAzi: Azimuth
}

trait GeodeticCurves {
  def curve(d: Double, a: Azimuth, ra: Azimuth) = new GeodeticCurve {
    val ellipsoidalDistance = d.abs
    val azi = a
    val reverseAzi = ra
  }
}

object GeodeticCurve {
  implicit val geodeticCurveInstances = new Show[GeodeticCurve] with Order[GeodeticCurve] {
    override def shows(c: GeodeticCurve) = {
      val aziShow = Show[Azimuth]
      "[" + c.ellipsoidalDistance  + " " + aziShow.shows(c.azi) + " " + aziShow.shows(c.reverseAzi)
    }
    def order(c1: GeodeticCurve, c2: GeodeticCurve) =
      Order[(Double, Azimuth, Azimuth)].order(
        (c1.ellipsoidalDistance, c1.azi, c1.reverseAzi),
        (c2.ellipsoidalDistance, c2.azi, c2.reverseAzi)
      )
  }
}
