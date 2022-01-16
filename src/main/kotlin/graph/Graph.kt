package graph

import drawing.Coordinate
import drawing.DrawingApi
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

const val NODE_RADIUS = 20.0

abstract class Graph {
    lateinit var drawingApi: DrawingApi

    private val areaRadius
        get() = min(drawingApi.getDrawingAreaHeight(), drawingApi.getDrawingAreaHeight()) / 2 - NODE_RADIUS * 2

    @OptIn(ExperimentalStdlibApi::class)
    protected fun calculateNodeDrawAreas(nodesNumber: Int): List<Coordinate> {
        return buildList {
            for (node in 0 until nodesNumber) {
                val angle = 2 * Math.PI * node / nodesNumber
                val x = cos(angle) * areaRadius
                val y = sin(angle) * areaRadius
                add(Coordinate(x, y))
            }
        }
    }
    
    protected fun circleCenter(leftTop: Coordinate,
                               nodeRadius: Double = NODE_RADIUS): Coordinate {
        return Coordinate(leftTop.x + nodeRadius / 2, leftTop.y + nodeRadius / 2)
    }

    abstract fun drawGraph()
}
