package view

import figures.Circle
import figures.Figure
import figures.Rectangle
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Polygon
import javax.swing.JPanel

class Canvas(private val figuresList: MutableList<Figure>): JPanel() {

    private lateinit var graphics: Graphics2D

    private val strokeWidth = 6F

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        graphics = g as Graphics2D
        graphics.stroke = BasicStroke(strokeWidth)


        for (figure in figuresList) {

            if (figure is Circle) {
                drawCircle(figure)
            } else if (figure is Rectangle) {
                drawRectangle(figure)
            }

        }

    }

    private fun drawCircle(circle: Circle) {
        graphics.color = Color.RED
        graphics.fillOval(circle.x - circle.width / 2, circle.y - circle.width / 2, circle.width, circle.height)

        graphics.color = Color.BLACK
        graphics.drawOval(circle.x - circle.width / 2, circle.y - circle.width / 2, circle.width, circle.height)
    }

    private fun drawRectangle(rectangle: Rectangle) {
//        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height)

        val polygon = Polygon()


        for (point in rectangle.pointsRotated) {
            polygon.addPoint(point.x, point.y)
        }

        graphics.color = Color.BLUE
        graphics.fillPolygon(polygon)

        graphics.color = Color.BLACK
        graphics.drawPolygon(polygon)

    }


}