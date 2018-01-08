package ua.nedz.images

import java.io.*
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) {
    val timeMillis = measureTimeMillis {
        val root = File("images_${System.currentTimeMillis()}")

        readCountries().forEach {
            val folder = File(root, it)
            folder.mkdirs()
            try {
                getImages(it).forEach {
                    saveImage(it, folder)
                }
            } catch (e: Exception) {
                println("Exception occured: ${e.message}")
            }
        }
    }
    println("Programm run $timeMillis ms")
}