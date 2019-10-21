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

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

/**
 * @Author: Andreaa Stirbu
 * @Since: 16/04/2018
 *
 * Tests the report an issue form that the user inputs information into
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReportIssueFormTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     *   Initiate testing
     */
    @Test
    public void reportIssueForm() {
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
     * Tests the activity loads
     */
    @Test
    public void checkIfReportIssueFormActivityLoad() {
        DataInteraction imageView = onData(anything())
                .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                        childAtPosition(
                                withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                1)))
                .atPosition(2);
        imageView.perform(click());
    }

    /**
     * Test the activity loads after activity from report issue activity is clicked.
     */
    @Test
    public void testIfReportIssueFromActivityLoadsAfterPressingTheCardView() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3592977);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction cardView = onView(
                allOf(withId(R.id.report_issue_card_view),
                        childAtPosition(
                                allOf(withId(R.id.report_issue_layout),
                                        childAtPosition(
                                                withId(R.id.recycler_view_report_issues),
                                                0)),
                                0),
                        isDisplayed()));
        cardView.perform(click());
    }

    /**
     * Test to see if toolbar exists
     */
    @Test
    public void checkIfToolbarExists() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3593393);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.report_issue_form_toolbar),
                        childAtPosition(
                                allOf(withId(R.id.appBarLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }

    /**
     * Check Edit text exists for user name
     */
    @Test
    public void checkIfNameFieldExists() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.their_name),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("")));
    }

    /**
     * Check Edit text exists for email
     */
    @Test
    public void checkIfEmailFieldExists() {
        ViewInteraction editText2 = onView(
                allOf(withId(R.id.their_email),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                3),
                        isDisplayed()));
        editText2.check(matches(withText("")));
    }

    /**
     * Check Edit text exists for phone
     */
    @Test
    public void checkIfPhoneFieldExists() {
        ViewInteraction editText3 = onView(
                allOf(withId(R.id.their_phone),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                5),
                        isDisplayed()));
        editText3.check(matches(withText("")));
    }

    /**
     * Check Edit text exists for issue and location description
     */
    @Test
    public void checkIfIssueAndLocationFieldExists() {
        ViewInteraction editText4 = onView(
                allOf(withId(R.id.their_issue_and_location),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                7),
                        isDisplayed()));
        editText4.check(matches(withText("")));
    }

    /**
     * Check check box exists for permissions
     */
    @Test
    public void checkIfCheckBoxExists() {
        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.confirm_policy),
                        childAtPosition(
                                allOf(withId(R.id.checkbox_confirm_policy),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                8)),
                                0)));
        appCompatCheckBox.perform(scrollTo(), click());
    }

    /**
     * Check report issue form submit button exists
     */
    @Test
    public void checkIfReportIssueButtonExists() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.post_issue), withText("Report issue"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        appCompatButton.perform(scrollTo(), click());
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
