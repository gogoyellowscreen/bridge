package graph

import drawing.DrawingApi
import java.io.File

abstract class Graph(protected val drawingApi: DrawingApi) {
    abstract fun read(file: File)
    abstract fun drawGraph()
}
