package view

import figures.Figure
import java.awt.Dimension
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import javax.swing.JFrame

class GraphicsWindow(width: Int, height: Int, figuresList: MutableList<Figure>): JFrame(), ComponentListener {

    var canvas: Canvas

    init {

        canvas = Canvas(figuresList)
        canvas.preferredSize = Dimension(width, height)
        contentPane.add(canvas)

        pack()
        addComponentListener(this)

        title = "Лаба 2"
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE

        isVisible = true

    }

    override fun componentResized(e: ComponentEvent?) {
        println("resized ${canvas.width} ${canvas.height}")
    }

    override fun componentMoved(e: ComponentEvent?) {}

    override fun componentShown(e: ComponentEvent?) {}

    override fun componentHidden(e: ComponentEvent?) {}


}