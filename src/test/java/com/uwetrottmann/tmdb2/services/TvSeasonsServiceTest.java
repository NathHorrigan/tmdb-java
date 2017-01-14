package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.enumerations.AppendToResponseItem;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TvSeasonsServiceTest extends BaseTestCase {

    @Test
    public void test_season() throws IOException {
        Observable<TvSeason> observable = getManager().tvSeasonsService().season(TestData.TVSHOW_ID, 1, null, null);

        observable.subscribe(new Action1<TvSeason>() {
            @Override
            public void call(TvSeason season) {
                assertTvSeason(season);
            }
        });
    }

    @Test
    public void test_season_with_append_to_response() throws IOException {
        Observable<TvSeason> observable = getManager().tvSeasonsService().season(
                TestData.TVSHOW_ID, 1, null,
                new AppendToResponse(AppendToResponseItem.IMAGES, AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS)
        );

        observable.subscribe(new Action1<TvSeason>() {
            @Override
            public void call(TvSeason season) {
                assertTvSeason(season);
                // credits
                assertThat(season.credits).isNotNull();
                assertCrewCredits(season.credits.crew);
                assertCastCredits(season.credits.cast);

                // images
                assertThat(season.images).isNotNull();
                assertImages(season.images.posters);

                // external ids
                assertThat(season.external_ids).isNotNull();
                assertThat(season.external_ids.freebase_id).isNotNull();
                assertThat(season.external_ids.freebase_mid).isNotNull();
                assertThat(season.external_ids.tvdb_id).isNotNull();
            }
        });
    }

    @Test
    public void test_credits() throws IOException {
        Observable<Credits> observable = getManager().tvSeasonsService().credits(TestData.TVSHOW_ID, 1);

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
        Observable<ExternalIds> observable = getManager().tvSeasonsService().externalIds(TestData.TVSHOW_ID, 1, null);

        observable.subscribe(new Action1<ExternalIds>() {
            @Override
            public void call(ExternalIds ids) {
                assertThat(ids.id).isNotNull();
                assertThat(ids.freebase_id).isNotNull();
                assertThat(ids.freebase_mid).isNotNull();
                assertThat(ids.tvdb_id).isNotNull();
            }
        });
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> observable = getManager().tvSeasonsService().images(TestData.TVSHOW_ID, 1, null);

        observable.subscribe(new Action1<Images>() {
            @Override
            public void call(Images images) {
                assertThat(images.id).isNotNull();
                assertImages(images.posters);
            }
        });
    }

    @Test
    public void test_videos() throws IOException {
        Observable<Videos> observable = getManager().tvSeasonsService().videos(TestData.TVSHOW_ID, 1, null);

        observable.subscribe(new Action1<Videos>() {
            @Override
            public void call(Videos videos) {
                assertVideos(videos);
            }
        });
    }

    private void assertTvSeason(TvSeason season) {
        assertThat(season.air_date).isNotNull();
        assertThat(season.name).isEqualTo("Season 1");
        assertThat(season.overview).isNotNull();
        assertThat(season.id).isNotNull();
        assertThat(season.poster_path).isNotEmpty();
        assertThat(season.season_number).isEqualTo(1);
        assertThat(season.episodes).isNotEmpty();

        for (TvEpisode episode : season.episodes) {
            assertCrewCredits(episode.crew);
            assertCastCredits(episode.guest_stars);
            assertThat(episode.air_date).isNotNull();
            assertThat(episode.episode_number).isPositive();
            assertThat(episode.name).isNotNull();
            assertThat(episode.overview).isNotNull();
            assertThat(episode.id).isNotNull();
            assertThat(episode.season_number).isEqualTo(1);
            assertThat(episode.still_path).isNotNull();
            assertThat(episode.vote_average).isGreaterThanOrEqualTo(0);
            assertThat(episode.vote_count).isGreaterThanOrEqualTo(0);
        }
    }

}
