# Topological Data Analysis methods for dimensionality reduction

Project for [data exploration class](http://www.ii.uni.wroc.pl/~lipinski/lectureED2016.html)

The aim of this project is to provide example of using *topological data analysis* for evaluating dimensionality reduction algorithms.

*Homology* is well-known topological invariant. Rougly put, rank of homology group in dimension *d* corresponds to number of *d*-dimensional holes of a space.

In the project method of *persistent homology* is used to evaluate appropriate homology of point clouds before and after using dimensionality reduction. We can then compare results, and see which method preserves homology better - in other words, find out which reduction corresponds to deformation that loses less geometrical features.

## Setup


#### Notebook
``` setup.sh ``` downloads dependencies.
Run ``` sbt assembly ``` (creates project jar used by notebook)

### Dependencies
sbt

[Jupyter Scala](https://github.com/alexarchambault/jupyter-scala)

[javaplex](https://github.com/appliedtopology/javaplex) 

