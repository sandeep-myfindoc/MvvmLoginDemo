package com.example.mvvmlogindemo.view
import android.content.Intent
import androidx.paging.ExperimentalPagingApi
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Assert.*
import com.example.mvvmlogindemo.R
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LoginActivityTest {
    @OptIn(ExperimentalPagingApi::class)
    @get:Rule
    val activityScenarioRule = activityScenarioRule<LoginActivity>()
    @Before
    fun setUp() {
    }
    @Test
    fun testLoginButton(){
        //onView(withId(R.id.btnLogin)).perform(click())
    }
    /*
    * Test case to test share functionality
    * */
   /* @Test
    fun testShareButton_expectedIntentChooser(){
        Intents.init()
        val expected = allOf(hasAction(Intent.ACTION_SEND))//"android.intent.action.SEND"
        onView(withId(R.id.btnShare)).perform(click())
        intended(expected)
        Intents.release()
    }*/
    /*@After
    fun tearDown() {
    }*/
}