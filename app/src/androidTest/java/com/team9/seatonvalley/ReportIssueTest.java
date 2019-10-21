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
 * @Author: Andreaa Stirbu
 * @Since: 16/04/2018
 *
 * Tests the report an issue user interface
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReportIssueTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     *   Initiate testing
     */
    @Test
    public void reportIssueTest() {
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
     public void checkIfRepportIssuesActivityLoads() {
         // Added a sleep statement to match the app's execution delay.
         // The recommended way to handle such scenarios is to use Espresso idling resources:
         // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
         try {
             Thread.sleep(3582348);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
            DataInteraction imageView = onData(anything())
                    .inAdapterView(allOf(withId(R.id.activity_menu_buttons),
                            childAtPosition(
                                    withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                    1)))
                    .atPosition(2);
            imageView.perform(click());
        }

    /**
     * Test if toolbar title is report issue
      */
    @Test
    public void testActionBarTitleIsCorrect(){
        ViewInteraction textView = onView(
                allOf(withText("Report Issue"),
                        childAtPosition(
                                allOf(withId(R.id.report_issues_toolbar),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Report Issue")));
    }


    /**
     * Test to see if toolbar exists
     */
    @Test
    public void checkIfToolbarExists() {
        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.report_issues_toolbar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.app_bar_report_issue),
                                        0),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }

    /**
     * Test settings overflow menu exists
     */
    @Test
    public void testSettingsExists(){
            ViewInteraction imageView2 = onView(
                    allOf(withContentDescription("More options"),
                            childAtPosition(
                                    childAtPosition(
                                            withId(R.id.report_issues_toolbar),
                                            2),
                                    0),
                            isDisplayed()));
            imageView2.check(matches(isDisplayed()));
    }

    /**
     *  Test recycler view exists
     */
    @Test
    public void checkIfRecyclerViewExists(){
            ViewInteraction recyclerView = onView(
                    allOf(withId(R.id.recycler_view_report_issues),
                            childAtPosition(
                                    allOf(withId(R.id.app_bar_report_issue),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    1),
                            isDisplayed()));
            recyclerView.check(matches(isDisplayed()));
    }

    /**
     * Test card views in recycler view exists
     */
    @Test
    public void checkIfCardViewsExists() {
            ViewInteraction relativeLayout = onView(
                    allOf(withId(R.id.report_issue_card_view_content),
                            childAtPosition(
                                    allOf(withId(R.id.report_issue_card_view),
                                            childAtPosition(
                                                    withId(R.id.report_issue_layout),
                                                    0)),
                                    0),
                            isDisplayed()));
            relativeLayout.check(matches(isDisplayed()));
    }

    /**
     * Test report issue icon exists
     */
    @Test
    public void checkIfReportIssueIconExists() {
        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.report_issue_icon), withContentDescription("Report It Icon"),
                        childAtPosition(
                                allOf(withId(R.id.report_issue_card_view_content),
                                        childAtPosition(
                                                withId(R.id.report_issue_card_view),
                                                0)),
                                0),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));
    }

    /**
     * Check report issue title exists and is correct
     */
    @Test
     public void checkIfReportIssueTitleExistsAndItIsCorrect() {
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.report_issue_title), withText("Dog Fouling"),
                        childAtPosition(
                                allOf(withId(R.id.report_issue_card_view_content),
                                        childAtPosition(
                                                withId(R.id.report_issue_card_view),
                                                0)),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Dog Fouling")));
     }

    /**
     *  Test report issue description exists and is correct
     */
    @Test
    public void checkIfReportIssueDescriptionExistsAndItIsCorrect() {
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.report_issue_description), withText("Report dog fouling that takes places on highway or public places"),
                        childAtPosition(
                                allOf(withId(R.id.report_issue_card_view_content),
                                        childAtPosition(
                                                withId(R.id.report_issue_card_view),
                                                0)),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("Report dog fouling that takes places on highway or public places")));
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
