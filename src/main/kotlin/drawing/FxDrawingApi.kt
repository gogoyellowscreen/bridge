package drawing

class FxDrawingApi(private val width: Double,
                   private val height: Double) : DrawingApi {
    override fun getDrawingAreaWidth() = width

    override fun getDrawingAreaHeight() = height

    override fun drawCircle(leftTop: Coordinate, radius: Double) {
        TODO("Not yet implemented")
    }

    override fun drawLine(begin: Coordinate, end: Coordinate) {
        TODO("Not yet implemented")
    }

    override fun drawNodeNumber(nodeCenter: Coordinate, node: Int) {
        TODO("Not yet implemented")
    }
}
