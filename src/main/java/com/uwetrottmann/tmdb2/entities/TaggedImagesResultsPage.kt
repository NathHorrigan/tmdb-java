package com.uwetrottmann.tmdb2.entities

class TaggedImagesResultsPage {
    inner class TaggedImage : Image() {
        var id: String? = null
        var image_type: String? = null
        var media_type: String? = null
        var media: Media? = null
    }

    var id: Int = 0
    var page: Int = 0
    var results: List<TaggedImage>? = null
}
