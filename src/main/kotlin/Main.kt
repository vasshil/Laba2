import data.Vector
import figures.Circle
import figures.Figure
import figures.Rectangle
import view.GraphicsWindow
import view.InfoWindow
import java.util.*

fun main() {

    val figuresCount = 27
    val width = 700
    val height = 700

    val frameSleepTime = 9L

    val figMaxWidth = 100
    val figMaxHeight = 100

    val dxyMin = 2
    val dxyMax = 6

    val minAngle = (Math.PI / 100).toFloat()
    val maxAngle = (Math.PI / 50).toFloat()


    val figuresList = mutableListOf<Figure>()

    var circlesReflections = 0
    var rectangleReflections = 0

    var circlesCount = 0
    var rectCount = 0

    for (i in 1..figuresCount) {

        val figType = r(2)

        if (figType == 0) { // circle

            circlesCount++

            val figWidth = r(figMaxWidth) + 1

            val figX = r(width - figWidth) + figWidth / 2
            val figY = r(height - figWidth) + figWidth / 2

            val vector = randomVector(dxyMin, dxyMax)
            val angle = 0F


            figuresList += Circle(figX, figY, figWidth, figWidth, vector, angle)

        } else { // rectangle

            rectCount++

            val figWidth = r(figMaxWidth) + 1
            val figHeight = r(figMaxHeight) + 1

            val figX = r(width - figWidth) + figWidth / 2
            val figY = r(height - figHeight) + figHeight / 2

            val vector = randomVector(dxyMin, dxyMax)
            val dA = randomAngle(minAngle, maxAngle) * (if (r(2) == 0) { 1 } else { -1 })

            figuresList += Rectangle(figX, figY, figWidth, figHeight, vector, dA)

        }

    }


    val graphicsWindow = GraphicsWindow(width, height, figuresList)

    val infoWindow = InfoWindow(circlesCount, rectCount)
    infoWindow.setCirclesReflectionCount(circlesReflections)
    infoWindow.setRectReflectionCount(rectangleReflections)



    while (true) {

        for (figure in figuresList) {

            figure.move()
            figure.rotate()

            if (figure is Circle) {

                if (figure.checkForReflection(graphicsWindow.canvas.width, graphicsWindow.canvas.height)) {
                    circlesReflections++
                    infoWindow.setCirclesReflectionCount(circlesReflections)
                }

            } else if (figure is Rectangle) {

                if (figure.checkForReflection(graphicsWindow.canvas.width, graphicsWindow.canvas.height)) {
                    rectangleReflections++
                    infoWindow.setRectReflectionCount(rectangleReflections)
                }

            }

        }

        graphicsWindow.repaint()

        Thread.sleep(frameSleepTime)

    }



}




fun r(bound: Int): Int {
    return Random().nextInt(bound)
}

fun randomVector(min: Int, max: Int): Vector {
    return Vector(
        Random().nextInt(max - min + 1) + min,
        Random().nextInt(max - min + 1) + min
    )

}

fun randomAngle(min: Float, max: Float): Float {
    return min + Random().nextFloat() * max
}
