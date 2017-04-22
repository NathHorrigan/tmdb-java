package com.uwetrottmann.tmdb2.entities

class Configuration {

    class ImagesConfiguration {

        var base_url: String? = null
        var secure_base_url: String? = null
        var poster_sizes: List<String>? = null
        var backdrop_sizes: List<String>? = null
        var profile_sizes: List<String>? = null
        var logo_sizes: List<String>? = null
        var still_sizes: List<String>? = null
    }

    var images: ImagesConfiguration? = null
    var change_keys: List<String>? = null
}
