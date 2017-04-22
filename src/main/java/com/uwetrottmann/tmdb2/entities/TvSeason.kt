package com.uwetrottmann.tmdb2.entities

import java.util.Date

class TvSeason {

    var id: Int? = null
    var air_date: Date? = null
    var episodes: List<TvEpisode>? = null
    var episode_count: Int? = null
    var name: String? = null
    var overview: String? = null
    var poster_path: String? = null
    var season_number: Int? = null
    var credits: Credits? = null
    var images: Images? = null
    var external_ids: ExternalIds? = null

}
