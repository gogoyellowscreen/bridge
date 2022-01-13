package graph

import drawing.DrawingApi
import java.io.File
import java.io.FileInputStream
import java.util.*

class MatrixGraph(drawingApi: DrawingApi, graphFile: File) : Graph(drawingApi) {
    private val graph: Array<Array<Boolean>>

    init {
        Scanner(FileInputStream(graphFile)).use { sc ->
            val matrixSize = sc.nextInt()
            graph = Array(matrixSize) { Array(matrixSize) { false } }
            for (i in 0 until matrixSize) {
                for (j in 0 until matrixSize) {
                    graph[i][j] = sc.nextInt() == 1
                }
            }
        }
    }

    override fun drawGraph() {
        val nodeToDrawArea = calculateNodeDrawAreas(graph.size)

        for (nodeFrom in graph.indices) {
            drawingApi.drawCircle(nodeToDrawArea[nodeFrom], NODE_RADIUS)
            for (nodeTo in nodeFrom + 1 until graph.size) {
                if (graph[nodeFrom][nodeTo]) {
                    drawingApi.drawLine(
                        begin = circleCenter(nodeToDrawArea[nodeFrom]),
                        end = circleCenter(nodeToDrawArea[nodeTo])
                    )
                }
            }
            drawingApi.drawNodeNumber(circleCenter(nodeToDrawArea[nodeFrom]), nodeFrom + 1)
        }
    }
}
