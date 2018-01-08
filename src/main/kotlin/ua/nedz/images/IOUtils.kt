package ua.nedz.images

import java.io.*
import java.net.URL

fun readCountries(): List<String> {
    val countries = mutableListOf<String>()
    BufferedReader(FileReader("input.txt")).use {
        it.lines().forEach { country -> countries.add(country) }
    }
    return countries
}

fun saveImage(imageUrl: String, folder: File) {
    val url = URL(imageUrl)
    val fileName = url.file
    val destination = File(folder, fileName.substring(fileName.lastIndexOf("/") + 1))

    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null
    try {
        inputStream = url.openStream()
        outputStream = FileOutputStream(destination)
        val b = ByteArray(2048)
        var length = inputStream.read(b)

        while (length != -1) {
            outputStream.write(b, 0, length)
            length = inputStream.read(b)
        }
    } finally {
        inputStream?.close()
        outputStream?.close()
    }
}