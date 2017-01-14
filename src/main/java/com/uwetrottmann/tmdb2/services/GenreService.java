package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.GenreResults;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GenreService {

    /**
     * Get the list of movie genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/movie/list")
    Observable<GenreResults> movie(
            @Query("language") String language
    );

    /**
     * Get the list of TV genres.
     *
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("genre/tv/list")
    Observable<GenreResults> tv(
            @Query("language") String language
    );
}
