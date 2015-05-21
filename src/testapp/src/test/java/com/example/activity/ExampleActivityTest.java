package com.example.activity;

import com.appstax.AppstaxObject;
import com.appstax.android.Appstax;
import com.appstax.android.Callback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class ExampleActivityTest {

    private static final String APP_KEY_1 = "YourApiKey";
    private static final String APP_KEY_2 = "SomeAppKey";
    private static final String API_URL_1 = com.appstax.Appstax.getApiUrl();

    private static final String COLLECTION_COUNT = "CountCollection";
    private static final String COLLECTION_BLANK = "BlankCollection";

    @Before
    public void before() {
        Appstax.setAppKey(APP_KEY_1);
        Appstax.setApiUrl(API_URL_1);
    }

    @Test
    public void testRoboelectricSetup() throws Exception {
        assertTrue(Robolectric.buildActivity(ExampleActivity.class).create().get() != null);
    }

    @Test
    public void testAppKey() {
        assertEquals(APP_KEY_1, Appstax.getAppKey());
        Appstax.setAppKey(APP_KEY_2);
        assertEquals(APP_KEY_2, Appstax.getAppKey());
    }

    @Test
    public void testApiUrl() {
        assertEquals(API_URL_1, Appstax.getApiUrl());
    }

    @Test
    public void testObjectCreate() {
        AppstaxObject object = new AppstaxObject(COLLECTION_COUNT);
        object.put("title", "hello");
        object.put("count", 42);
        assertEquals(COLLECTION_COUNT, object.getCollection());
        assertEquals("hello", object.get("title"));
        assertEquals(42, object.get("count"));
    }

    @Test
    public void testObjectSave() {
        Appstax.save(new AppstaxObject(COLLECTION_BLANK), new Callback<AppstaxObject>() {
            public void onSuccess(AppstaxObject output) {
                assertTrue(false);
            }
            public void onError(Exception e) {
                assertTrue(e.getMessage().startsWith("Not authorized."));
            }
        });
    }

    @Test
    public void testObjectRemove() {
        Appstax.remove(new AppstaxObject(COLLECTION_BLANK), new Callback<AppstaxObject>() {
            public void onSuccess(AppstaxObject output) {
                assertTrue(false);
            }
            public void onError(Exception e) {
                assertTrue(e.getMessage().startsWith("Not authorized."));
            }
        });
    }

    @Test
    public void testObjectFilter() {
        Appstax.filter(COLLECTION_BLANK, "Age > 42", new Callback<List<AppstaxObject>>() {
            public void onSuccess(List<AppstaxObject> output) {
                assertTrue(false);
            }
            public void onError(Exception e) {
                assertTrue(e.getMessage().startsWith("Not authorized."));
            }
        });
    }

}