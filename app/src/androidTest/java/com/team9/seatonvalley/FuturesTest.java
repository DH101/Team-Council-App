package com.team9.seatonvalley;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * @Author: Adrienne Hui,
 * @Since: 16/04/2018.
 *
 * This class is used to test the futures activity.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FuturesTest {

    @Rule
    public ActivityTestRule<FuturesActivity> mFuturesActivityTestRule =
            new ActivityTestRule<>(FuturesActivity.class);

    /**
     *   Initiate testing
     */
    @Test
    public void futureActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test to see if toolbar exists
     */
    @Test
    public void testToolBarExist() {

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.futures_toolbar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }


    /**
     * Test to see if toolbar title matches futures
     */
    @Test
    public void testActionBarTitle() {
        ViewInteraction textView = onView(
                allOf(withText("Futures"),
                        childAtPosition(
                                allOf(withId(R.id.futures_toolbar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Futures")));
    }

    /**
     * Test to see if toolbar overflow menu exists
     */
    @Test
    public void testSettingsExists(){
        ViewInteraction imageView2 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.futures_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }

    /**
     * Test to see if button leads to future
     */
    @Test
    public void testViewButton() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.pdfDisplay), withText("View"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.futures_card_view),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

    }

    /**
     * Test to see if back button exists
     */
    @Test
    public void testBackButtonExists(){
        ViewInteraction imageBack = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.futures_toolbar),
                                        1),
                                0),
                        isDisplayed()));
        imageBack.check(matches(isDisplayed()));
    }

    /**
     *  Get the child ( widget ) at the position.
     */
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
