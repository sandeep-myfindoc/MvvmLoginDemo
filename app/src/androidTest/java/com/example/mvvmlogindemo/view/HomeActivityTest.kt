package com.example.mvvmlogindemo.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.mvvmlogindemo.R
import com.example.mvvmlogindemo.adapter.QuoteDataAdapter
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class HomeActivityTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testRecyclerView(){
        val viewInteraction = Espresso.onView((withId(R.id.rvNotes)))
        //viewInteraction.perform(RecyclerViewActions.actionOnItemAtPosition<QuoteDataAdapter.QuoteViewHolder>(2,click()))
    }
    @After
    fun tearDown() {
    }
}