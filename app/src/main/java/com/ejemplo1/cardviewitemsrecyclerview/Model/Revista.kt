package com.ejemplo1.cardviewitemsrecyclerview.Model

public class Revista {
    var id = 0
    var title: String? = null
    var urlImgCover: String? = null
    var volume: String? = null
    var year: String? = null
    var number: String? = null

    constructor() {}
    constructor(
        title: String?,
        urlImgCover: String?,
        volume: String?,
        year: String?,
        number: String?
    ) {
        this.title = title
        this.urlImgCover = urlImgCover
        this.volume = volume
        this.year = year
        this.number = number
    }
}
