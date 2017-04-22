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

class Media {

    var id: Int? = null
    /** Only for movies.  */
    var adult: Boolean? = null
    var backdrop_path: String? = null
    var first_air_date: Date? = null
    var genre_ids: List<Int>? = null
    var name: String? = null
    var origin_country: List<String>? = null
    var original_language: String? = null
    var original_name: String? = null
    /** Only for movies.  */
    var original_title: String? = null
    var overview: String? = null
    /** Only for movies.  */
    var release_date: Date? = null
    var poster_path: String? = null
    var popularity: Double? = null
    /** Only for movies.  */
    var title: String? = null
    var vote_average: Double? = null
    var vote_count: Int? = null
    var media_type: String? = null

}
