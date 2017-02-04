import ammonite.ops._
import java.io.File

lazy val toplevelPath = Path("/" +
  pwd.segments
     .dropRight(1)
     .mkString("/"))


lazy val datadir = toplevelPath / 'data

private lazy val jarPath = toplevelPath / "lib" / "tda-project-assembly-1.0.jar"

def loadProjectDependencies = interp.load.cp(jarPath)

def loadData(fileName: String) = {
  lazy val dataPath = toplevelPath / 'data / fileName
  new File(dataPath.toString)
}
