package com.example.aurika.ui

data class BookDomain(

    val id: Int = -1,
    val name:String = "ERROR100",
    val authors: List<String>?= null,
    val description: String = "ERROR100",
    val rating: Double = 0.0,
    val imgSrc: String = "ERROR100")
