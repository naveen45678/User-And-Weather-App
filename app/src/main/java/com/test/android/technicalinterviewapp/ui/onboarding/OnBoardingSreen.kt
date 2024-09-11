import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.test.android.technicalinterviewapp.navigation.Routes
import com.test.android.technicalinterviewapp.ui.ButtonComponent
import com.test.android.technicalinterviewapp.ui.TextComponent

@Composable
fun OnBoardingScreen(modifier: Modifier,navController: NavController) {

    Column(
        modifier = modifier
            .fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TextComponent(
            value = "Welcome",
            textSize = 30.sp,
            textColor = Color.Blue,
            fontWeight = FontWeight.Bold
        )

        // Add a Spacer to push the button towards the center
        Spacer(modifier = Modifier.weight(1f))

        // Center the button using a Box with Alignment.Center
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    println("Button Clicked") // Check if this prints
                    navController.navigate(Routes.LOGIN_SCREEN)
                },
                value = "Login",
                buttonColor = Color.Blue,
                textColor = Color.White,
                textSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen(modifier = Modifier, navController = rememberNavController())
}
