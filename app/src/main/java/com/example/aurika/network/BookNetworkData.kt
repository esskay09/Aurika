package com.example.aurika.network

import com.example.aurika.ui.BookDomain
import com.squareup.moshi.Json

data class Items(@Json(name = "items") val booksNetwork: List<BookInfo>)
data class BookInfo(@Json(name = "volumeInfo") val volumeInfo: VolumeInfo)
data class VolumeInfo(
    @Json(name = "title") val title: String = "ERROR100",
    @Json(name = "description") val description: String = "ERROR100",
    @Json(name = "authors") val authors: List<String>? = null,
    @Json(name = "averageRating") val rating: Double = -1.0,
    @Json(name= "imageLinks") val imageLinkscontainer: Thumbnails ?= null
)

data class Thumbnails(@Json(name = "thumbnail") val imageLink: String= "ERROR100")

fun Items.asDomainModel(): List<BookDomain> {
    return booksNetwork.map {
        BookDomain(
            name = it.volumeInfo.title,
            description = trimDescription(it.volumeInfo.description),
            authors = it.volumeInfo.authors,
            rating = it.volumeInfo.rating,
            imgSrc = appendImgSrcWithHttps(it.volumeInfo.imageLinkscontainer?.imageLink ?: "ERROR100")
        )
    }
}

fun trimDescription(string: String) = string.take(400)
fun appendImgSrcWithHttps(string: String) : String{
    //https://books.google.com/books/content?id=tOqs6nXnpiEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api

    return if (string.contains("http:")) {

        "https" + string.substringAfter("http")

    } else string

}


