package com.example.scott.beerdiary2;

import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Scott on 5/11/18.
 */

public class Utils {
    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                if(!(view.getParent() instanceof ViewGroup)) return false;
                ViewGroup parent = (ViewGroup) view.getParent();

                return parentMatcher.matches(parent)
                        && parent.getChildCount() > childPosition
                        && parent.getChildAt(childPosition).equals(view);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("position " + childPosition + " of parent");
                parentMatcher.describeTo(description);
            }
        };
    }


}
