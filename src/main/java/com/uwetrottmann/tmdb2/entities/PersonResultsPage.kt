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

class PersonResultsPage : BaseResultsPage() {

    class ResultsPage {

        var id: Int? = null
        var adult: Boolean? = null
        var name: String? = null
        var popularity: Double? = null
        var profile_path: String? = null
        var known_for: List<Media>? = null

    }

    var results: List<ResultsPage>? = null

}
