package com.team9.seatonvalley;



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


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * @Author: Dalton Yates
 * @Since: 16/04/2018
 *
 * Tests the latest activity
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LatestTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     *   Initiate testing
     */
    @Test
    public void latestTest() {
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
    public void testActionBarExists() {

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.latest_toolbar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }

    /**
     * Test to see if toolbar title matches seaton valley latest
     */
    @Test
    public void testActionBarTitleIsCorrect() {
        ViewInteraction textView = onView(
                allOf(withText("Seaton Valley Latest"),
                        childAtPosition(
                                allOf(withId(R.id.latest_toolbar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Seaton Valley Latest")));
    }

    /**
     * Test to see if toolbar overflow menu exists
     */
    @Test
    public void testSettingsExists() {
        ViewInteraction imageView2 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.latest_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }

    /**
     * Test to see if search bar exists.
     */
    @Test
    public void testSearchBarExists() {
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.search_bar),
                        childAtPosition(
                                allOf(withId(R.id.eventSearchBar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));
    }

    /**
     * Test events list exists.
     */
    @Test
    public void testEventsListExists() {
        ViewInteraction relativeLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.eventsList),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1)),
                        0),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));
    }

    /**
     * Test to see if back button exists
     */
    @Test
    public void testBackButtonExists() {
        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.latest_toolbar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));
    }

    /**
     * Test tab goes to news
     */
    @Test
    public void testGoToNews() {
        ViewInteraction tabView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.mTab_ID),
                                0),
                        1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.pager),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        viewPager.perform(swipeLeft());
    }

    /**
     * Test news list exists.
     */
    @Test
    public void testNewsListExists() {
        ViewInteraction listView2 = onView(
                allOf(withId(R.id.newsList),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        listView2.check(matches(isDisplayed()));
    }

    /**
     * Test read more button leads to web page related to news card view.
     */
    @Test
    public void testReadMore() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.newsReadMore), withText("Read more"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_card_view),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

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
