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
        TODO("Not yet implemented")
    }
}
