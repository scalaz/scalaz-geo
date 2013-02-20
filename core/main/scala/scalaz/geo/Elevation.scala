package scalaz.contrib
package geo

sealed trait Elevation {
  val value: Double
}

trait Elevations {
  def elevation(d: Double) = new Elevation {
    val value = d.abs
  }
}

object Elevation {
  
  implicit val elevationInstances = new scalaz.Show[Elevation] with scalaz.Order[Elevation] {
    override def shows(e: Elevation) = e.value + "m"
    def order(e1: Elevation, e2: Elevation) = scalaz.Order[Double].order(e1.value, e2.value)
  }
}
