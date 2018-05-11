package com.example.scott.beerdiary2;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateBeerEntryTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void createBeerEntryTest() throws InterruptedException {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.add_beer), withText("Add Beer"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.beer_name_input),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("TEst Name"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.beer_name_input), withText("TEst Name"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.beer_name_input), withText("TEst Name"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText3.perform(scrollTo(), replaceText("Test Name"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.beer_name_input), withText("Test Name"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        Thread.sleep(2000);

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.beer_style_dropdown),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                12)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.hop_name),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.bittering_hops_list),
                                                1)),
                                0)));
        appCompatEditText5.perform(scrollTo(), replaceText("cascade"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.hop_amount),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.bittering_hops_list),
                                                1)),
                                1)));
        appCompatEditText6.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.hop_name),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.flavor_hops_list),
                                                1)),
                                0)));
        appCompatEditText7.perform(scrollTo(), replaceText("cascade"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.hop_amount),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.flavor_hops_list),
                                                1)),
                                1)));
        appCompatEditText8.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.hop_name),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.aroma_hops_list),
                                                1)),
                                0)));
        appCompatEditText9.perform(scrollTo(), replaceText("cascade"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.hop_amount),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.aroma_hops_list),
                                                1)),
                                1)));
        appCompatEditText10.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.hop_name),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.knockout_hops_list),
                                                1)),
                                0)));
        appCompatEditText11.perform(scrollTo(), replaceText("cascade"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.hop_amount),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.knockout_hops_list),
                                                1)),
                                1)));
        appCompatEditText12.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.hop_name),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.dry_hops_list),
                                                1)),
                                0)));
        appCompatEditText13.perform(scrollTo(), replaceText("cascade"), closeSoftKeyboard());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.hop_amount),
                        childAtPosition(
                                allOf(withId(R.id.hop_row_layout),
                                        childAtPosition(
                                                withId(R.id.dry_hops_list),
                                                1)),
                                1)));
        appCompatEditText14.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.preboil_gravity),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                25)));
        appCompatEditText15.perform(scrollTo(), replaceText("1.012"), closeSoftKeyboard());

        pressBack();

        ViewInteraction customEditText = onView(
                allOf(withId(R.id.original_gravity),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                27)));
        customEditText.perform(scrollTo(), replaceText("1.011"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.final_gravity),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                29)));
        appCompatEditText16.perform(scrollTo(), replaceText("1.09"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.notes_input),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                34)));
        appCompatEditText17.perform(scrollTo(), replaceText("notes"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.save_button), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                35)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvBeers),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        pressBack();

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.beer_style_dropdown),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                12)));
        appCompatSpinner2.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.save_button), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                35)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.rvBeers),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0)),
                        0),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
