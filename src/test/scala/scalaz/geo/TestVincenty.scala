package scalaz.contrib.geo

import org.specs2.scalaz.ScalazMatchers
import org.specs2.Specification

class TestVincenty extends Specification with ScalazMatchers {
  import scalaz.contrib.geo._
  import Geo._

  def is = "Vincenty test" ! {
    // Mount Barney, Queensland, Australia
    val mountBarney = -28.2807 |-| 152.698

    // N'ga Peak, Isle of Pines, New Caledonia
    val ngaPeak = -22.6528 |-| 167.4619

    // Travelling at 11.5 degrees for a little over 150km from Mount Barney places you at the given vector.
    (mountBarney.direct(bearing(11.5D), 150435D) must equal(vector(-26.950066610300084D |-| 153.0000106566432D, bearing(11.359998078380356D)))) and
    // Mount Barney to N'ga Peak is 1608.695km
    ((mountBarney inverse ngaPeak) must equal(curve(1608695.5945547633, azimuth(0D), azimuth(180D))))
  }
}
