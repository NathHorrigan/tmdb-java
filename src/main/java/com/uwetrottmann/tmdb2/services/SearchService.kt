package com.uwetrottmann.tmdb2.services

import com.uwetrottmann.tmdb2.entities.*
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface SearchService {

    /**
     * Search for companies by name.

     * @param query CGI escaped string
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     */
    @GET("search/company")
    fun company(
            @Query("query") query: String,
            @Query("page") page: Int?
    ): Observable<CompanyResultsPage>

    /**
     * Search for collections by name.

     * @param query CGI escaped string
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     */
    @GET("search/collection")
    fun collection(
            @Query("query") query: String,
            @Query("page") page: Int?,
            @Query("language") language: String
    ): Observable<CollectionResultsPage>

    /**
     * Search for keywords by name.

     * @param query CGI escaped string
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     */
    @GET("search/collection")
    fun keyword(
            @Query("query") query: String,
            @Query("page") page: Int?
    ): Observable<KeywordResultsPage>

    /**
     * Search for movies by title.

     * @param query CGI escaped string
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param language *Optional.* ISO 639-1 code.
     * *
     * @param includeAdult *Optional.* Toggle the inclusion of adult titles. Expected value is: true or false
     * *
     * @param year *Optional.* Filter the results release dates to matches that include this value.
     * *
     * @param primaryReleaseYear *Optional.* Filter the results so that only the primary release dates have this
     * * value.
     * *
     * @param searchType *Optional.* By default, the search type is 'phrase'. This is almost guaranteed the
     * * option you will want. It's a great all purpose search type and by far the most tuned for every day querying. For
     * * those wanting more of an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("search/movie")
    fun movie(
            @Query("query") query: String,
            @Query("page") page: Int?,
            @Query("language") language: String,
            @Query("include_adult") includeAdult: Boolean?,
            @Query("year") year: Int?,
            @Query("primary_release_year") primaryReleaseYear: Int?,
            @Query("search_type") searchType: String
    ): Observable<MovieResultsPage>

    /**
     * Search for people by name.

     * @param query CGI escaped string
     * *
     * @param page *Optional.* Minimum value is 1, expected value is an integer.
     * *
     * @param includeAdult *Optional.* Toggle the inclusion of adult titles. Expected value is: true or false
     * *
     * @param searchType *Optional.* By default, the search type is 'phrase'. This is almost guaranteed the
     * * option you will want. It's a great all purpose search type and by far the most tuned for every day querying. For
     * * those wanting more of an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("search/person")
    fun person(
            @Query("query") query: String,
            @Query("page") page: Int?,
            @Query("include_adult") includeAdult: Boolean?,
            @Query("search_type") searchType: String
    ): Observable<PersonResultsPage>

    /**
     * Search for TV shows by title.

     * @param query CGI escaped string
     * *
     * @param page Minimum 1, maximum 1000.
     * *
     * @param language ISO 639-1 code.
     * *
     * @param firstAirDateYear Filter the results to only match shows that have an air date with this value.
     * *
     * @param searchType By default, the search type is 'phrase'. This is almost guaranteed the option you will want.
     * * It's a great all purpose search type and by far the most tuned for every day querying. For those wanting more of
     * * an "autocomplete" type search, set this option to 'ngram'.
     */
    @GET("search/tv")
    fun tv(
            @Query("query") query: String,
            @Query("page") page: Int?,
            @Query("language") language: String,
            @Query("first_air_date_year") firstAirDateYear: Int?,
            @Query("search_type") searchType: String
    ): Observable<TvResultsPage>
}
