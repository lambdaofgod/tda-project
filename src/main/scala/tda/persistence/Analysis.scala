package tda.persistence

import edu.stanford.math.plex4.api.Plex4
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection
import edu.stanford.math.plex4.homology.chain_basis.Simplex
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm
import edu.stanford.math.plex4.streams.interfaces.AbstractFilteredStream
import edu.stanford.math.plex4.visualization.BarcodeVisualizer

import scala.collection.JavaConverters._


object Analysis {

  def defaultPersistenceAlgorithm(dim: Int) =
    Plex4.getModularSimplicialAlgorithm(dim, 2)

  def barcodeImages(
      complex: AbstractFilteredStream[Simplex],
      maxDimension: Int,
      maxFiltrationValue: Double,
      persistence: AbstractPersistenceAlgorithm[Simplex]) = {
    val intervals = persistence.computeIntervals(complex)
    val images = intervalImages(intervals, maxFiltrationValue)

    images
  }

  def subsampleByMaxmin(noSamples: Int, dataArray: Array[Array[Double]]): Array[Array[Double]] = {
    val selector = Plex4.createMaxMinSelector(dataArray, noSamples)
    for {
      i <- selector.getLandmarkPoints
    } yield dataArray(i)
  }

  def subsampleRandom(noSamples: Int, dataArray: Array[Array[Double]]): Array[Array[Double]] = {
    val selector = Plex4.createRandomSelector(dataArray, noSamples)
    for {
      i <- selector.getLandmarkPoints
    } yield dataArray(i)
  }

  private def intervalImages(
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
