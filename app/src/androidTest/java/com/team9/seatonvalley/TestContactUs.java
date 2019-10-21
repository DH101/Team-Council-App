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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
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
 * @Author Alex Newton-Green
 * @Since: 16/04/2018
 *
 * Tests the Contact Us form
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestContactUs {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     *   Initiate testing
     */
    @Test
    public void testContactUs() {
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
     * Test toolbar exists
     */
    @Test
    public void testActionBarExists() {

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.contact_us_toolbar),
                        childAtPosition(
                                allOf(withId(R.id.appBarLayout2),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));
    }

    /**
     * Test toolbar title is correct
     */
    @Test
    public void testActionBarTitleIsCorrect() {

        ViewInteraction textView = onView(
                allOf(withText("Contact Us"),
                        childAtPosition(
                                allOf(withId(R.id.contact_us_toolbar),
                                        childAtPosition(
                                                withId(R.id.appBarLayout2),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Contact Us")));
    }

    /**
     * Test toolbar overflow menu exists
     */
    @Test
    public void testActionBarOptionSettingsExists() {

        ViewInteraction imageView2 = onView(
                allOf(withContentDescription("More options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.contact_us_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));
    }

    /**
     * Test intro text is correct
     */
    @Test
    public void testIntroTextIsCorrect() {
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.contactInfo), withText("You can contact the council in person during opening times, by post, telephone or email."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("You can contact the council in person during opening times, by post, telephone or email.")));
    }

    /**
     * Test phone header is correct
     */
    @Test
    public void testPhoneHeaderIsCorrect() {
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.council_phone), withText("CALL US"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("CALL US")));
    }

    /**
     * Test phone number is correct
     */
    @Test
    public void testPhoneNumberIsCorrect() {
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.council_phone_content), withText("01912379870"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                2),
                        isDisplayed()));
        textView4.check(matches(withText("01912379870")));
    }

    /**
     * Test email header is correct
     */
    @Test
    public void testEmailHeaderIsCorrect() {

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.council_email), withText("EMAIL US"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                3),
                        isDisplayed()));
        textView5.check(matches(withText("EMAIL US")));
    }

    /**
     * Test email is correct
     */
    @Test
    public void testEmailAddressIsCorrect() {

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.council_email_content), withText("clerk@seatonvalleycommunitycouncil.gov.uk"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                4),
                        isDisplayed()));
        textView6.check(matches(withText("clerk@seatonvalleycommunitycouncil.gov.uk")));
    }

    /**
     * Test find us header is correct
     */
    @Test
    public void testFindUsHeaderIsCorrect() {

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.council_address), withText("FIND US"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                5),
                        isDisplayed()));
        textView7.check(matches(withText("FIND US")));
    }

    /**
     * Test address is correct
     */
    @Test
    public void testAddressIsCorrect() {

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.council_address_content), withText("20–22 Astley Road\nSeaton Delaval\nNorthumberland\nNE25 0DG"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));
        textView8.check(matches(withText("20–22 Astley Road Seaton Delaval Northumberland NE25 0DG")));
    }

    /**
     * Test map button is exists
     */
    @Test
    public void testMapButtonExists() {

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.findOnMap), withContentDescription("Find on map"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                7),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));
    }

    /**
     * Test opening header is correct
     */
    @Test
    public void testOpeningTimesHeaderIsCorrect() {

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.council_opening_times), withText("OPENING TIMES"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                8),
                        isDisplayed()));
        textView9.check(matches(withText("OPENING TIMES")));
    }

    /**
     * Test opening times is correct
     */
    @Test
    public void testOpeningTimesAreCorrect() {

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.council_opening_times_content), withText("Monday - Thursday\n8:30am - 4:30pm"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                9),
                        isDisplayed()));
        textView10.check(matches(withText("Monday - Thursday 8:30am - 4:30pm")));
    }

    /**
     * Test form header is correct
     */
    @Test
    public void testFormHeaderIsCorrect() {

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.form), withText("FORM"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                10),
                        isDisplayed()));
        textView11.check(matches(withText("FORM")));
    }

    /**
     * Test name text is correct
     */
    @Test
    public void testNameTextIsCorrect() {

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.your_name_t), withText("Your Name (Required):"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                11),
                        isDisplayed()));
        textView12.check(matches(withText("Your Name (Required):")));
    }

    /**
     * Test name field is correct
     */
    @Test
    public void testNameFieldExists() {

        ViewInteraction editText = onView(
                allOf(withId(R.id.your_name),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                12),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));
    }

    /**
     * Test email text is correct
     */
    @Test
    public void testEmailTextIsCorrect() {

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.your_email_t), withText("Your Email (Required):"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                13),
                        isDisplayed()));
        textView13.check(matches(withText("Your Email (Required):")));
    }

    /**
     * Test email field is correct
     */
    @Test
    public void testEmailFieldExists() {

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.your_email),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                14),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));
    }

    /**
     * Test subject text is correct
     */
    @Test
    public void testSubjectTextIsCorrect() {

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.your_subject_t), withText("Subject (Required):"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                15),
                        isDisplayed()));
        textView14.check(matches(withText("Subject (Required):")));
    }

    /**
     * Test subject field is correct
     */
    @Test
    public void testSubjectFieldExists() {

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.your_subject),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                16),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));
    }

    /**
     * Test message text is correct
     */
    @Test
    public void testMessageTextIsCorrect() {

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.your_message_t), withText("Your Message (Required):"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                17),
                        isDisplayed()));
        textView15.check(matches(withText("Your Message (Required):")));
    }

    /**
     * Test message field exists
     */
    @Test
    public void testMessageFieldExists() {

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.your_message),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                18),
                        isDisplayed()));
        editText4.check(matches(isDisplayed()));
    }

    /**
     * Test send button exists
     */
    @Test
    public void testSendButtonExists() {

        ViewInteraction button = onView(
                allOf(withId(R.id.post_message),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                19),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    /**
     * Test options button works
     */
    @Test
    public void testOptionsButtonWorks() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
    }

    /**
     * Test settings name is correct
     */
    @Test
    public void testSettingsNameIsCorrect() {

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView16.check(matches(withText("Settings")));
    }

    /**
     * Test error appears for messages show with empty field and invalid email addresses
     */
    @Test
    public void testErrorMessagesShowWithEmptyFieldsAndInvalidEmailAddresses() {

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.post_message), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                19)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.your_name),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                12)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.your_name),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                12)));
        appCompatEditText2.perform(scrollTo(), replaceText("Alex Newton-Green"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.post_message), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                19)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.your_email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.your_email),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText4.perform(scrollTo(), replaceText("alex"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.post_message), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                19)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.your_email), withText("alex"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.your_email), withText("alex"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText6.perform(scrollTo(), replaceText("alex@te"));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.your_email), withText("alex@te"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14),
                        isDisplayed()));
        appCompatEditText7.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.your_email), withText("alex@te"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText8.perform(scrollTo(), replaceText("alex@team9"));

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.your_email), withText("alex@team9"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14),
                        isDisplayed()));
        appCompatEditText9.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.post_message), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                19)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.your_email), withText("alex@team9"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText10.perform(scrollTo(), click());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.your_email), withText("alex@team9"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText11.perform(scrollTo(), click());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.your_email), withText("alex@team9"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14)));
        appCompatEditText12.perform(scrollTo(), replaceText("alex@team9.com"));

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.your_email), withText("alex@team9.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                14),
                        isDisplayed()));
        appCompatEditText13.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.post_message), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                19)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.your_subject),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                16)));
        appCompatEditText14.perform(scrollTo(), click());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.your_subject),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                16)));
        appCompatEditText15.perform(scrollTo(), replaceText("Subject"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.post_message), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                19)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.your_message),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                18)));
        appCompatEditText16.perform(scrollTo(), click());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.your_message),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                18)));
        appCompatEditText17.perform(scrollTo(), replaceText("Message"), closeSoftKeyboard());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.your_message), withText("Message"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                18),
                        isDisplayed()));
        appCompatEditText19.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.post_message), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                19)));
        appCompatButton7.perform(scrollTo(), click());
    }

    /**
     * Test map button works
     */
    @Test
    public void testMapButtonWorks() {

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.findOnMap), withContentDescription("Find on map"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        appCompatImageButton.perform(scrollTo(), click());
    }

    /**
     * Test settings button works
     */
    @Test
    public void testSettingsButtonWorks() {

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        pressBack();
    }

    /**
     * Test back button works
     */
    @Test
    public void testBackButtonWorks() {

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.contact_us_toolbar),
                                        childAtPosition(
                                                withId(R.id.appBarLayout2),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

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
