package com.example.laboratorio11

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.laboratorio11.ui.login.LoginFragment
import junit.framework.TestCase.assertEquals
import org.junit.Test

class LoginNavigationTest {
    @Test
    fun testNavigationToWelcome() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val loginScenario = launchFragmentInContainer<LoginFragment>(themeResId = R.style.Theme_Laboratorio11)
        loginScenario.onFragment{frag ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(frag.requireView(), navController)
        }

        onView(withId(R.id.email_input)).perform(typeText("test@test.com"))
        onView(withId(R.id.password_input)).perform(typeText("12345678"))
        onView(withId(R.id.loginBtn)).perform(click())
        Thread.sleep(2100)
        navController.currentDestination?.let { assertEquals(it.id, R.id.welcomeFragment) }
    }
}