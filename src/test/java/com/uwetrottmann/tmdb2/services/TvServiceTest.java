package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import io.reactivex.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TvServiceTest extends BaseTestCase {

    @Test
    public void test_tvshow() throws IOException {
        Observable<TvShowComplete> observable = getManager().tvService().tv(TestData.TVSHOW_ID, null, null);
        observable.subscribe(new Action1<TvShowComplete>() {
            @Override
            public void call(TvShowComplete show) {
                assertTvShow(show);
            }
        });
    }

    @Test
    public void test_tvshow_with_append_to_response() throws IOException {
        Observable<TvShowComplete> observable = getManager().tvService().tv(
                TestData.TVSHOW_ID, null,
                new AppendToResponse(AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.ALTERNATIVE_TITLES,
                        AppendToResponseItem.SIMILAR,
                        AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.IMAGES,
                        AppendToResponseItem.CONTENT_RATINGS)
        );

        observable.subscribe(new Action1<TvShowComplete>() {
            @Override
            public void call(TvShowComplete show) {
                assertTvShow(show);

                // credits
                assertThat(show.credits).isNotNull();
                assertCrewCredits(show.credits.crew);
                assertCastCredits(show.credits.cast);

                // images
                assertThat(show.images).isNotNull();
                assertImages(show.images.backdrops);
                assertImages(show.images.posters);

                // external ids
                assertThat(show.external_ids).isNotNull();
                assertThat(show.external_ids.freebase_id).isNotNull();
                assertThat(show.external_ids.freebase_mid).isNotNull();
                assertThat(show.external_ids.tvdb_id).isNotNull();
                assertThat(show.external_ids.imdb_id).isNotNull();
                assertThat(show.external_ids.tvrage_id).isNotNull();

                // similar
                assertThat(show.similar).isNotNull();
                assertThat(show.similar.results).isNotNull();
                assertThat(show.similar.results).isNotEmpty();

                // videos
                assertThat(show.videos).isNotNull();
                assertThat(show.videos.results).isNotNull();
                assertThat(show.videos.results).isNotEmpty();

                // alternative_titles
                assertThat(show.alternative_titles).isNotNull();
                assertThat(show.alternative_titles.results).isNotNull();
                assertThat(show.alternative_titles.results).isNotEmpty();

                // content ratings
                assertThat(show.content_ratings).isNotNull();
                assertThat(show.content_ratings.results).isNotEmpty();
                assertThat(show.content_ratings.results.get(0).iso_3166_1).isNotNull();
                assertThat(show.content_ratings.results.get(0).rating).isNotNull();
            }
        });
    }

    @Test
    public void test_content_ratings() throws IOException {
        Observable<TvContentRatings> observable = getManager().tvService().content_ratings(TestData.TVSHOW_ID);

        observable.subscribe(new Action1<TvContentRatings>() {
            @Override
            public void call(TvContentRatings ratings) {
                assertThat(ratings).isNotNull();
                assertThat(ratings.id).isEqualTo(TestData.TVSHOW_ID);
                assertThat(ratings.results).isNotEmpty();
                assertThat(ratings.results.get(0).iso_3166_1).isNotNull();
                assertThat(ratings.results.get(0).rating).isNotNull();
            }
        });
    }

    @Test
    public void test_alternative_titles() throws IOException {
        Observable<TvAlternativeTitles> observable = getManager().tvService().alternativeTitles(TestData.TVSHOW_ID);

        observable.subscribe(new Action1<TvAlternativeTitles>() {
            @Override
            public void call(TvAlternativeTitles titles) {
                assertThat(titles).isNotNull();
                assertThat(titles.id).isEqualTo(TestData.TVSHOW_ID);
                assertThat(titles.results).isNotEmpty();
                assertThat(titles.results.get(0).iso_3166_1).isNotNull();
                assertThat(titles.results.get(0).title).isNotNull();
            }
        });
    }

    @Test
    public void test_credits() throws IOException {
        Observable<Credits> observable = getManager().tvService().credits(TestData.TVSHOW_ID, null);

        observable.subscribe(new Action1<Credits>() {
            @Override
            public void call(Credits credits) {
                assertThat(credits.id).isNotNull();
                assertCrewCredits(credits.crew);
                assertCastCredits(credits.cast);
            }
        });
    }

    @Test
    public void test_externalIds() throws IOException {
        Observable<ExternalIds> observable = getManager().tvService().externalIds(TestData.TVSHOW_ID, null);

        observable.subscribe(new Action1<ExternalIds>() {
            @Override
            public void call(ExternalIds ids) {
                assertThat(ids.id).isNotNull();
                assertThat(ids.freebase_id).isNotNull();
                assertThat(ids.freebase_mid).isNotNull();
                assertThat(ids.tvdb_id).isNotNull();
                assertThat(ids.imdb_id).isNotNull();
                assertThat(ids.tvrage_id).isNotNull();
            }
        });
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> observable = getManager().tvService().images(TestData.TVSHOW_ID, null);

        observable.subscribe(new Action1<Images>() {
            @Override
            public void call(Images images) {
                assertThat(images).isNotNull();
                assertThat(images.id).isEqualTo(TestData.TVSHOW_ID);
                assertImages(images.backdrops);
                assertImages(images.posters);
            }
        });
    }

    @Test
    public void test_keywords() throws IOException {
        Observable<TvKeywords> observable = getManager().tvService().keywords(TestData.TVSHOW_ID);
        observable.subscribe(new Action1<TvKeywords>() {
            @Override
            public void call(TvKeywords keywords) {
                assertThat(keywords).isNotNull();
                assertThat(keywords.id).isEqualTo(TestData.TVSHOW_ID);
                assertThat(keywords.results.get(0).id).isNotNull();
                assertThat(keywords.results.get(0).name).isNotNull();
            }
        });
    }

    @Test
    public void test_similar() throws IOException {
        Observable<TvResultsPage> observable = getManager().tvService().similar(TestData.TVSHOW_ID, 1, null);

        observable.subscribe(new Action1<TvResultsPage>() {
            @Override
            public void call(TvResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.page).isNotNull().isPositive();
                assertThat(results.total_pages).isNotNull().isPositive();
                assertThat(results.total_results).isNotNull().isPositive();
                assertThat(results.results).isNotEmpty();
                assertThat(results.results.get(0).backdrop_path).isNotNull();
                assertThat(results.results.get(0).id).isNotNull().isPositive();
                assertThat(results.results.get(0).original_name).isNotNull();
                assertThat(results.results.get(0).first_air_date).isNotNull();
                assertThat(results.results.get(0).poster_path).isNotNull();
                assertThat(results.results.get(0).popularity).isNotNull().isPositive();
                assertThat(results.results.get(0).name).isNotNull();
                assertThat(results.results.get(0).vote_average).isNotNull().isPositive();
                assertThat(results.results.get(0).vote_count).isNotNull().isPositive();
            }
        });
    }

    @Test
    public void test_videos() throws IOException {
        Observable<Videos> observable = getManager().tvService().videos(TestData.TVSHOW_ID, null);

        observable.subscribe(new Action1<Videos>() {
            @Override
            public void call(Videos videos) {
                assertThat(videos).isNotNull();
                assertThat(videos.id).isEqualTo(TestData.TVSHOW_ID);
                assertThat(videos.results.get(0).id).isNotNull();
                assertThat(videos.results.get(0).iso_639_1).isNotNull();
                assertThat(videos.results.get(0).key).isNotNull();
                assertThat(videos.results.get(0).name).isNotNull();
                assertThat(videos.results.get(0).site).isEqualTo("YouTube");
                assertThat(videos.results.get(0).size).isNotNull();
                assertThat(videos.results.get(0).type).isEqualTo("Trailer");
            }
        });
    }

    @Test
    public void test_latest() throws IOException {
        Observable<TvShowComplete> observable = getManager().tvService().latest();

        observable.subscribe(new Action1<TvShowComplete>() {
            @Override
            public void call(TvShowComplete show) {
                // Latest show might not have a complete TMDb entry, but at should least some basic properties.
                assertThat(show).isNotNull();
                assertThat(show.id).isPositive();
                assertThat(show.name).isNotEmpty();
            }
        });
    }

    @Test
    public void test_onTheAir() throws IOException {
        Observable<TvResultsPage> observable = getManager().tvService().onTheAir(null, null);

        observable.subscribe(new Action1<TvResultsPage>() {
            @Override
            public void call(TvResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.results).isNotEmpty();
            }
        });
    }

    @Test
    public void test_airingToday() throws IOException {
        Observable<TvResultsPage> observable = getManager().tvService().airingToday(null, null);

        observable.subscribe(new Action1<TvResultsPage>() {
            @Override
            public void call(TvResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.results).isNotEmpty();
            }
        });
    }

    @Test
    public void test_topRated() throws IOException {
        Observable<TvResultsPage> observable = getManager().tvService().topRated(null, null);

        observable.subscribe(new Action1<TvResultsPage>() {
            @Override
            public void call(TvResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.results).isNotEmpty();
            }
        });
    }

    @Test
    public void test_popular() throws IOException {
        Observable<TvResultsPage> observable = getManager().tvService().popular(null, null);

        observable.subscribe(new Action1<TvResultsPage>() {
            @Override
            public void call(TvResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.results).isNotEmpty();
            }
        });
    }

    private void assertTvShow(TvShowComplete show) {
        assertThat(show.first_air_date).isNotNull();
        assertThat(show.homepage).isNotNull();
        assertThat(show.id).isNotNull();
        assertThat(show.in_production).isNotNull();
        assertThat(show.languages).isNotEmpty();
        assertThat(show.last_air_date).isNotNull();
        assertThat(show.name).isNotNull();
        assertThat(show.number_of_seasons).isNotNull().isPositive();
        assertThat(show.original_language).isNotNull();
        assertThat(show.original_name).isNotNull();
        assertThat(show.overview).isNotNull();
        assertThat(show.popularity).isNotNull().isGreaterThanOrEqualTo(0);
        assertThat(show.poster_path).isNotNull();
        assertThat(show.status).isNotNull();
        assertThat(show.type).isNotNull();
        assertThat(show.vote_average).isNotNull().isGreaterThanOrEqualTo(0);
        assertThat(show.vote_count).isNotNull().isGreaterThanOrEqualTo(0);

        assertThat(show.created_by).isNotEmpty();
        for (Person person : show.created_by) {
            assertThat(person.id).isNotNull();
            assertThat(person.name).isNotNull();
            assertThat(person.profile_path).isNotNull();
        }

        assertThat(show.seasons).isNotEmpty();
        for (TvSeason company : show.seasons) {
            assertThat(company.id).isNotNull();
            assertThat(company.air_date).isNotNull();
            assertThat(company.episode_count).isNotNull();
            assertThat(company.season_number).isNotNull();
        }

    }

}
