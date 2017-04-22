package com.uwetrottmann.tmdb2.entities

import java.util.Date

class TvEpisode {

    var id: Int? = null

    var air_date: Date? = null
    var crew: List<CrewMember>? = null
    var episode_number: Int? = null
    var guest_stars: List<CastMember>? = null
    var name: String? = null
    var overview: String? = null
    var production_code: String? = null
    var season_number: Int? = null
    var still_path: String? = null
    var vote_average: Double? = null
    var vote_count: Int? = null
    var images: Images? = null
    var external_ids: ExternalIds? = null
    var credits: Credits? = null

}
