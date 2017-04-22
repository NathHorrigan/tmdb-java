package com.uwetrottmann.tmdb2.entities

import java.util.Date

class ReleaseDate {

    var certification: String? = null
    var iso_639_1: String? = null
    var note: String? = null
    var release_date: Date? = null
    var type: Int = 0

    companion object {
        var TYPE_PREMIERE = 1
        var TYPE_THEATRICAL_LIMITED = 2
        var TYPE_THEATRICAL = 3
        var TYPE_DIGITAL = 4
        var TYPE_PHYSICAL = 5
        var TYPE_TV = 6
    }
}
