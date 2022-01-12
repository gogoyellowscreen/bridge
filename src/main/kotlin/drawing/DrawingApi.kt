package drawing

data class Coordinate(val x: Int, val y: Int)

interface DrawingApi {
    fun getDrawingAreaWidth(): Long
    fun getDrawingAreaHeight(): Long
    fun drawCircle(leftTop: Coordinate, radius: Int)
    fun drawLine(begin: Coordinate, end: Coordinate)
}
