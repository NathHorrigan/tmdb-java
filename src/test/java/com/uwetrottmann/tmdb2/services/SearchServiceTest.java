package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.*;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchServiceTest extends BaseTestCase {

    @Test
    public void test_companySearch() throws IOException {
        Observable<CompanyResultsPage> observable = getManager().searchService().company("Sony Pictures", null);

        observable.subscribe(new Action1<CompanyResultsPage>() {
            @Override
            public void call(CompanyResultsPage companyResults) {
                assertResultsPage(companyResults);
                assertThat(companyResults.results).isNotEmpty();
                assertThat(companyResults.results.get(0).id).isNotNull();
                assertThat(companyResults.results.get(0)).isNotNull();
                assertThat(companyResults.results.get(0).logo_path).isNotNull();
            }
        });
    }

    @Test
    public void test_collectionSearch() throws IOException {
        Observable<CollectionResultsPage> observable = getManager().searchService().collection("The Avengers Collection", null,
                null);

        observable.subscribe(new Action1<CollectionResultsPage>() {
            @Override
            public void call(CollectionResultsPage collectionResults) {
                assertResultsPage(collectionResults);
                assertThat(collectionResults.results).isNotEmpty();
                assertThat(collectionResults.results.get(0).id).isNotNull();
                assertThat(collectionResults.results.get(0).backdrop_path).isNotNull();
                assertThat(collectionResults.results.get(0).name).isNotNull();
                assertThat(collectionResults.results.get(0).poster_path).isNotNull();
            }
        });
    }

    @Test
    public void test_keywordSearch() throws IOException {
        Observable<KeywordResultsPage> observable = getManager().searchService().keyword("fight", null);

        observable.subscribe(new Action1<KeywordResultsPage>() {
            @Override
            public void call(KeywordResultsPage keywordResults) {
                assertResultsPage(keywordResults);
                assertThat(keywordResults.results).isNotEmpty();
                assertThat(keywordResults.results.get(0).id).isNotNull();
                assertThat(keywordResults.results.get(0).name).isNotNull();
            }
        });
    }

    @Test
    public void test_movieSearch() throws IOException {
        Observable<MovieResultsPage> observable = getManager().searchService().movie(TestData.MOVIE_TITLE, null, null, null, null,
                null, null);

        observable.subscribe(new Action1<MovieResultsPage>() {
            @Override
            public void call(MovieResultsPage movieResults) {
                assertResultsPage(movieResults);
                assertThat(movieResults.results).isNotEmpty();
            }
        });
    }

    @Test
    public void test_personSearch() throws IOException {
        Observable<PersonResultsPage> observable = getManager().searchService().person(TestData.PERSON_NAME, null, null, null);

        observable.subscribe(new Action1<PersonResultsPage>() {
            @Override
            public void call(PersonResultsPage movieResults) {
                assertResultsPage(movieResults);
                assertThat(movieResults.results.get(0).id).isNotNull();
                assertThat(movieResults.results.get(0).name).isNotNull();
                assertThat(movieResults.results.get(0).popularity).isNotNull();
                assertThat(movieResults.results.get(0).profile_path).isNotNull();
                assertThat(movieResults.results.get(0).adult).isNotNull();
                assertMedia(movieResults.results.get(0).known_for);
            }
        });
    }

    @Test
    public void test_tv() throws IOException {
        Observable<TvResultsPage> observable = getManager().searchService().tv(TestData.TVSHOW_TITLE, null, null, null, null);

        observable.subscribe(new Action1<TvResultsPage>() {
            @Override
            public void call(TvResultsPage tvResults) {
                assertResultsPage(tvResults);
                assertThat(tvResults.results).isNotEmpty();
                assertThat(tvResults.results.get(0).name).isEqualTo(TestData.TVSHOW_TITLE);
            }
        });
    }

    private void assertResultsPage(BaseResultsPage results) {
        assertThat(results.page).isPositive();
        assertThat(results.total_pages).isPositive();
        assertThat(results.total_results).isPositive();
    }

}
