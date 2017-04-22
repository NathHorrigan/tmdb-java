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

import com.uwetrottmann.tmdb2.entities.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Observable

interface TvEpisodesService {

    /**
     * Get the primary information about a TV episode by combination of a season and episode number.

     * @param showId A themoviedb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("tv/{id}/season/{season_number}/episode/{episode_number}")
    fun episode(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int,
            @Path("episode_number") episodeNumber: Int,
            @Query("language") language: String,
            @Query("append_to_response") appendToResponse: AppendToResponse
    ): Observable<TvEpisode>

    /**
     * Get the TV episode credits by combination of season and episode number.

     * @param showId A themoviedb id.
     */
    @GET("tv/{id}/season/{season_number}/episode/{episode_number}/credits")
    fun credits(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int,
            @Path("episode_number") episodeNumber: Int
    ): Observable<Credits>

    /**
     * Get the external ids for a TV episode by combination of a season and episode number.

     * @param showId A themoviedb id.
     */
    @GET("tv/{id}/season/{season_number}/episode/{episode_number}/external_ids")
    fun externalIds(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int,
            @Path("episode_number") episodeNumber: Int
    ): Observable<ExternalIds>

    /**
     * Get the images (episode stills) for a TV episode by combination of a season and episode number. Since episode
     * stills don't have a language, this call will always return all images.

     * @param showId A themoviedb id.
     */
    @GET("tv/{id}/season/{season_number}/episode/{episode_number}/images")
    fun images(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int,
            @Path("episode_number") episodeNumber: Int
    ): Observable<Images>

    /**
     * Get the videos that have been added to a TV episode (teasers, clips, etc...)

     * @param showId A themoviedb id.
     */
    @GET("tv/{id}/season/{season_number}/episode/{episode_number}/videos")
    fun videos(
            @Path("id") showId: Int,
            @Path("season_number") seasonNumber: Int,
            @Path("episode_number") episodeNumber: Int
    ): Observable<Videos>

}
