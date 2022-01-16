package drawing

import java.awt.Canvas
import java.awt.Graphics
import java.awt.Toolkit
import java.awt.image.BufferStrategy
import javax.swing.JFrame
import javax.swing.JLayeredPane

class AwtDrawingApi(private val width: Double,
                    private val height: Double) : DrawingApi {
    private val bufferStrategy: BufferStrategy
    private val graphics: Graphics
    private val xCenter get() = width / 2
    private val yCenter get() = height / 2

    init {
        val toolkit: Toolkit = Toolkit.getDefaultToolkit()
        val screenD = toolkit.screenSize
        val frame = JFrame("Graph plot")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocation(
            ((screenD.width - width) / 2).toInt(), (
                    (screenD.height - height) / 2).toInt()
        )
        frame.setSize(width.toInt(), height.toInt())
        frame.isResizable = false
        frame.isVisible = true
        val panel = JLayeredPane()
        panel.layout = null
        val canvas = Canvas()
        canvas.setSize(width.toInt(), height.toInt())
        panel.add(canvas, 1)
        frame.add(panel)
        frame.isVisible = true
        canvas.createBufferStrategy(3)
        bufferStrategy = canvas.bufferStrategy
        graphics = bufferStrategy.drawGraphics
        bufferStrategy.show()
    }

    override fun getDrawingAreaWidth() = width

    override fun getDrawingAreaHeight() = height

    override fun drawCircle(leftTop: Coordinate, radius: Double) {
        val ix = (xCenter + leftTop.x).toInt()
        val iy = (yCenter + leftTop.y).toInt()
        graphics.fillOval(ix, iy, radius.toInt(), radius.toInt())
        bufferStrategy.show()
    }

    override fun drawLine(begin: Coordinate, end: Coordinate) {
        graphics.drawLine(
            (xCenter + begin.x).toInt(), (yCenter + begin.y).toInt(),
            (xCenter + end.x).toInt(), (yCenter + end.y).toInt()
        )
        bufferStrategy.show()
    }
}
