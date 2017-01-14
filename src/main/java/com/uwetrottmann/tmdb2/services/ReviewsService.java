package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Review;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ReviewsService {
	
    @GET("review/{review_id}")
    Observable<Review> getDetails(
            @Path("review_id") String externalId
    );
}
