package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.*;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Observable;

public interface TvService {

    /**
     * Get the primary information about a TV series by id.
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("tv/{id}")
    Observable<TvShowComplete> tv(
            @Path("id") int tmdbId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the alternative titles for a specific show ID.
     *
     * @param tmdbId A themoviedb id.
     */
    @GET("tv/{id}/alternative_titles")
    Observable<TvAlternativeTitles> alternativeTitles(
            @Path("id") int tmdbId
    );

    /**
     * Get the cast and crew information about a TV series. Just like the website, we pull this information from the
     * last season of the series.
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/credits")
    Observable<Credits> credits(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the content ratings for a specific TV show.
     *
     * @param tmbdId A themoviedb id
     */
    @GET("tv/{id}/content_ratings")
    Observable<TvContentRatings> content_ratings(
            @Path("id") int tmbdId
    );

    /**
     * Get the external ids that we have stored for a TV series.
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/external_ids")
    Observable<ExternalIds> externalIds(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the images (posters and backdrops) for a TV series.
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/images")
    Observable<Images> images(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the plot keywords for a specific TV show id.
     *
     * @param tmdbId A themoviedb id.
     */
    @GET("tv/{id}/keywords")
    Observable<TvKeywords> keywords(
            @Path("id") int tmdbId
    );

    /**
     * Get the similar TV shows for a specific tv id.
     *
     * @param tmdbId A themoviedb id.
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/similar")
    Observable<TvResultsPage> similar(
            @Path("id") int tmdbId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the videos that have been added to a TV series (trailers, opening credits, etc...)
     *
     * @param tmdbId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/videos")
    Observable<Videos> videos(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    /**
     * Get the latest TV show id.
     */
    @GET("tv/latest")
    Observable<TvShowComplete> latest();

    /**
     * Get the list of TV shows that are currently on the air. This query looks for any TV show that has an episode with
     * an air date in the next 7 days.
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/on_the_air")
    Observable<TvResultsPage> onTheAir(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of TV shows that air today. Without a specified timezone, this query defaults to EST (Eastern Time
     * UTC-05:00).
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/airing_today")
    Observable<TvResultsPage> airingToday(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of top rated TV shows. By default, this list will only include TV shows that have 2 or more votes.
     * This list refreshes every day.
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/top_rated")
    Observable<TvResultsPage> topRated(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular TV shows. This list refreshes every day.
     *
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/popular")
    Observable<TvResultsPage> popular(
            @Query("page") Integer page,
            @Query("language") String language
    );

}
