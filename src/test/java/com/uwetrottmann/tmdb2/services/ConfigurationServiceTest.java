
package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.entities.Configuration;
import org.junit.Test;
import io.reactivex.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationServiceTest extends BaseTestCase {

    @Test
    public void test_configuration() throws IOException {
        Observable<Configuration> observable = getManager().configurationService().configuration();

        observable.subscribe(new Action1<Configuration>() {
            @Override
            public void call(Configuration config) {
                assertThat(config).isNotNull();
                assertThat(config.images).isNotNull();
                assertThat(config.images.base_url).isNotEmpty();
                assertThat(config.images.secure_base_url).isNotEmpty();
                assertThat(config.images.poster_sizes).isNotEmpty();
                assertThat(config.images.backdrop_sizes).isNotEmpty();
                assertThat(config.images.profile_sizes).isNotEmpty();
                assertThat(config.images.logo_sizes).isNotEmpty();
                assertThat(config.images.still_sizes).isNotEmpty();
                assertThat(config.change_keys).isNotEmpty();
            }
        });
    }
}
