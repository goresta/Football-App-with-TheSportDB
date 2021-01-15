@file:Suppress("DEPRECATION")

package com.tatak.footballapp.ui.searchmatches


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.tatak.footballapp.R
import com.tatak.footballapp.ui.SplashScreenActivity

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchMatchActivityInstrumentedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashScreenActivity::class.java)

    @Test
    fun searchMatchActivityInstrumentedTest() {

        Thread.sleep(6000)

        //check searchBar
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.actionSearch), withContentDescription("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolbar_main),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )

        delay()

        actionMenuItemView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )

        delay()

        //example search on searchBar
        searchAutoComplete.perform(replaceText("Arsenal"), closeSoftKeyboard())

        val searchAutoComplete2 = onView(
            allOf(
                withId(R.id.search_src_text), withText("Arsenal"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(pressImeActionButton())

        delay()

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun delay(){
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}
