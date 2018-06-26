package com.example.scott.beerdiary2;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.PickerActions.setDate;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static com.example.scott.beerdiary2.Utils.nthChildOf;


/**
 * Created by Scott on 4/16/18.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddBeerToList {

    String beerName = "Summer Forever";

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(
            ListActivity.class);

    @Test
    public void addBeerToList() {
        //Tap Add Beer button
        onView(allOf(withId(R.id.add_beer), withText("Add Beer"))).perform(click());

        //Enter beer name
        onView(withId(R.id.beer_name_input)).perform(replaceText(beerName));

        //Set brew date
        onView(withId(R.id.brew_date_input)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(setDate(2018, 5, 3));

        onView(withText("OK"))
                .perform(click());

        //Enter beer type
        onView(withId(R.id.beer_style_dropdown)).perform(scrollTo(), click());
        onData(allOf(is(instanceOf(String.class)), is("Wheat")))
                .perform(click());

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
        onView(nthChildOf(withId(R.id.rvBeers), 0)).check(matches(hasDescendant(withText(beerName))));
    }

}
