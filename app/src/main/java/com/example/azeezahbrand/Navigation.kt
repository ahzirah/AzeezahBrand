import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "SplashActivity") {
//        composable("home") { LoginActivity(navController) }
//        composable("profile") { ProfileScreen(navController) }
    }
}