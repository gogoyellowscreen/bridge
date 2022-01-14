package drawing

data class Coordinate(val x: Double, val y: Double)

interface DrawingApi {
    fun getDrawingAreaWidth(): Double
    fun getDrawingAreaHeight(): Double
    fun drawCircle(leftTop: Coordinate, radius: Int)
    fun drawLine(begin: Coordinate, end: Coordinate)
}
