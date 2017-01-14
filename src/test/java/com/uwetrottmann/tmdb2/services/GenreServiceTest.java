package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Genre;
import com.uwetrottmann.tmdb2.entities.GenreResults;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreServiceTest extends BaseTestCase {

    @Test
    public void test_movie() throws IOException {
        Observable<GenreResults> observable = getManager().genreService().movie(null);

        observable.subscribe(new Action1<GenreResults>() {
            @Override
            public void call(GenreResults results) {
                assertGenres(results);
            }
        });
    }

    @Test
    public void test_tv() throws IOException {
        Observable<GenreResults> observable = getManager().genreService().tv(null);

        observable.subscribe(new Action1<GenreResults>() {
            @Override
            public void call(GenreResults results) {
                assertGenres(results);
            }
        });
    }

    private static void assertGenres(GenreResults results) {
        assertThat(results).isNotNull();
        assertThat(results.genres).isNotEmpty();
        for (Genre genre : results.genres) {
            assertThat(genre.id).isGreaterThan(0);
            assertThat(genre.name).isNotEmpty();
        }
    }

}
