package com.rjt.projectmanagementsystem.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.rjt.projectmanagementsystem.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void registerActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.link_signup), withText("No account yet? Create one!"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("rash"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.input_name), withText("rash"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.input_name), withText("rash"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText3.perform(scrollTo(), replaceText("rashm"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.input_name), withText("rashm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.input_name), withText("rashm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText5.perform(scrollTo(), click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.input_last_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.input_name), withText("rashm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText7.perform(scrollTo(), replaceText(""));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.input_last_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText9.perform(scrollTo(), replaceText("a"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.input_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText10.perform(scrollTo(), replaceText("rashmi"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.input_name), withText("rashmi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText11.perform(scrollTo(), click());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.input_name), withText("rashmi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText12.perform(scrollTo(), click());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.input_name), withText("rashmi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameLayout),
                                        0),
                                0)));
        appCompatEditText13.perform(scrollTo(), click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.input_email),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.emailLayout),
                                        0),
                                0)));
        appCompatEditText14.perform(scrollTo(), replaceText("arm06@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.input_mobile),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.phoneLayout),
                                        0),
                                0)));
        appCompatEditText15.perform(scrollTo(), replaceText("13129375549"), closeSoftKeyboard());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.input_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.passwordLayout),
                                        0),
                                0)));
        appCompatEditText16.perform(scrollTo(), replaceText("1234567"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.company_size),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.companySizeLayout),
                                        0),
                                0)));
        appCompatEditText17.perform(scrollTo(), replaceText("5"), closeSoftKeyboard());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.company_role),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.companyRoleLayout),
                                        0),
                                0)));
        appCompatEditText18.perform(scrollTo(), replaceText("Android Developer"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.input_last_name), withText("a"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText19.perform(scrollTo(), replaceText("all"));

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.input_last_name), withText("all"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText20.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.input_last_name), withText("all"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText21.perform(scrollTo(), click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.input_last_name), withText("all"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText22.perform(scrollTo(), replaceText("Allan"));

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText23.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText24.perform(scrollTo(), click());

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText25.perform(scrollTo(), replaceText("Allan"));

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText26.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText27.perform(scrollTo(), click());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText28.perform(scrollTo(), click());

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0)));
        appCompatEditText29.perform(scrollTo(), replaceText("Allan"));

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.input_last_name), withText("Allan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText30.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_signup), withText("Create Account"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatButton2.perform(scrollTo(), click());

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
