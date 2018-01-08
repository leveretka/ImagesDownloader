package ua.nedz.images

import org.jsoup.Jsoup

const val SELECTOR = "div.search_results section.post_content img.photo"
const val SITE = "https://www.tumblr.com/search"

fun String.toSearchString() = replace(" ", "+")

fun getImages(country: String): List<String> {
    val doc = Jsoup.connect("$SITE/${country.toSearchString()}+landscape").timeout(10000).get()
    val elements = doc.select(SELECTOR)

    return elements
            .map { it.attr("src") }
            .filter { it.startsWith("http") }

}