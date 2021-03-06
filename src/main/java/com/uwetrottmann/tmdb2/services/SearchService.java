package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.*;
import retrofit2.http.GET;
import retrofit2.http.Query;
import io.reactivex.Observable;

public interface SearchService {

    /**
     * Search for companies by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("search/company")
    Observable<CompanyResultsPage> company(
            @Query("query") String query,
            @Query("page") Integer page
    );

    /**
     * Search for collections by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("search/collection")
    Observable<CollectionResultsPage> collection(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Search for keywords by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("search/collection")
    Observable<KeywordResultsPage> keyword(
            @Query("query") String query,
            @Query("page") Integer page
    );

    /**
     * Search for movies by title.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     * @param includeAdult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param year <em>Optional.</em> Filter the results release dates to matches that include this value.
     * @param primaryReleaseYear <em>Optional.</em> Filter the results so that only the primary release dates have this
     * value.
     * @param searchType <em>Optional.</em> By default, the search type is 'phrase'. This is almost guaranteed the
     * option you will want. It's a great all purpose search type and by far the most tuned for every day querying. For
     * those wanting more of an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("search/movie")
    Observable<MovieResultsPage> movie(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("include_adult") Boolean includeAdult,
            @Query("year") Integer year,
            @Query("primary_release_year") Integer primaryReleaseYear,
            @Query("search_type") String searchType
    );

    /**
     * Search for people by name.
     *
     * @param query CGI escaped string
     * @param page <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param includeAdult <em>Optional.</em> Toggle the inclusion of adult titles. Expected value is: true or false
     * @param searchType <em>Optional.</em> By default, the search type is 'phrase'. This is almost guaranteed the
     * option you will want. It's a great all purpose search type and by far the most tuned for every day querying. For
     * those wanting more of an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("search/person")
    Observable<PersonResultsPage> person(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("include_adult") Boolean includeAdult,
            @Query("search_type") String searchType
    );

    /**
     * Search for TV shows by title.
     *
     * @param query CGI escaped string
     * @param page Minimum 1, maximum 1000.
     * @param language ISO 639-1 code.
     * @param firstAirDateYear Filter the results to only match shows that have an air date with this value.
     * @param searchType By default, the search type is 'phrase'. This is almost guaranteed the option you will want.
     * It's a great all purpose search type and by far the most tuned for every day querying. For those wanting more of
     * an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("search/tv")
    Observable<TvResultsPage> tv(
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("language") String language,
            @Query("first_air_date_year") Integer firstAirDateYear,
            @Query("search_type") String searchType
    );
}
