package scalaz.contrib
package geo

sealed trait Azimuth {
  val value: Double
}

trait Azimuths {
  def azimuth(d: Double) = new Azimuth {
    val value = d % 360
  }
}

object Azimuth {
  implicit val azimuthInstances = new scalaz.Show[Azimuth] with scalaz.Order[Azimuth] {
    override def shows(a: Azimuth) = a.value + "°"
    def order(a1: Azimuth, a2: Azimuth) = scalaz.Order[Double].order(a1.value, a2.value)
  }
}
