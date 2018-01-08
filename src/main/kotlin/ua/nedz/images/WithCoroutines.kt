package ua.nedz.images

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.io.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val timeMillis = measureTimeMillis {
        runBlocking {
            val root = File("images_${System.currentTimeMillis()}")

            readCountries().forEach {
                val folder = File(root, it)
                folder.mkdirs()
                try {
                    getImages(it).forEach {
                        async {
                            saveImage(it, folder)
                        }
                    }
                } catch (e: Exception) {
                    println("Exception occured: ${e.message}")
                }
            }
        }
    }
    println("Programm run $timeMillis ms")
}