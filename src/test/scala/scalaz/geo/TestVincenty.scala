package scalaz.contrib.geo

import org.specs2.scalaz.Spec

import scalaz.scalacheck.ScalazArbitrary._
import scalaz.scalacheck.ScalazProperties._

class TestVincenty extends Spec {
  import scalaz.Scalaz._
  import scalaz.contrib.geo._
  import Geo._

  "Vincenty test" >> {
    // Mount Barney, Queensland, Australia
    val mountBarney = -28.2807 |-| 152.698

    // N'ga Peak, Isle of Pines, New Caledonia
    val ngaPeak = -22.6528 |-| 167.4619

    // Travelling at 11.5 degrees for a little over 150km from Mount Barney places you at the given vector.
    mountBarney.direct(bearing(11.5D), 150435D) must be_===(vector(-26.950066610300084D |-| 153.0000106566432D, bearing(11.359998078380356D)))

    // Mount Barney to N'ga Peak is 1608.695km
    (mountBarney inverse ngaPeak) must be_===(curve(1608695.5945547633, azimuth(0D), azimuth(180D)))
  }
}
