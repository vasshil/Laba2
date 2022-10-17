package figures

import data.Vector
import java.awt.Point
import kotlin.math.cos
import kotlin.math.sin

class Rectangle(override var x: Int,
                override var y: Int,
                override var width: Int,
                override var height: Int,
                override val vector: Vector,
                override val dA: Float
) : Figure() {

    private val points = mutableListOf(
        Point(x - width / 2, y - height / 2),
        Point(x - width / 2, y + height / 2),
        Point(x + width / 2, y + height / 2),
        Point(x + width / 2, y - height / 2)
    )

    val pointsRotated = MutableList(4) { Point(0, 0) }


    override fun rotate() {
        angle += dA

        for (i in 0 until pointsRotated.size) {
            pointsRotated[i].x = ((points[i].x - x) * cos(angle) - (points[i].y - y) * sin(angle) + x).toInt()
            pointsRotated[i].y = ((points[i].x - x) * sin(angle) + (points[i].y - y) * cos(angle) + y).toInt()
        }

    }

    override fun move() {
        x += vector.x
        y += vector.y

        for (i in 0 until pointsRotated.size) {
            points[i].x += vector.x
            points[i].y += vector.y
        }
    }

    override fun checkForReflection(screenWidth: Int, screenHeight: Int): Boolean {

        if (getRightX() >= screenWidth) {
            vector.reverseX()

            val dx = getRightX() - screenWidth + 1

            x -= dx

            for (i in 0 until pointsRotated.size) {
                points[i].x -= dx
            }

            return true
        }

        if (getLeftX() <= 0) {
            vector.reverseX()

            val dx = getLeftX() - 1

            x -= dx

            for (i in 0 until pointsRotated.size) {
                points[i].x -= dx
            }

            return true
        }

        if (getBottomY() >= screenHeight) {
            vector.reverseY()

            val dy = getBottomY() - screenHeight + 1

            y -= dy

            for (i in 0 until pointsRotated.size) {
                points[i].y -= dy
            }

            return true
        }

        if (getTopY() <= 0) {
            vector.reverseY()

            val dy = getTopY() - 1

            y -= dy

            for (i in 0 until pointsRotated.size) {
                points[i].y -= dy
            }

            return true
        }

        return false
    }

    private fun getLeftX(): Int {
        return pointsRotated.minWith(Comparator.comparingInt { it.x }).x
    }

    private fun getRightX(): Int {
        return pointsRotated.maxWith(Comparator.comparingInt { it.x }).x
    }

    private fun getTopY(): Int {
        return pointsRotated.minWith(Comparator.comparingInt { it.y }).y
    }

    private fun getBottomY(): Int {
        return pointsRotated.maxWith(Comparator.comparingInt { it.y }).y
    }

}