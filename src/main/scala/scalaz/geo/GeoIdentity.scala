package scalaz.contrib
package geo

sealed trait GeoOps[A] extends scalaz.syntax.Ops[A] {
  def toRadians(implicit r: Radians[A]): Double = r.toRadians(self)
}

trait ToGeoOps {
  implicit def GeoIdentityTo[A](x: A) = new GeoOps[A] {
    val self = x
  }
}
