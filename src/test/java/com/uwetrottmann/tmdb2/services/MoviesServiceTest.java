
package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import io.reactivex.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class MoviesServiceTest extends BaseTestCase {

    @Test
    public void test_summary() throws ParseException, IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID, null, null);

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertMovie(movie);
                assertThat(movie.original_title).isEqualTo(TestData.MOVIE_TITLE);
            }
        });
    }

    @Test
    public void test_summary_language() throws ParseException, IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID, "pt-BR", null);

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertThat(movie).isNotNull();
                assertThat(movie.title).isEqualTo("Clube da Luta");
            }
        });
    }

    @Test
    public void test_summary_with_collection() throws ParseException, IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_WITH_COLLECTION_ID, null, null);

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertThat(movie.title).isEqualTo(TestData.MOVIE_WITH_COLLECTION_TITLE);
                assertThat(movie.belongs_to_collection).isNotNull();
                assertThat(movie.belongs_to_collection.id).isEqualTo(1241);
                assertThat(movie.belongs_to_collection.name).isEqualTo("Harry Potter Collection");
            }
        });
    }

    private void assertMovie(Movie movie) {
        assertThat(movie).isNotNull();
        assertThat(movie.id).isEqualTo(TestData.MOVIE_ID);
        assertThat(movie.title).isEqualTo(TestData.MOVIE_TITLE);
        assertThat(movie.overview).isNotEmpty();
        assertThat(movie.tagline).isNotEmpty();
        assertThat(movie.adult).isFalse();
        assertThat(movie.backdrop_path).isNotEmpty();
        assertThat(movie.budget).isEqualTo(63000000);
        assertThat(movie.imdb_id).isEqualTo(TestData.MOVIE_IMDB);
        assertThat(movie.poster_path).isNotEmpty();
        assertThat(movie.release_date).isEqualTo("1999-10-14");
        assertThat(movie.revenue).isEqualTo(100853753);
        assertThat(movie.runtime).isEqualTo(139);
        assertThat(movie.vote_average).isPositive();
        assertThat(movie.vote_count).isPositive();
        assertThat(movie.status).isEqualTo(TestData.STATUS);
    }

    @Test
    public void test_summary_append_videos() throws IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.VIDEOS));

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertNotNull(movie.videos);
            }
        });
    }

    @Test
    public void test_summary_append_alternative_titles() throws IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.ALTERNATIVE_TITLES));

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertNotNull(movie.alternative_titles);
            }
        });
    }

    @Test
    public void test_summary_append_credits() throws IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.CREDITS));

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertNotNull(movie.credits);
            }
        });
    }

    @Test
    public void test_summary_append_release_dates() throws IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASE_DATES));

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertNotNull(movie.release_dates);
            }
        });
    }

    @Test
    public void test_summary_append_similar() throws IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.SIMILAR));

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertNotNull(movie.similar);
            }
        });
    }

    @Test
    public void test_summary_append_all() throws IOException {
        Observable<Movie> observable = getManager().moviesService().summary(TestData.MOVIE_ID,
                null,
                new AppendToResponse(
                        AppendToResponseItem.RELEASE_DATES,
                        AppendToResponseItem.CREDITS,
                        AppendToResponseItem.VIDEOS,
                        AppendToResponseItem.ALTERNATIVE_TITLES,
                        AppendToResponseItem.SIMILAR));

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                assertNotNull(movie.release_dates);
                assertNotNull(movie.release_dates.results);
                assertThat(movie.release_dates.results).isNotEmpty();
                assertNotNull(movie.credits);
                assertNotNull(movie.videos);
                assertNotNull(movie.similar);
                assertNotNull(movie.alternative_titles);
            }
        });
    }

    @Test
    public void test_alternative_titles() throws IOException {
        Observable<MovieAlternativeTitles> observable = getManager().moviesService().alternativeTitles(TestData.MOVIE_ID, null);

        observable.subscribe(new Action1<MovieAlternativeTitles>() {
            @Override
            public void call(MovieAlternativeTitles titles) {
                assertThat(titles).isNotNull();
                assertThat(titles.id).isEqualTo(TestData.MOVIE_ID);
                assertThat(titles.titles).isNotEmpty();
                assertThat(titles.titles.get(0).iso_3166_1).hasSize(2);
                assertThat(titles.titles.get(0).title).isNotEmpty();
            }
        });
    }

    @Test
    public void test_credits() throws IOException {
        Observable<Credits> observable = getManager().moviesService().credits(TestData.MOVIE_ID);

        observable.subscribe(new Action1<Credits>() {
            @Override
            public void call(Credits credits) {
                assertThat(credits).isNotNull();
                assertThat(credits.id).isEqualTo(TestData.MOVIE_ID);
                assertThat(credits.cast).isNotEmpty();
                assertThat(credits.cast.get(0)).isNotNull();
                assertThat(credits.cast.get(0).name).isEqualTo("Edward Norton");
                assertThat(credits.crew).isNotEmpty();
            }
        });
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> observable = getManager().moviesService().images(TestData.MOVIE_ID, null);

        observable.subscribe(new Action1<Images>() {
            @Override
            public void call(Images images) {
                assertThat(images).isNotNull();
                assertThat(images.id).isEqualTo(TestData.MOVIE_ID);
                assertThat(images.backdrops).isNotEmpty();
                assertThat(images.backdrops.get(0).file_path).isNotEmpty();
                assertThat(images.backdrops.get(0).width).isGreaterThanOrEqualTo(1280);
                assertThat(images.backdrops.get(0).height).isGreaterThanOrEqualTo(720);
                if (images.backdrops.get(0).iso_639_1 != null) {
                    assertThat(images.backdrops.get(0).iso_639_1).hasSize(2);
                }
                assertThat(images.backdrops.get(0).aspect_ratio).isGreaterThan(1.7f);
                assertThat(images.backdrops.get(0).vote_average).isPositive();
                assertThat(images.backdrops.get(0).vote_count).isPositive();
                assertThat(images.posters).isNotEmpty();
                assertThat(images.posters.get(0).file_path).isNotEmpty();
                assertThat(images.posters.get(0).width).isPositive();
                assertThat(images.posters.get(0).height).isPositive();
                assertThat(images.posters.get(0).iso_639_1).hasSize(2);
                assertThat(images.posters.get(0).aspect_ratio).isGreaterThan(0.6f);
                assertThat(images.posters.get(0).vote_average).isPositive();
                assertThat(images.posters.get(0).vote_count).isPositive();
            }
        });
    }

    @Test
    public void test_keywords() throws IOException {
        Observable<MovieKeywords> observable = getManager().moviesService().keywords(TestData.MOVIE_ID);

        observable.subscribe(new Action1<MovieKeywords>() {
            @Override
            public void call(MovieKeywords keywords) {
                assertThat(keywords).isNotNull();
                assertThat(keywords.id).isEqualTo(TestData.MOVIE_ID);
                assertThat(keywords.keywords.get(0).id).isEqualTo(825);
                assertThat(keywords.keywords.get(0).name).isEqualTo("support group");
            }
        });
    }

    @Test
    public void test_release_dates() throws IOException {
        Observable<ReleaseDatesResults> observable = getManager().moviesService().releaseDates(TestData.MOVIE_ID);

        observable.subscribe(new Action1<ReleaseDatesResults>() {
            @Override
            public void call(ReleaseDatesResults results) {
                assertThat(results).isNotNull();
                assertThat(results.id).isEqualTo(TestData.MOVIE_ID);
                assertThat(results.results).isNotNull();
                assertThat(results.results.isEmpty()).isFalse();

                ReleaseDatesResult usResult = null;
                for (ReleaseDatesResult result : results.results) {
                    assertThat(result.iso_3166_1).isNotNull();
                    if (result.iso_3166_1.equals("US")) {
                        usResult = result;
                    }
                }

                assertThat(usResult).isNotNull();
                assertThat(usResult.release_dates).isNotNull();
                assertThat(usResult.release_dates.isEmpty()).isFalse();
                assertThat(usResult.release_dates.get(0).iso_639_1).isNotNull();
                assertThat(usResult.release_dates.get(0).certification).isEqualTo("R");
                assertThat(usResult.release_dates.get(0).release_date).isEqualTo("1999-10-14T00:00:00.000Z");
                assertThat(usResult.release_dates.get(0).note).isNotNull();
                assertThat(usResult.release_dates.get(0).type).isBetween(1, 6);
            }
        });
    }

    @Test
    public void test_videos() throws IOException {
        Observable<Videos> observable = getManager().moviesService().videos(TestData.MOVIE_ID, null);

        observable.subscribe(new Action1<Videos>() {
            @Override
            public void call(Videos videos) {
                assertThat(videos).isNotNull();
                assertThat(videos.id).isEqualTo(TestData.MOVIE_ID);
                assertThat(videos.results.get(0).getId()).isNotNull();
                assertThat(videos.results.get(0).getIso_639_1()).isNotNull();
                assertThat(videos.results.get(0).getKey()).isNotNull();
                assertThat(videos.results.get(0).getName()).isNotNull();
                assertThat(videos.results.get(0).getSite()).isEqualTo("YouTube");
                assertThat(videos.results.get(0).getSize()).isNotNull();
                assertThat(videos.results.get(0).getType()).isEqualTo("Trailer");
            }
        });
    }

    @Test
    public void test_translations() throws IOException {
        Observable<Translations> observable = getManager().moviesService().translations(TestData.MOVIE_ID, null);

        observable.subscribe(new Action1<Translations>() {
            @Override
            public void call(Translations translations) {
                assertThat(translations).isNotNull();
                assertThat(translations.id).isEqualTo(TestData.MOVIE_ID);
                for (Translations.Translation translation : translations.translations) {
                    assertThat(translation.getName()).isNotNull();
                    assertThat(translation.getIso_639_1()).isNotNull();
                    assertThat(translation.getEnglish_name()).isNotNull();
                }
            }
        });
    }

    @Test
    public void test_similar() throws IOException {
        Observable<MovieResultsPage> observable = getManager().moviesService().similar(TestData.MOVIE_ID, 3, null);

        observable.subscribe(new Action1<MovieResultsPage>() {
            @Override
            public void call(MovieResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.page).isNotNull().isPositive();
                assertThat(results.total_pages).isNotNull().isPositive();
                assertThat(results.total_results).isNotNull().isPositive();
                assertThat(results.results).isNotEmpty();
                assertThat(results.results.get(0).adult).isEqualTo(false);
                assertThat(results.results.get(0).backdrop_path).isNotNull();
                assertThat(results.results.get(0).id).isNotNull().isPositive();
                assertThat(results.results.get(0).original_title).isNotNull();
                assertThat(results.results.get(0).release_date).isNotNull();
                assertThat(results.results.get(0).poster_path).isNotNull();
                assertThat(results.results.get(0).popularity).isNotNull().isPositive();
                assertThat(results.results.get(0).title).isNotNull();
                assertThat(results.results.get(0).vote_average).isNotNull().isPositive();
                assertThat(results.results.get(0).vote_count).isNotNull().isPositive();
            }
        });
    }

    @Test
    public void test_reviews() throws IOException {
        Observable<ReviewResultsPage> observable = getManager().moviesService().reviews(49026, 1, null);

        observable.subscribe(new Action1<ReviewResultsPage>() {
            @Override
            public void call(ReviewResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.id).isNotNull();
                assertThat(results.page).isNotNull().isPositive();
                assertThat(results.total_pages).isNotNull().isPositive();
                assertThat(results.total_results).isNotNull().isPositive();
                assertThat(results.results).isNotEmpty();
                assertThat(results.results.get(0).id).isNotNull();
                assertThat(results.results.get(0).author).isNotNull();
                assertThat(results.results.get(0).content).isNotNull();
                assertThat(results.results.get(0).url).isNotNull();
            }
        });
    }

    @Test
    public void test_lists() throws IOException {
        Observable<ListResultsPage> observable = getManager().moviesService().lists(49026, 1, null);

        observable.subscribe(new Action1<ListResultsPage>() {
            @Override
            public void call(ListResultsPage results) {
                assertThat(results).isNotNull();
                assertThat(results.id).isNotNull();
                assertThat(results.page).isNotNull().isPositive();
                assertThat(results.total_pages).isNotNull().isPositive();
                assertThat(results.total_results).isNotNull().isPositive();
                assertThat(results.results).isNotEmpty();
                assertThat(results.results.get(0).id).isNotNull();
                assertThat(results.results.get(0).description).isNotNull();
                assertThat(results.results.get(0).favorite_count).isNotNull().isGreaterThanOrEqualTo(0);
                assertThat(results.results.get(0).item_count).isPositive();
                assertThat(results.results.get(0).iso_639_1).isNotNull();
                assertThat(results.results.get(0).getName()).isNotNull();
            }
        });
    }

    @Test
    public void test_latest() throws IOException {
        Observable<Movie> observable = getManager().moviesService().latest();

        observable.subscribe(new Action1<Movie>() {
            @Override
            public void call(Movie movie) {
                // Latest movie might not have a complete TMDb entry, but should at least some basic properties.
                assertThat(movie).isNotNull();
                assertThat(movie.getId()).isPositive();
                assertThat(movie.getTitle()).isNotEmpty();
            }
        });
    }

    @Test
    public void test_upcoming() throws IOException {
        Observable<MovieResultsPage> observable = getManager().moviesService().upcoming(null, null);

        observable.subscribe(new Action1<MovieResultsPage>() {
            @Override
            public void call(MovieResultsPage page) {
                assertThat(page).isNotNull();
                assertThat(page.getResults()).isNotEmpty();
            }
        });
    }

    @Test
    public void test_nowPlaying() throws IOException {
        Observable<MovieResultsPage> observable = getManager().moviesService().nowPlaying(null, null);

        observable.subscribe(new Action1<MovieResultsPage>() {
            @Override
            public void call(MovieResultsPage page) {
                assertThat(page).isNotNull();
                assertThat(page.getResults()).isNotEmpty();
            }
        });
    }

    @Test
    public void test_popular() throws IOException {
        Observable<MovieResultsPage> observable = getManager().moviesService().popular(null, null);

        observable.subscribe(new Action1<MovieResultsPage>() {
            @Override
            public void call(MovieResultsPage page) {
                assertThat(page).isNotNull();
                assertThat(page.getResults()).isNotEmpty();
            }
        });
    }

    @Test
    public void test_topRated() throws IOException {
        Observable<MovieResultsPage> observable = getManager().moviesService().topRated(null, null);

        observable.subscribe(new Action1<MovieResultsPage>() {
            @Override
            public void call(MovieResultsPage page) {
                assertThat(page).isNotNull();
                assertThat(page.getResults()).isNotEmpty();
            }
        });

    }

}
