package graph

import drawing.DrawingApi

abstract class Graph(protected val drawingApi: DrawingApi) {
    abstract fun drawGraph()
}
