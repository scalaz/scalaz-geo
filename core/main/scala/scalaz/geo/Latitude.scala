package scalaz.contrib
package geo

sealed trait Latitude {
  val value: Double

  import Geo.coord
  
  def |:|(lon: Longitude) = coord(this, lon)
}

trait Latitudes {
  def latitude(d: Double) = new Latitude {
    val value = (d + 90) % 180 - 90
  }
}

object Latitude {
  implicit val latitudeInstances = new scalaz.Show[Latitude] with scalaz.Order[Latitude] {
    override def shows(l: Latitude) = l.value + "°"
    def order(l1: Latitude, l2: Latitude) = scalaz.Order[Double].order(l1.value, l2.value)
  }
}
