package com.example.scott.beerdiary2;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParentIndex;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.scott.beerdiary2.Utils.nthChildOf;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Scott on 5/31/18.
 */

public class RemoveBeerFromList {

    String beerName = "Summer Forever";

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(
            ListActivity.class);

    @Test
    public void removeBeerFromList() {
        //Tap Add Beer button
        onView(withId(R.id.add_beer)).perform(click());

        //Enter beer name
        onView(withId(R.id.beer_name_input)).perform(replaceText(beerName));

        Espresso.closeSoftKeyboard();

        //Tap Save button
        onView(withId(R.id.save_button)).perform(scrollTo());
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        onView(withId(R.id.save_button)).perform(click());

        //verify the beer entry exists
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }

        onView(withText(beerName)).check(matches(isDisplayed()));
        onView(withId(R.id.delete_button)).perform(click());
        onView(nthChildOf(withId(R.id.rvBeers), 0)).check(doesNotExist());

    }
}