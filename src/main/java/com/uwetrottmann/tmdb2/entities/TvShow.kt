package com.uwetrottmann.tmdb2.entities

import java.util.Date

open class TvShow {

    var id: Int? = null
    var original_name: String? = null
    var name: String? = null
    var origin_country: List<String>? = null
    var first_air_date: Date? = null
    var backdrop_path: String? = null
    var poster_path: String? = null
    var popularity: Double? = null
    var vote_average: Double? = null
    var vote_count: Int? = null

}
