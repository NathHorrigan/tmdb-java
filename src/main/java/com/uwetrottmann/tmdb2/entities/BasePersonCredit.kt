package com.uwetrottmann.tmdb2.entities

import java.util.Date

abstract class BasePersonCredit {

    var credit_id: String? = null
    var id: Int? = null
    var media_type: String? = null

    // both
    var poster_path: String? = null

    // movies
    var adult: Boolean? = null
    var release_date: Date? = null
    var title: String? = null
    var original_title: String? = null

    // tv
    var first_air_date: Date? = null
    var name: String? = null
    var original_name: String? = null

}
