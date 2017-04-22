/*
 * Copyright 2015 Miguel Teixeira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

public class TvEpisodesServiceTest extends BaseTestCase {

    @Test
    public void test_episode() throws IOException {
        Observable<TvEpisode> observable = getManager().tvEpisodesService().episode(TestData.TVSHOW_ID, 1, 1, null, null);

        observable.subscribe(new Action1<TvEpisode>() {
            @Override
            public void call(TvEpisode episode) {
                assertTvEpisode(episode);
            }
        });
    }

    @Test
    public void test_episode_with_append_to_response() throws IOException {
        Observable<TvEpisode> observable = getManager().tvEpisodesService().episode(
                TestData.TVSHOW_ID, 1, 1, null,
                new AppendToResponse(AppendToResponseItem.IMAGES, AppendToResponseItem.EXTERNAL_IDS,
                        AppendToResponseItem.CREDITS)
        );

        observable.subscribe(new Action1<TvEpisode>() {
            @Override
            public void call(TvEpisode episode) {
                assertTvEpisode(episode);

                // credits
                assertThat(episode.credits).isNotNull();
                assertCrewCredits(episode.credits.crew);
                assertCastCredits(episode.credits.guest_stars);
                assertCastCredits(episode.credits.cast);

                assertThat(episode.crew).isNotNull();
                assertThat(episode.guest_stars).isNotNull();
                assertCrewCredits(episode.crew);
                assertCastCredits(episode.guest_stars);

                // images
                assertThat(episode.images).isNotNull();
                assertImages(episode.images.stills);

                // external ids
                assertIds(episode.external_ids);
            }
        });
    }

    @Test
    public void test_credits() throws IOException {
        Observable<Credits> observable = getManager().tvEpisodesService().credits(TestData.TVSHOW_ID, 1, 1);

        observable.subscribe(new Action1<Credits>() {
            @Override
            public void call(Credits credits) {
                assertThat(credits.id).isNotNull();
                assertCrewCredits(credits.crew);
                assertCastCredits(credits.cast);
                assertCastCredits(credits.guest_stars);
            }
        });
    }

    @Test
    public void test_externalIds() throws IOException {
        Observable<ExternalIds> observable = getManager().tvEpisodesService().externalIds(TestData.TVSHOW_ID, 1, 1);

        observable.subscribe(new Action1<ExternalIds>() {
            @Override
            public void call(ExternalIds ids) {
                assertThat(ids.id).isNotNull();
                assertIds(ids);
            }
        });
    }

    @Test
    public void test_images() throws IOException {
        Observable<Images> observable = getManager().tvEpisodesService().images(TestData.TVSHOW_ID, 1, 1);

        observable.subscribe(new Action1<Images>() {
            @Override
            public void call(Images images) {
                assertThat(images.id).isNotNull();
                assertImages(images.stills);
            }
        });
    }

    @Test
    public void test_videos() throws IOException {
        Observable<Videos> observable = getManager().tvEpisodesService().videos(TestData.TVSHOW_ID, 1, 1);

        observable.subscribe(new Action1<Videos>() {
            @Override
            public void call(Videos videos) {
                assertVideos(videos);
            }
        });
    }

    private void assertIds(ExternalIds ids) {
        assertThat(ids.freebase_id).isNull();
        assertThat(ids.freebase_mid).isNotNull();
        assertThat(ids.tvdb_id).isNotNull();
        assertThat(ids.imdb_id).isNotNull();
        assertThat(ids.tvrage_id).isNotNull();
    }

    private void assertTvEpisode(TvEpisode episode) {
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
