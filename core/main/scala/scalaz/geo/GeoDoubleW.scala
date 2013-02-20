package scalaz.contrib
package geo


sealed trait GeoDoubleOps extends scalaz.syntax.Ops[Double] {
  
  import Geo._

  def fromRadians[A](implicit r: Radians[A]) = r.fromRadians(self)

  def |-|(lon: Double) = latitude(self) |:| longitude(lon)

  def rad(lon: Double) = self.fromRadians[Latitude] |:| lon.fromRadians[Longitude]
}


trait ToGeoDoubleOps {
  implicit def GeoDoubleTo(n: Double): GeoDoubleOps = new GeoDoubleOps {
    val self = n
  }
}
