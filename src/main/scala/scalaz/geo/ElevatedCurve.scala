package scalaz.contrib
package geo

import scalaz._
import std.tuple._

sealed trait ElevatedCurve {
  val curve: GeodeticCurve
  val elevation: Elevation

  lazy val length = {
    val d = curve.ellipsoidalDistance
    val e = elevation.value
    scala.math.sqrt(d * d + e * e) 
  }
}

trait ElevatedCurves {
  def elevatedCurve(c: GeodeticCurve, e: Elevation) = new ElevatedCurve {
    val curve = c
    val elevation = e
  }
}

object ElevatedCurve {
  implicit val elevatedCurveInstances = new Show[ElevatedCurve] with Order[ElevatedCurve] {
    override def shows(e: ElevatedCurve) = Show[(GeodeticCurve, Elevation)].shows((e.curve, e.elevation))
    def order(e1: ElevatedCurve, e2: ElevatedCurve) =
      Order[(GeodeticCurve, Elevation)].order((e1.curve, e1.elevation), (e2.curve, e2.elevation))
  }
}
