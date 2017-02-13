package tda.persistence

import breeze.linalg._


object Math {

  def dist(X: DenseMatrix[Double], Y: DenseMatrix[Double]): DenseMatrix[Double] = {
    val XdotY = -2.0 * (X * Y.t)
    val XdotX = diag(X * X.t)
    val YdotY = diag(Y * Y.t)
    val tmp1 =  XdotY(::, *) + XdotX
    tmp1(*, ::) + YdotY
  }

  def embedding(x: Double, y: Double): Array[Double] = {
    Array(
      x *x,
      y*y,
      math.sqrt(2) * x * y,
      math.sqrt(2)* x,
      math.sqrt(2)* y,
      1)
  }
}
