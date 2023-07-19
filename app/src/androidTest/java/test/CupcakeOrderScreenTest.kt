package test

import androidx.activity.ComponentActivity

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.R

import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }
        // Then all the options are displayed on the screen.
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next)
            .assertIsNotEnabled()

        //select an option and assert that next button is activated
        composeTestRule.onNodeWithText(flavors[0]).performClick()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

    @Test
    fun startScreen_verifyContent() {
        val quantityOptions = listOf(
            Pair(R.string.one_cupcake, 1),
            Pair(R.string.six_cupcakes, 6),
            Pair(R.string.twelve_cupcakes, 12)
        )
        composeTestRule.setContent {
            StartOrderScreen(quantityOptions = quantityOptions, onNextButtonClicked = {})
        }

        //check if the image is there
        composeTestRule.onNodeWithStringId(R.drawable.cupcake).assertIsDisplayed()

        //check that the buttons exist
        quantityOptions.forEach { item ->
            composeTestRule.onNodeWithStringId(item.first).assertIsDisplayed()
        }

    }

//    @Test
//    fun summaryScreen_verifyContent() {
//        composeTestRule.setContent {
//            OrderSummaryScreen(
//                orderUiState = ,
//                onCancelButtonClicked = { /*TODO*/ },
//                onSendButtonClicked =
//            )
//        }
//    }

}