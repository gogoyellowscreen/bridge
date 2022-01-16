import com.apurebase.arkenv.Arkenv
import com.apurebase.arkenv.argument
import com.apurebase.arkenv.parse
import com.sun.glass.ui.Application
import drawing.AwtDrawingApi
import drawing.Coordinate
import drawing.DrawingApi
import drawing.FxDrawingApi
import graph.EdgesGraph
import graph.Graph
import graph.MatrixGraph
import java.io.File
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

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
                MATRIX -> MatrixGraph(graphFile)
                EDGES -> EdgesGraph(graphFile)
                else -> throw IllegalArgumentException("Graph structure should be '$MATRIX' or '$EDGES'")
            }
        }
    }

    val drawingApi: String by argument("--drawing") {
        description = "API for drawing graph."
        validate("Should be '$AWT' or '$JAVA_FX'") {
            it.lowercase() == AWT || it.lowercase() == JAVA_FX
        }
    }

    val width: Double by argument("--width") {
        description = "Window screen width."
        defaultValue = { 1000.0 }
        validate("Should be more than 0") {
            it > 0
        }
    }

    val height: Double by argument("--height") {
        description = "Window screen height."
        defaultValue = { 800.0 }
        validate("Should be more than 0") {
            it > 0
        }
    }
}

fun main(args: Array<String>) {
    val parameters = Parameters().parse(args)

    if (parameters.help) {
        println(parameters.toString())
        return
    }

    parameters.run {
        if (drawingApi.lowercase() == AWT) {
            graph.drawingApi = AwtDrawingApi(width, height)
            graph.drawGraph()
        }

        if (drawingApi.lowercase() == JAVA_FX) {
            FxDrawingApi(width, height) {
                graph.drawingApi = it
                graph.drawGraph()
            }
        }
    }
}
