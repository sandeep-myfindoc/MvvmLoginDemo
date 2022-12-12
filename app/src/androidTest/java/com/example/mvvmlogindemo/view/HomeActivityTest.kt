package com.example.mvvmlogindemo.view

import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.mvvmlogindemo.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    @OptIn(ExperimentalPagingApi::class)
    @Rule
    @JvmField
    public var activityRule = ActivityTestRule(HomeActivity::class.java)


    @Before
    fun setUp() {

    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun testRecyclerView(){
        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        //Pass
        Espresso.onView(withId(R.id.rvNotes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,click()))

        //Pass
        /*var recyclerView:RecyclerView = activityRule.activity.findViewById(R.id.rvNotes)
        var itemCount = recyclerView.adapter?.itemCount
        if(itemCount!=null){
            Espresso.onView(withId(R.id.rvNotes)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount.minus(1)))
        }*/

        //Pass
        /*Espresso.onView(withId(R.id.rvNotes)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        var tvAuthor: String = "Michael Jordan"
        Espresso.onView(withText(tvAuthor)).check(matches(isDisplayed()))*/
    }
    @After
    fun tearDown() {

    }
}