/*
 * Copyright 2015 Miguel Teixeira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.uwetrottmann.tmdb2.entities

import java.util.Date

class TvShowComplete : TvShow() {

    var created_by: List<Person>? = null
    var networks: List<Network>? = null
    var episode_run_time: List<Int>? = null
    var genres: List<Genre>? = null
    var homepage: String? = null
    var in_production: Boolean = false
    var languages: List<String>? = null
    var last_air_date: Date? = null
    var number_of_episodes: Int? = null
    var number_of_seasons: Int? = null
    var original_language: String? = null
    var overview: String? = null
    var production_companies: List<ProductionCompany>? = null
    var seasons: List<TvSeason>? = null
    var status: String? = null
    var type: String? = null
    var images: Images? = null
    var credits: Credits? = null

    // Following are used with append_to_response
    var external_ids: ExternalIds? = null
    var alternative_titles: TvAlternativeTitles? = null
    var content_ratings: TvContentRatings? = null
    var similar: TvResultsPage? = null
    var videos: Videos? = null
}
