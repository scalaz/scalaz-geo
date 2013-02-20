package scalaz.contrib
package geo

sealed trait Bearing {
  val value: Double
}

trait Bearings {
  def bearing(d: Double) = new Bearing {
    val value = d % 360
  }
}

object Bearing {
  implicit val bearingInstances = new scalaz.Show[Bearing] with scalaz.Order[Bearing] {
    override def shows(b: Bearing) = b.value + "°"
    def order(b1: Bearing, b2: Bearing) = scalaz.Order[Double].order(b1.value, b2.value)
  }
}
