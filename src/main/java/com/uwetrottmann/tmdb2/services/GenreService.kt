package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.GenreResults
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface GenreService {

    /**
     * Get the list of movie genres.

     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("genre/movie/list")
    fun movie(
            @Query("language") language: String
    ): Observable<GenreResults>

    /**
     * Get the list of TV genres.

     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("genre/tv/list")
    fun tv(
            @Query("language") language: String
    ): Observable<GenreResults>
}
