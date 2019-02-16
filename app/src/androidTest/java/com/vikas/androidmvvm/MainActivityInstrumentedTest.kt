package com.vikas.androidmvvm

import android.arch.lifecycle.ViewModelProviders
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.vikas.androidmvvm.viewmodels.CountryDetailViewModel
import com.vikas.androidmvvm.views.CountryDetailFragment
import com.vikas.androidmvvm.views.MainActivity
import com.vikas.androidmvvm.views.MyCountryDetailRecyclerViewAdapter
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers.greaterThan
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit.rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val mainActivityActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.vikas.android_mvvm", appContext.packageName)
    }
    @Test
    fun checkProgressIsShown(){
        Espresso.onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfDataVisible(){
        Espresso.onView(withId(R.id.list)).check(matches(isDisplayed()))
    }
    @Test
    fun checkIfToastDisplayedOnClick(){
        getInstrumentation().waitForIdleSync()
        Thread.sleep(5000)
        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickOnViewChild(R.id.txtTitle))
        )
        // Espresso.onView(withText("Clicked")).check(matches(isDisplayed()))
    }
    private fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }
    /*@Test
    fun testInsureFrameLayout(){
        val activity = mainActivityActivityTestRule.activity
        val viewById = activity.findViewById(R.id.flContainer) as View
        assertThat(viewById, instanceOf(FrameLayout::class.java))

        val fragmentList = activity.supportFragmentManager.fragments
        assertEquals(fragmentList.size, 1)
        var fragment = fragmentList.get(0) as Fragment
        assertThat(fragmentList.get(0), instanceOf(CountryDetailFragment::class.java))
        fragment = fragment as CountryDetailFragment

        // fragment testing
        val view = fragment.getView()
        var recyclerView = view?.findViewById(R.id.list) as RecyclerView
        assertThat(recyclerView, instanceOf(RecyclerView::class.java))
        assertEquals((view.findViewById(R.id.progressBar) as ProgressBar).visibility, View.VISIBLE)
        recyclerView = recyclerView

        var newsViewModel = ViewModelProviders.of(activity).get(CountryDetailViewModel::class.java)
        newsViewModel.getCountryDetails()
        Thread.sleep(4000)
        assertEquals((view.findViewById(R.id.progressBar) as ProgressBar).visibility, View.VISIBLE)
        var newsAdapter = (recyclerView as RecyclerView).adapter as MyCountryDetailRecyclerViewAdapter?

        val count = newsAdapter?.getItemCount()
        assertThat(count, greaterThan(0)!!)}*/
}
