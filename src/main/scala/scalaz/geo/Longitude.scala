package scalaz.contrib
package geo

sealed trait Longitude {
  val value: Double
}

trait Longitudes {
  def longitude(d: Double) = new Longitude {
    val value = (d + 180) % 360 - 180
  }
}

object Longitude {
  implicit val longitudeInstances = new scalaz.Show[Longitude] with scalaz.Order[Longitude] {
    override def shows(l: Longitude) = l.value + "°"
    def order(l1: Longitude, l2: Longitude) = scalaz.Order[Double].order(l1.value, l2.value)
  }
}
