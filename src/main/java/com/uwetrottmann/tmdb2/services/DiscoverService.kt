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
 */

package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.AppendToDiscoverResponse
import com.uwetrottmann.tmdb2.entities.MovieResultsPage
import com.uwetrottmann.tmdb2.entities.TmdbDate
import com.uwetrottmann.tmdb2.entities.TvResultsPage
import com.uwetrottmann.tmdb2.enumerations.SortBy
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface DiscoverService {

    /**
     * Discover movies by different types of data like average rating, number of votes and genres.

     * @param includeAdult *Optional.* Toggle the inclusion of adult titles. Expected value is a boolean, true or
     * * false. Default is false.
     * *
     * @param includeVideo *Optional.* Toggle the inclusion of items marked as a video. Expected value is a
     * * boolean, true or false. Default is true.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param page *Optional.* Minimum 1, maximum 1000.
     * *
     * @param primaryReleaseYear *Optional.* Filter the results so that only the primary release date year has
     * * this value. Expected value is a year.
     * *
     * @param primaryReleaseYearGte *Optional.* Filter by the primary release date and only include those which
     * * are greater than or equal to the specified value. Expected format is YYYY-MM-DD.
     * *
     * @param primaryReleaseYearLte *Optional.* Filter by the primary release date and only include those which
     * * are less or equal to the specified value. Expected format is YYYY-MM-DD.
     * *
     * @param releaseDateGte *Optional.* Filter by all available release dates and only include those which are
     * * greater or equal to the specified value. Expected format is YYYY-MM-DD.
     * *
     * @param releaseDateLte *Optional.* Filter by all available release dates and only include those which are
     * * less or equal to the specified value. Expected format is YYYY-MM-DD.
     * *
     * @param sortBy *Optional.* Available options are: popularity.asc, popularity.desc, release_date.asc,
     * * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc, vote_count.asc, vote_count.desc
     * *
     * @param voteCountGte *Optional.* Filter movies by their vote count and only include movies that have a vote
     * * count that is equal to or lower than the specified value.
     * *
     * @param voteCountLte *Optional.* Filter movies by their vote count and only include movies that have a vote
     * * count that is equal to or lower than the specified value. Expected value is an integer.
     * *
     * @param voteAverageGte *Optional.* Filter movies by their vote average and only include those that have an
     * * average rating that is equal to or higher than the specified value. Expected value is a float.
     * *
     * @param voteAverageLte *Optional.* Filter movies by their vote average and only include those that have an
     * * average rating that is equal to or lower than the specified value. Expected value is a float.
     * *
     * @param withCast *Optional.* Only include movies that have this person id added as a cast member. Expected
     * * value is an integer (the id of a person).
     * *
     * @param withCrew *Optional.* Only include movies that have this person id added as a crew member. Expected
     * * value is an integer (the id of a person).
     * *
     * @param withCompanies *Optional.* Filter movies to include a specific company. Expected value is an integer
     * * (the id of a company).
     * *
     * @param withGenres *Optional.* Only include movies with the specified genres. Expected value is an integer
     * * (the id of a genre). Multiple values can be specified.
     * *
     * @param withKeywords *Optional.* Only include movies with the specified genres. Expected value is an
     * * integer (the id of a genre). Multiple values can be specified.
     * *
     * @param withPeople *Optional.* Only include movies that have these person id's added as a cast or crew
     * * member. Expected value is an integer (the id or ids of a person).
     * *
     * @param year *Optional.* Filter the results by all available release dates that have the specified value
     * * added as a year. Expected value is an integer (year).
     */
    @GET("discover/movie")
    fun discoverMovie(
            @Query("include_adult") includeAdult: Boolean,
            @Query("include_video") includeVideo: Boolean,
            @Query("language") language: String,
            @Query("page") page: Int?,
            @Query("primary_release_year") primaryReleaseYear: String,
            @Query("primary_release_date.gte") primaryReleaseYearGte: TmdbDate,
            @Query("primary_release_date.lte") primaryReleaseYearLte: TmdbDate,
            @Query("release_date.gte") releaseDateGte: TmdbDate,
            @Query("release_date.lte") releaseDateLte: TmdbDate,
            @Query("sort_by") sortBy: SortBy,
            @Query("vote_count.gte") voteCountGte: Int?,
            @Query("vote_count.lte") voteCountLte: Int?,
            @Query("vote_average.gte") voteAverageGte: Float?,
            @Query("vote_average.lte") voteAverageLte: Float?,
            @Query("with_cast") withCast: AppendToDiscoverResponse,
            @Query("with_crew") withCrew: AppendToDiscoverResponse,
            @Query("with_companies") withCompanies: AppendToDiscoverResponse,
            @Query("with_genres") withGenres: AppendToDiscoverResponse,
            @Query("with_keywords") withKeywords: AppendToDiscoverResponse,
            @Query("with_people") withPeople: AppendToDiscoverResponse,
            @Query("year") year: Int?
    ): Observable<MovieResultsPage>

    /**
     * Discover TV shows by different types of data like average rating, number of votes, genres, the network they aired
     * on and air dates.

     * @param page *Optional.* Minimum 1, maximum 1000.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param sortBy *Optional.* Available options are: popularity.asc, popularity.desc, release_date.asc,
     * * release_date.desc, revenue.asc, revenue.desc, primary_release_date.asc, primary_release_date.desc,
     * * original_title.asc, original_title.desc, vote_average.asc, vote_average.desc, vote_count.asc, vote_count.desc
     * *
     * @param firstAirDateYear *Optional.* Filter the results release dates to matches that include this value.
     * * Expected value is a year.
     * *
     * @param voteCountGte *Optional.* Only include TV shows that are equal to, or have a vote count higher than
     * * this value. Expected value is an integer.
     * *
     * @param voteAverageGte *Optional.* Only include TV shows that are equal to, or have a higher average rating
     * * than this value. Expected value is a float.
     * *
     * @param withGenres *Optional.* Only include TV shows with the specified genres. Expected value is an
     * * integer (the id of a genre). Multiple values can be specified.
     * *
     * @param withNetworks *Optional.* Filter TV shows to include a specific network. Expected value is an
     * * integer (the id of a network).
     * *
     * @param firstAirDateGte *Optional.* The minimum release to include. Expected format is YYYY-MM-DD.
     * *
     * @param firstAirDateLte *Optional.* The maximum release to include. Expected format is YYYY-MM-DD.
     */
    @GET("discover/tv")
    fun discoverTv(
            @Query("page") page: Int?,
            @Query("language") language: String,
            @Query("sort_by") sortBy: SortBy,
            @Query("first_air_date_year") firstAirDateYear: String,
            @Query("vote_count.gte") voteCountGte: Int?,
            @Query("vote_average.gte") voteAverageGte: Float?,
            @Query("with_genres") withGenres: AppendToDiscoverResponse,
            @Query("with_networks") withNetworks: AppendToDiscoverResponse,
            @Query("first_air_date.gte") firstAirDateGte: TmdbDate,
            @Query("first_air_date.lte") firstAirDateLte: TmdbDate
    ): Observable<TvResultsPage>

}
