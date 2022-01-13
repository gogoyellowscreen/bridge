import com.apurebase.arkenv.Arkenv
import com.apurebase.arkenv.argument
import com.apurebase.arkenv.parse
import drawing.AwtDrawingApi
import drawing.DrawingApi
import drawing.FxDrawingApi
import graph.EdgesGraph
import graph.Graph
import graph.MatrixGraph
import java.io.File

private const val MATRIX = "matrix"
private const val EDGES = "edges"
private const val AWT = "awt"
private const val JAVA_FX = "javafx"

class Parameters : Arkenv() {
    val graphFile: File by argument("--graph-file") {
        description = "Graph filepath."
        mapping = {
            File(it.lowercase())
        }
        validate("File does not exist or cannot be read") {
            it.exists() && it.isFile && it.canRead()
        }
    }

    val graph: Graph by argument("--graph") {
        description = "Graph structure."
        mapping = {
            when (it.lowercase()) {
                MATRIX -> MatrixGraph(drawingApi, graphFile)
                EDGES -> EdgesGraph(drawingApi, graphFile)
                else -> throw IllegalArgumentException("Graph structure should be '$MATRIX' or '$EDGES'")
            }
        }
    }

    val drawingApi: DrawingApi by argument("--drawing") {
        description = "API for drawing graph."
        mapping = {
            when (it.lowercase()) {
                AWT -> AwtDrawingApi()
                JAVA_FX -> FxDrawingApi()
                else -> throw IllegalArgumentException("Drawing API should be '$AWT' or '$JAVA_FX'")
            }
        }
    }
}

fun main(args: Array<String>) {
    val parameters = Parameters().parse(args)

    if (parameters.help) {
        println(parameters.toString())
        return
    }

//    parameters.run {
//        graph.read(graphFilepath)
//        graph.drawGraph()
//    }
}