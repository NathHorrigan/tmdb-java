package com.uwetrottmann.tmdb2.entities

import com.uwetrottmann.tmdb2.enumerations.Status

import java.util.Date

class Movie {

    var id: Int? = null

    var adult: Boolean? = null
    var backdrop_path: String? = null
    var belongs_to_collection: Collection? = null
    var budget: Int? = null
    var genres: List<Genre>? = null
    var homepage: String? = null
    var imdb_id: String? = null
    var original_title: String? = null
    var overview: String? = null
    var popularity: Double? = null
    var poster_path: String? = null
    var production_companies: List<ProductionCompany>? = null
    var production_countries: List<ProductionCountry>? = null
    var release_date: Date? = null
    var revenue: Int? = null
    var runtime: Int? = null
    var spoken_languages: List<SpokenLanguage>? = null
    var status: Status? = null
    var tagline: String? = null
    var title: String? = null
    var vote_average: Double? = null
    var vote_count: Int? = null

    // Following are used with append_to_response
    var alternative_titles: MovieAlternativeTitles? = null
    var credits: Credits? = null
    var release_dates: ReleaseDatesResults? = null
    var similar: MovieResultsPage? = null
    var videos: Videos? = null
}
