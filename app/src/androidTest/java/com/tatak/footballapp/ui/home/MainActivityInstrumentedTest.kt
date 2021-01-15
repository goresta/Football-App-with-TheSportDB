@file:Suppress("DEPRECATION")

package com.tatak.footballapp.ui.home


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.tatak.footballapp.R
import com.tatak.footballapp.ui.SplashScreenActivity

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashScreenActivity::class.java)

    @Test
    fun mainActivityInstrumentedTest() {

        Thread.sleep(6000)

        val tabView = onView(
            allOf(
                withContentDescription("Upcoming Match"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        delay()

        tabView.perform(click())

        val tabView2 = onView(
            allOf(
                withContentDescription("Last Match"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )

        delay()

        tabView2.perform(click())

        val relativeLayout = onView(
            childAtPosition(
                allOf(
                    withId(R.id.rvFootballLast),
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        0
                    )
                ),
                0
            )
        )

        delay()

        relativeLayout.perform(scrollTo(), click())

        val actionMenuItemView = onView(
            allOf(
                withId(R.id.favorite), withContentDescription("Favorite"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )

        delay()

        actionMenuItemView.perform(click())

        pressBack()

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.favNav), withContentDescription("Favorite"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

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
