package com.team9.seatonvalley;



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

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

/**
 * @Author: Dean Hunter, Student Number: 16027456
 * @Since: 14/04/2018.
 *
 * This class is used to test the services activity, including all of its intents.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     *   Initiate testing
     */
    @Test
    public void homeActivityTest() {
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
     *   Test to see if toolbar exists
     */
    @Test
    public void testToolBarExists() {
        // Check there is a toolbar
        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.toolbar),
                        childAtPosition(
                                allOf(withId(R.id.main_bar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }

    /**
     *  Test toolbar title is Seaton Valley Community Council
     */
    @Test
    public void testToolBarTextMatches(){

        // Check the title in the toolbar is "Seaton Valley Community Council"
        ViewInteraction textView = onView(
                allOf(withText("Seaton Valley Community Council"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.main_bar),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Seaton Valley Community Council")));

    }

    /**
     *  Test toolbar title overflow menu exists
     */
    @Test
    public void testToolBarOverflowExists() {
        // Check there is a more options tab on the toolbar
        ViewInteraction imageView = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    /**
     *  Test grid view menu with buttons for features exists
     */
    @Test
    public void testMenuExists() {
        // Check the grid view exists for the buttons
        ViewInteraction gridView = onView(
                allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        gridView.check(matches(isDisplayed()));

        // Check there is a button for latest
        ViewInteraction imageView2 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.activity_menu_buttons),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        // Check there is a button for tweets
        ViewInteraction imageView3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.activity_menu_buttons),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        1),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        // Check there is a button for report it
        ViewInteraction imageView4 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.activity_menu_buttons),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        2),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        // Check there is a button for futures
        ViewInteraction imageView5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.activity_menu_buttons),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        3),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        // Check there is a button for contact us
        ViewInteraction imageView6 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.activity_menu_buttons),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        4),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        // Check there is a button for services
        ViewInteraction imageView7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.activity_menu_buttons),
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        1)),
                        5),
                        isDisplayed()));
        imageView7.check(matches(isDisplayed()));

    }

    /**
     * Test settings intent in home activity goes to settings
     */
    @Test
    public void testSettingsIntent() {
        // Open the overflow options menu to see if settings exists.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click settings, checking if it leads to settings activity which it does
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3214302);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the back button to go back to home activity
        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0)),
                        1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    /**
     * Test latest intent in home activity goes to latest
     */
    @Test
    public void testLatestIntent() {


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3588949);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click latest, checking if it leads to latest activity which it does
        DataInteraction imageView8 = onData(anything())
                .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)))
                .atPosition(0);
        imageView8.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3592350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the back button to go back to home activity
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.latest_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

    }

    /**
     * Test tweets intent in home activity goes to tweets
     */
    @Test
    public void testTweetsIntent() {


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3591300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click tweets, checking if it leads to tweets activity which it does
        DataInteraction imageView9 = onData(anything())
                .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)))
                .atPosition(1);
        imageView9.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593708);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the back button to go back to home activity
        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.services_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

    }

    /**
     * Test report it intent in home activity goes to report it
     */
    @Test
    public void testReportItIntent() {


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3590615);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click report it, checking if it leads to report it activity which it does
        DataInteraction imageView10 = onData(anything())
                .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)))
                .atPosition(2);
        imageView10.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593105);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the back button to go back to home activity
        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.report_issues_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

    }

    /**
     * Test futures intent in home activity goes to futures
     */
    @Test
    public void testFuturesIntent() {


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593593);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click futures, checking if it leads to futures activity which it does
        DataInteraction imageView11 = onData(anything())
                .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)))
                .atPosition(3);
        imageView11.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3594100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the back button to go back to home activity
        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.futures_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

    }

    /**
     * Test contact us intent in home activity goes to contact us
     */
    @Test
    public void testContactUsIntent(){

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3591787);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click contact us, checking if it leads to contact us activity which it does
        DataInteraction imageView12 = onData(anything())
                .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)))
                .atPosition(4);
        imageView12.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593513);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the back button to go back to home activity
        ViewInteraction appCompatImageButton6 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.contact_us_toolbar),
                                        childAtPosition(
                                                withId(R.id.appBarLayout2),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

    }

    /**
     * Test services intent in home activity goes to services
     */
    @Test
    public void testServicesIntent(){

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3589291);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click services, checking if it leads to services activity which it does
        DataInteraction imageView13 = onData(anything())
                .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)))
                .atPosition(5);
        imageView13.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593367);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the back button to go back to home activity
        ViewInteraction appCompatImageButton7 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.services_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.support.design.widget.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

    }

    /**
     *  Get the child ( widget ) at the position. This is used for the grid view in order
     * to get the activity related to the button by intent.
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
