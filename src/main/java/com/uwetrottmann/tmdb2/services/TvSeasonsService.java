package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.*;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Observable;

public interface TvSeasonsService {

    /**
     * Get the primary information about a TV season by its season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result.
     */
    @GET("tv/{id}/season/{season_number}")
    Observable<TvSeason> season(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the cast and crew credits for a TV season by season number.
     *
     * @param showId A themoviedb id.
     */
    @GET("tv/{id}/season/{season_number}/credits")
    Observable<Credits> credits(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber
    );

    /**
     * Get the external ids that we have stored for a TV season by season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/external_ids")
    Observable<ExternalIds> externalIds(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );

    /**
     * Get the images (posters) that we have stored for a TV season by season number.
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/images")
    Observable<Images> images(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );

    /**
     * Get the videos that have been added to a TV season (trailers, teasers, etc...)
     *
     * @param showId A themoviedb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("tv/{id}/season/{season_number}/videos")
    Observable<Videos> videos(
            @Path("id") int showId,
            @Path("season_number") int seasonNumber,
            @Query("language") String language
    );

}
