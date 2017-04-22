package com.uwetrottmann.tmdb2

import com.uwetrottmann.tmdb2.services.CollectionService
import com.uwetrottmann.tmdb2.services.ConfigurationService
import com.uwetrottmann.tmdb2.services.DiscoverService
import com.uwetrottmann.tmdb2.services.FindService
import com.uwetrottmann.tmdb2.services.GenreService
import com.uwetrottmann.tmdb2.services.MoviesService
import com.uwetrottmann.tmdb2.services.PeopleService
import com.uwetrottmann.tmdb2.services.ReviewsService
import com.uwetrottmann.tmdb2.services.SearchService
import com.uwetrottmann.tmdb2.services.TvEpisodesService
import com.uwetrottmann.tmdb2.services.TvSeasonsService
import com.uwetrottmann.tmdb2.services.TvService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Helper class for easy usage of the TMDB v3 API using retrofit.

 *
 * Create an instance of this class and then call any of the service methods.

 *
 * The service methods take care of constructing the required [Retrofit] instance and creating the service. It
 * is recommended you use your existing OkHttp client instance by overriding [.okHttpClient].

 *
 * Only one [Retrofit] instance is created upon the first and re-used for any consequent service method call.
 */
open class Tmdb
/**
 * Create a new manager instance.

 * @param apiKey Your TMDB API key.
 */
(private var apiKey: String?) {

    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    fun apiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    fun apiKey(): String? {
        return apiKey
    }

    /**
     * Creates a [Retrofit.Builder] that sets the base URL, adds a Gson converter and sets [.okHttpClient]
     * as its client.

     * @see .okHttpClient
     */
    protected fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(TmdbHelper.gsonBuilder.create()))
                .client(okHttpClient())
    }


    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.

     * @see .setOkHttpClientDefaults
     */
    @Synchronized protected fun okHttpClient(): OkHttpClient? {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            setOkHttpClientDefaults(builder)
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected open fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
        builder.addInterceptor(TmdbInterceptor(this))
    }

    /**
     * Return the current [Retrofit] instance. If none exists (first call, auth changed), builds a new one.
     *
     * When building, sets the base url and a custom client with an [Interceptor] which supplies authentication
     * data.
     */
    protected fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build()
        }
        return retrofit
    }

    fun configurationService(): ConfigurationService? {
        return getRetrofit()!!.create<ConfigurationService>(ConfigurationService::class.java)
    }

    fun findService(): FindService {
        return getRetrofit()!!.create<FindService>(FindService::class.java)
    }

    fun moviesService(): MoviesService {
        return getRetrofit()!!.create<MoviesService>(MoviesService::class.java)
    }

    fun personService(): PeopleService {
        return getRetrofit()!!.create<PeopleService>(PeopleService::class.java)
    }

    fun searchService(): SearchService {
        return getRetrofit()!!.create<SearchService>(SearchService::class.java)
    }

    fun tvService(): TvService {
        return getRetrofit()!!.create<TvService>(TvService::class.java)
    }

    fun tvSeasonsService(): TvSeasonsService {
        return getRetrofit()!!.create<TvSeasonsService>(TvSeasonsService::class.java)
    }

    fun tvEpisodesService(): TvEpisodesService {
        return getRetrofit()!!.create<TvEpisodesService>(TvEpisodesService::class.java)
    }

    fun discoverService(): DiscoverService {
        return getRetrofit()!!.create<DiscoverService>(DiscoverService::class.java)
    }

    fun collectionService(): CollectionService {
        return getRetrofit()!!.create<CollectionService>(CollectionService::class.java)
    }

    fun genreService(): GenreService {
        return getRetrofit()!!.create<GenreService>(GenreService::class.java)
    }

    fun reviewsService(): ReviewsService {
        return getRetrofit()!!.create<ReviewsService>(ReviewsService::class.java)
    }

    companion object {

        val API_HOST = "api.themoviedb.org"
        val API_VERSION = "3"
        val API_URL = "https://$API_HOST/$API_VERSION/"

        /**
         * API key query parameter name.
         */
        val PARAM_API_KEY = "api_key"
    }
}
