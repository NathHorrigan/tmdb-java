package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.Review;
import org.junit.Test;
import io.reactivex.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewsServiceTest extends BaseTestCase {

    @Test
    public void test_getDetails() throws IOException {
        Observable<Review> observable = getManager().reviewsService().getDetails(TestData.REVIEW_ID);

        observable.subscribe(new Action1<Review>() {
            @Override
            public void call(Review review) {
                assertThat(review.author).isEqualTo(TestData.REVIEW_AUTHOR);
                assertThat(review.iso_639_1).isEqualTo(TestData.REVIEW_ISO_639_1);
                assertThat(review.media_id).isEqualTo(TestData.REVIEW_MEDIA_ID);
                assertThat(review.media_title).isEqualTo(TestData.REVIEW_MEDIA_TITLE);
                assertThat(review.media_type).isEqualTo(TestData.REVIEW_MEDIA_TYPE);
                assertThat(review.url).isEqualTo(TestData.REVIEW_URL);
            }
        });
    }
}
