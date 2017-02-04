package tda.persistence

import breeze.linalg._

/**
  * Created by kuba on 04.02.17.
  */
object Math {

  def dist(X: DenseMatrix[Double], Y: DenseMatrix[Double]): DenseMatrix[Double] = {
    val XdotY = -2.0 * (X * Y.t)
    val XdotX = diag(X * X.t)
    val YdotY = diag(Y * Y.t)
    val tmp1 =  XdotY(::, *) + XdotX
    tmp1(*, ::) + YdotY
  }
}
