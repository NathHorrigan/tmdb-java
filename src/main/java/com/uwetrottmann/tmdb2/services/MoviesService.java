package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.*;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Observable;

public interface MoviesService {

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param tmdbId TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("movie/{id}")
    Observable<Movie> summary(
            @Path("id") int tmdbId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the alternative titles for a specific movie id.
     *
     * @param tmdbId TMDb id.
     * @param country <em>Optional.</em> ISO 3166-1 code.
     */
    @GET("movie/{id}/alternative_titles")
    Observable<MovieAlternativeTitles> alternativeTitles(
            @Path("id") int tmdbId,
            @Query("country") String country
    );

    /**
     * Get the cast and crew information for a specific movie id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("movie/{id}/credits")
    Observable<Credits> credits(
            @Path("id") int tmdbId
    );

    /**
     * Get the images (posters and backdrops) for a specific movie id.
     *
     * @param tmdbId TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{id}/images")
    Observable<Images> images(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the plot keywords for a specific movie id.
     *
     * @param tmdbId TMDb id.
     */
    @GET("movie/{id}/keywords")
    Observable<MovieKeywords> keywords(
            @Path("id") int tmdbId
    );

    /**
     * Get the release dates, certifications and related information by country for a specific movie id.
     *
     * The results are keyed by iso_3166_1 code and contain a type value which on our system, maps to:
     * {@link ReleaseDate#TYPE_PREMIERE}, {@link ReleaseDate#TYPE_THEATRICAL_LIMITED},
     * {@link ReleaseDate#TYPE_THEATRICAL}, {@link ReleaseDate#TYPE_DIGITAL}, {@link ReleaseDate#TYPE_PHYSICAL},
     * {@link ReleaseDate#TYPE_TV}
     *
     * @param tmdbId TMDb id.
     */
    @GET("movie/{id}/release_dates")
    Observable<ReleaseDatesResults> releaseDates(
            @Path("id") int tmdbId
    );

    /**
     * Get the videos (trailers, teasers, clips, etc...) for a specific movie id.
     *
     * @param tmdbId TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{id}/videos")
    Observable<Videos> videos(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the translations for a specific movie id.
     *
     * @param tmdbId TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("movie/{id}/translations")
    Observable<Translations> translations(
            @Path("id") int tmdbId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param tmdbId TMDb id.
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{id}/similar")
    Observable<MovieResultsPage> similar(
            @Path("id") int tmdbId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param tmdbId TMDb id.
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{id}/reviews")
    Observable<ReviewResultsPage> reviews(
            @Path("id") int tmdbId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param tmdbId TMDb id.
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{id}/lists")
    Observable<ListResultsPage> lists(
            @Path("id") int tmdbId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the latest movie id.
     */
    @GET("movie/latest")
    Observable<Movie> latest();

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/upcoming")
    Observable<MovieResultsPage> upcoming(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/now_playing")
    Observable<MovieResultsPage> nowPlaying(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/popular")
    Observable<MovieResultsPage> popular(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/top_rated")
    Observable<MovieResultsPage> topRated(
            @Query("page") Integer page,
            @Query("language") String language
    );

}
