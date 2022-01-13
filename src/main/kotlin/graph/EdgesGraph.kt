package graph

import drawing.DrawingApi
import java.io.File
import java.io.FileInputStream
import java.util.*

class EdgesGraph(drawingApi: DrawingApi, graphFile: File) : Graph(drawingApi) {
    private val graph: List<MutableList<Int>>

    init {
        Scanner(FileInputStream(graphFile)).use { sc ->
            val nodesNumber = sc.nextInt()
            val edgesNumber = sc.nextInt()
            graph = List(nodesNumber) { mutableListOf() }
            for (i in 0 until edgesNumber) {
                val node1 = sc.nextInt() - 1
                val node2 = sc.nextInt() - 1
                graph[node1].add(node2)
                graph[node2].add(node1)
            }
        }
    }

    override fun drawGraph() {
        val nodeToDrawArea = calculateNodeDrawAreas(graph.size)

        for ((node, nodesTo) in graph.withIndex()) {
            drawingApi.drawCircle(nodeToDrawArea[node], NODE_RADIUS)
            for (nodeTo in nodesTo) {
                drawingApi.drawLine(
                    begin = circleCenter(nodeToDrawArea[node]),
                    end = circleCenter(nodeToDrawArea[nodeTo])
                )
            }
            drawingApi.drawNodeNumber(circleCenter(nodeToDrawArea[node]), node + 1)
        }
    }
}
