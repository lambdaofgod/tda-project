package tda.persistence

import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection
import edu.stanford.math.plex4.visualization.BarcodeVisualizer

import scala.collection.JavaConverters._

/**
  * Created by kuba on 04.02.17.
  */
object Viz {

  def barcodeImages(
      intervals: BarcodeCollection[java.lang.Double],
      maxFiltrationValue: Double) = {
    val intervalsPerDimension = intervals
      .getIntervalIterator
      .asScala
      .map(x => (x.getKey, x.getValue))
      .toList
    val imgs = intervalsPerDimension map { case (dim, is) =>
      BarcodeVisualizer
        .drawBarcode(is, s"dimension $dim", maxFiltrationValue)
    }
    imgs
  }
}
