package drawing

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.layout.StackPane
import javafx.stage.Stage

typealias FxInitCallback = (FxDrawingApi) -> Unit

class FxDrawingApi() : Application(), DrawingApi {
    private var context: GraphicsContext? = null
    private val xCenter get() = width / 2
    private val yCenter get() = height / 2

    constructor(pWidth: Double,
                pHeight: Double,
                pCallback: FxInitCallback) : this() {
        width = pWidth
        height = pHeight
        callback = pCallback
        launch()
    }

    override fun getDrawingAreaWidth() = width

    override fun getDrawingAreaHeight() = height

    override fun start(primaryStage: Stage) {
        val pane = StackPane()
        val canvas = Canvas()
        pane.children.add(canvas)
        canvas.height = height
        canvas.width = width
        pane.setMinSize(100.0, 100.0)
        val scene = Scene(pane)
        primaryStage.title = "Graph plot"
        primaryStage.isResizable = false
        primaryStage.scene = scene
        primaryStage.sizeToScene()
        primaryStage.show()
        context = canvas.graphicsContext2D
        callback?.invoke(this)
    }

    override fun drawCircle(leftTop: Coordinate, radius: Double) {
        context?.fillOval(
            xCenter + leftTop.x,
            yCenter + leftTop.y,
            radius,
            radius
        )
    }

    override fun drawLine(begin: Coordinate, end: Coordinate) {
        context?.strokeLine(
            xCenter + begin.x, yCenter + begin.y,
            xCenter + end.x, yCenter + end.y
        )
    }

    companion object {
        private var width: Double = 1000.0
        private var height: Double = 800.0
        private var callback: FxInitCallback? = null
    }
}
