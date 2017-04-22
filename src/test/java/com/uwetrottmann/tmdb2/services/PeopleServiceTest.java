package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.BaseTestCase;
import com.uwetrottmann.tmdb2.TestData;
import com.uwetrottmann.tmdb2.entities.*;
import org.junit.Test;
import io.reactivex.Observable;
import rx.functions.Action1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PeopleServiceTest extends BaseTestCase {

    private static final SimpleDateFormat JSON_STRING_DATE = new SimpleDateFormat("yyy-MM-dd");

    @Test
    public void test_summary() throws IOException, ParseException {
        Observable<Person> observable = getManager().personService().summary(TestData.PERSON_ID);

        observable.subscribe(new Action1<Person>() {
            @Override
            public void call(Person person) {
                assertThat(person).isNotNull();
                assertThat(person.id).isEqualTo(TestData.PERSON_ID);
                assertThat(TestData.PERSON_NAME).isEqualTo(TestData.PERSON_NAME);
                try {
                    assertThat(person.birthday).isEqualTo(JSON_STRING_DATE.parse("1944-05-14"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                assertThat(person.deathday).isNull();
                assertThat(person.profile_path).startsWith("/").endsWith(".jpg");
                assertThat(person.biography).isNotEmpty();
            }
        });
    }

    @Test
    public void test_movie_credits() throws IOException {
        Observable<PersonCredits> observable = getManager().personService().movieCredits(TestData.PERSON_ID, null);

        observable.subscribe(new Action1<PersonCredits>() {
            @Override
            public void call(PersonCredits credits) {
                assertThat(credits.id).isEqualTo(TestData.PERSON_ID);
                assertCastCredits(credits, false);
                assertCrewCredits(credits, false);

                for (PersonCastCredit credit : credits.cast) {
                    assertThat(credit.title).isNotEmpty();
                }
            }
        });
    }

    @Test
    public void test_tv_credits() throws IOException {
        Observable<PersonCredits> observable = getManager().personService().tvCredits(TestData.PERSON_ID, null);

        observable.subscribe(new Action1<PersonCredits>() {
            @Override
            public void call(PersonCredits credits) {
                assertThat(credits.id).isEqualTo(TestData.PERSON_ID);
                assertCastCredits(credits, false);

                for (PersonCastCredit credit : credits.cast) {
                    assertThat(credit.episode_count).isGreaterThanOrEqualTo(0);
                    assertThat(credit.name).isNotEmpty();
                }
            }
        });
    }

    @Test
    public void test_combined_credits() throws IOException {
        Observable<PersonCredits> observable = getManager().personService().combinedCredits(TestData.PERSON_ID, null);

        observable.subscribe(new Action1<PersonCredits>() {
            @Override
            public void call(PersonCredits credits) {
                assertThat(credits.id).isEqualTo(TestData.PERSON_ID);
                assertCastCredits(credits, true);
                assertCrewCredits(credits, true);
            }
        });
    }

    @Test
    public void test_external_ids() throws IOException {
        Observable<PersonIds> observable = getManager().personService().externalIds(TestData.PERSON_ID);

        observable.subscribe(new Action1<PersonIds>() {
            @Override
            public void call(PersonIds ids) {
                assertThat(ids.id).isEqualTo(TestData.PERSON_ID);
                assertThat(ids.imdb_id).isEqualTo("nm0000184");
                assertThat(ids.freebase_id).isEqualTo("/en/george_lucas");
                assertThat(ids.freebase_mid).isEqualTo("/m/0343h");
                assertThat(ids.tvrage_id).isEqualTo(6490);
            }
        });
    }

    @Test
    public void test_images() throws IOException {
        Observable<PersonImages> observable = getManager().personService().images(TestData.PERSON_ID);

        observable.subscribe(new Action1<PersonImages>() {
            @Override
            public void call(PersonImages images) {
                assertThat(images.id).isEqualTo(TestData.PERSON_ID);

                for (Image image : images.profiles) {
                    assertThat(image.file_path).isNotEmpty();
                    assertThat(image.width).isNotNull();
                    assertThat(image.height).isNotNull();
                    assertThat(image.aspect_ratio).isGreaterThan(0);
                }
            }
        });
    }

    @Test
    public void test_tagged_images() throws IOException {
        Observable<TaggedImagesResultsPage> observable = getManager().personService().taggedImages(TestData.PERSON_ID, null, null);

        observable.subscribe(new Action1<TaggedImagesResultsPage>() {
            @Override
            public void call(TaggedImagesResultsPage images) {
                assertThat(images.id).isEqualTo(TestData.PERSON_ID);

                for (TaggedImagesResultsPage.TaggedImage image : images.results) {
                    assertThat(image.file_path).isNotEmpty();
                    assertThat(image.width).isNotNull();
                    assertThat(image.width).isGreaterThan(0);
                    assertThat(image.height).isNotNull();
                    assertThat(image.height).isGreaterThan(0);
                    assertThat(image.aspect_ratio).isGreaterThan(0);
                    assertThat(image.media).isNotNull();
                    assertThat(image.id).isNotNull();
                    assertThat(image.media_type).isNotNull();
                    assertThat(image.image_type).isNotNull();
                }
            }
        });
    }

    @Test
    public void test_popular() throws IOException {
        Observable<PersonResultsPage> observable = getManager().personService().popular(null);

        observable.subscribe(new Action1<PersonResultsPage>() {
            @Override
            public void call(PersonResultsPage popular) {
                assertThat(popular.results.get(0).id).isNotNull();
                assertThat(popular.results.get(0).name).isNotNull();
                assertThat(popular.results.get(0).popularity).isPositive();
                assertThat(popular.results.get(0).profile_path).isNotEmpty();
                assertThat(popular.results.get(0).adult).isNotNull();
                assertMedia(popular.results.get(0).known_for);
            }
        });
    }

    @Test
    public void test_latest() throws IOException {
        Observable<Person> observable = getManager().personService().latest();

        observable.subscribe(new Action1<Person>() {
            @Override
            public void call(Person person) {
                // Latest person might not have a complete TMDb entry, but at should least some basic properties.
                assertThat(person).isNotNull();
                assertThat(person.name).isNotEmpty();
                assertThat(person.id).isPositive();
            }
        });
    }

    private void assertCastCredits(PersonCredits credits, boolean hasMediaType) {
        // assert cast credits
        assertThat(credits.cast).isNotEmpty();
        for (PersonCastCredit credit : credits.cast) {
            assertThat(credit.character).isNotNull(); // may be empty

            if (hasMediaType) {
                assertThat(credit.media_type).isNotEmpty();
            }
        }
    }

    private void assertCrewCredits(PersonCredits credits, boolean hasMediaType) {
        // assert crew credits
        assertThat(credits.crew).isNotEmpty();
        for (PersonCrewCredit credit : credits.crew) {
            // may be empty, but should exist
            assertThat(credit.department).isNotNull();
            assertThat(credit.job).isNotNull();

            if (hasMediaType) {
                assertThat(credit.media_type).isNotEmpty();
            }
        }
    }

}
