package com.uwetrottmann.tmdb2.entities

class Translations {

    class Translation {
        var iso_639_1: String? = null
        var name: String? = null
        var english_name: String? = null
    }

    var id: Int? = null
    var translations: List<Translation>? = null
}
