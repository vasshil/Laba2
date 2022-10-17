package figures

import data.Vector

abstract class Figure {

    abstract var x: Int
    abstract var y: Int

    abstract var width: Int
    abstract var height: Int

    abstract val vector: Vector

    abstract val dA: Float
    var angle = 0F

    abstract fun rotate()
    abstract fun move()

    abstract fun checkForReflection(screenWidth: Int, screenHeight: Int): Boolean

}