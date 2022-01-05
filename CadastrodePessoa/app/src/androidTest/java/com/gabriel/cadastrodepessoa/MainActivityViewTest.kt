package com.gabriel.cadastrodepessoa

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.gabriel.cadastrodepessoa.activities.FormularioPessoaActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityViewTest {

    var nome: String = ""



    @get:Rule
    var activityRule: ActivityTestRule<FormularioPessoaActivity>
            = ActivityTestRule(FormularioPessoaActivity::class.java)


    @Before
    fun setNome(){
        nome = "Bia"
    }

//    @Test
//    fun changeText_sameActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.activity_formulario_pessoa_nome))
//                .perform(typeText(nome), closeSoftKeyboard())
//        onView(withId(R.id.changeTextBt)).perform(click())
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged))
//                .check(matches(withText(stringToBetyped)))
//    }
    @Test
    fun verificaBotao(){
        onView(withId(R.id.botao_cadastrar))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed()))
    }
}