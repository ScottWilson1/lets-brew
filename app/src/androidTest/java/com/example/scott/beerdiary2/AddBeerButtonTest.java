package com.example.scott.beerdiary2;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static org.hamcrest.core.AllOf.allOf;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by Scott on 3/25/18.
 */

@RunWith(AndroidJUnit4.class)
public class AddBeerButtonTest {
    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(
            ListActivity.class);

    @Test
    public void verifyAddBeerText() throws Exception {
        onView(withId(R.id.add_beer)).check(matches(withText("Add Beer")));
    }

    //@Test
   // public void verifyAddBeerClick() throws Exception {
        //onView(withId(R.id.add_beer)).perform(click());
        //intended(hasComponent(new ComponentName(getTargetContext(), ExpectedActuvity.class)));
    //}

}
