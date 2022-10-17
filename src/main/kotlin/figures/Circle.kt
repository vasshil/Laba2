package figures

import data.Vector

class Circle(override var x: Int,
             override var y: Int,
             override var width: Int,
             override var height: Int,
             override val vector: Vector,
             override val dA: Float
) : Figure() {


    override fun rotate() {
        // нет смысла вращать круг
    }

    override fun move() {
        x += vector.x
        y += vector.y
    }

    override fun checkForReflection(screenWidth: Int, screenHeight: Int): Boolean {
        if (x + width / 2 >= screenWidth) {
            vector.reverseX()
            x = screenWidth - width / 2

            return true
        }
        if (x - width / 2 <= 0) {
            vector.reverseX()
            x = width / 2

            return true
        }

        if (y + height / 2 >= screenHeight) {
            vector.reverseY()
            y = screenHeight - height / 2

            return true
        }
        if (y - height / 2 <= 0) {
            vector.reverseY()
            y = height / 2

            return true
        }

        return false
    }

}