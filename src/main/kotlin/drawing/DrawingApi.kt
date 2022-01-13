package drawing

data class Coordinate(val x: Double, val y: Double)

interface DrawingApi {
    fun getDrawingAreaWidth(): Double
    fun getDrawingAreaHeight(): Double
    fun drawCircle(leftTop: Coordinate, radius: Double)
    fun drawLine(begin: Coordinate, end: Coordinate)
    fun drawNodeNumber(nodeCenter: Coordinate, node: Int)
}
