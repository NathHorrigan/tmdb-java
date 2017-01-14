/*
 * Copyright 2015 Manuel Laggner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
import com.uwetrottmann.tmdb2.entities.Collection;
import com.uwetrottmann.tmdb2.entities.Images;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import rx.Observable;
import rx.functions.Action1;

import java.io.IOException;

public class CollectionServiceTest extends BaseTestCase {
  public CollectionServiceTest() {
  }

  @Test
  public void test_summary() throws IOException {
    final Observable<Collection> observable = this.getManager().collectionService().summary(TestData.MOVIE_COLLECTION_ID, null, null);

    observable.subscribe(new Action1<Collection>() {
      @Override
      public void call(Collection collection) {
        Assertions.assertThat(collection).isNotNull();
        Assertions.assertThat(collection.name).isEqualTo(TestData.MOVIE_COLLECTION_TITLE);
        Assertions.assertThat(collection.id).isEqualTo(1241);
        Assertions.assertThat(collection.overview).isNotEmpty();
        Assertions.assertThat(collection.backdrop_path).isNotEmpty();
        Assertions.assertThat(collection.poster_path).isNotEmpty();
        Assertions.assertThat(collection.parts).isNotEmpty();
        Assertions.assertThat(collection.parts.size()).isPositive();
        Assertions.assertThat(collection.parts.get(0).id).isEqualTo(671);
        Assertions.assertThat(collection.parts.get(1).id).isEqualTo(672);
      }
    });
  }

  @Test
  public void test_images() throws IOException {
    Observable<Images> observable = this.getManager().collectionService().images(TestData.MOVIE_COLLECTION_ID, null);

    observable.subscribe(new Action1<Images>() {
      @Override
      public void call(Images images) {
        Assertions.assertThat(images).isNotNull();
        Assertions.assertThat(images.id).isEqualTo(1241);
        Assertions.assertThat(images.backdrops).isNotEmpty();
        Assertions.assertThat(images.backdrops.get(0).file_path).isNotEmpty();
        Assertions.assertThat(images.backdrops.get(0).width).isEqualTo(1920);
        Assertions.assertThat(images.backdrops.get(0).height).isEqualTo(1080);
        Assertions.assertThat(images.backdrops.get(0).iso_639_1).hasSize(2);
        Assertions.assertThat(images.backdrops.get(0).aspect_ratio).isGreaterThan(1.7F);
        Assertions.assertThat(images.backdrops.get(0).vote_average).isPositive();
        Assertions.assertThat(images.backdrops.get(0).vote_count).isPositive();
        Assertions.assertThat(images.posters).isNotEmpty();
        Assertions.assertThat(images.posters.get(0).file_path).isNotEmpty();
        Assertions.assertThat(images.posters.get(0).width).isEqualTo(1000);
        Assertions.assertThat(images.posters.get(0).height).isEqualTo(1500);
        Assertions.assertThat(images.posters.get(0).iso_639_1).isEqualTo("en");
        Assertions.assertThat(images.posters.get(0).aspect_ratio).isGreaterThan(0.6F);
        Assertions.assertThat(images.posters.get(0).vote_average).isPositive();
        Assertions.assertThat(images.posters.get(0).vote_count).isPositive();
      }
    });
  }
}