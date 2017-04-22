package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Observable

interface MoviesService {

    /**
     * Get the basic movie information for a specific movie id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("movie/{id}")
    fun summary(
            @Path("id") tmdbId: Int,
            @Query("language") language: String,
            @Query("append_to_response") appendToResponse: AppendToResponse
    ): Observable<Movie>

    /**
     * Get the alternative titles for a specific movie id.

     * @param tmdbId TMDb id.
     * *
     * @param country *Optional.* ISO 3166-1 code.
     */
    @GET("movie/{id}/alternative_titles")
    fun alternativeTitles(
            @Path("id") tmdbId: Int,
            @Query("country") country: String
    ): Observable<MovieAlternativeTitles>

    /**
     * Get the cast and crew information for a specific movie id.

     * @param tmdbId TMDb id.
     */
    @GET("movie/{id}/credits")
    fun credits(
            @Path("id") tmdbId: Int
    ): Observable<Credits>

    /**
     * Get the images (posters and backdrops) for a specific movie id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/{id}/images")
    fun images(
            @Path("id") tmdbId: Int,
            @Query("language") language: String
    ): Observable<Images>

    /**
     * Get the plot keywords for a specific movie id.

     * @param tmdbId TMDb id.
     */
    @GET("movie/{id}/keywords")
    fun keywords(
            @Path("id") tmdbId: Int
    ): Observable<MovieKeywords>

    /**
     * Get the release dates, certifications and related information by country for a specific movie id.

     * The results are keyed by iso_3166_1 code and contain a type value which on our system, maps to:
     * [ReleaseDate.TYPE_PREMIERE], [ReleaseDate.TYPE_THEATRICAL_LIMITED],
     * [ReleaseDate.TYPE_THEATRICAL], [ReleaseDate.TYPE_DIGITAL], [ReleaseDate.TYPE_PHYSICAL],
     * [ReleaseDate.TYPE_TV]

     * @param tmdbId TMDb id.
     */
    @GET("movie/{id}/release_dates")
    fun releaseDates(
            @Path("id") tmdbId: Int
    ): Observable<ReleaseDatesResults>

    /**
     * Get the videos (trailers, teasers, clips, etc...) for a specific movie id.

     * @param tmdbId TMDb id.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/{id}/videos")
    fun videos(
            @Path("id") tmdbId: Int,
            @Query("language") language: String
    ): Observable<Videos>

    /**
     * Get the translations for a specific movie id.

     * @param tmdbId TMDb id.
     * *
     * @param appendToResponse *Optional.* extra requests to append to the result.
     */
    @GET("movie/{id}/translations")
    fun translations(
            @Path("id") tmdbId: Int,
            @Query("append_to_response") appendToResponse: AppendToResponse
    ): Observable<Translations>

    /**
     * Get the similar movies for a specific movie id.

     * @param tmdbId TMDb id.
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/{id}/similar")
    fun similar(
            @Path("id") tmdbId: Int,
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<MovieResultsPage>

    /**
     * Get the reviews for a particular movie id.

     * @param tmdbId TMDb id.
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/{id}/reviews")
    fun reviews(
            @Path("id") tmdbId: Int,
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<ReviewResultsPage>

    /**
     * Get the lists that the movie belongs to.

     * @param tmdbId TMDb id.
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/{id}/lists")
    fun lists(
            @Path("id") tmdbId: Int,
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<ListResultsPage>

    /**
     * Get the latest movie id.
     */
    @GET("movie/latest")
    fun latest(): Observable<Movie>

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/upcoming")
    fun upcoming(
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<MovieResultsPage>

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/now_playing")
    fun nowPlaying(
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<MovieResultsPage>

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/popular")
    fun popular(
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<MovieResultsPage>

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.

     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("movie/top_rated")
    fun topRated(
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<MovieResultsPage>

}
