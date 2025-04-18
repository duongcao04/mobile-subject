package com.example.firebaseauthentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.viewmodel.AuthViewModel
import com.example.firebaseauthentication.ui.screen.AuthScreen
import com.example.firebaseauthentication.ui.screen.ProfileScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val viewModel: AuthViewModel = viewModel()
            val user by viewModel.user.observeAsState()
            val navController = rememberNavController()

            // ✔️ Sử dụng rememberLauncherForActivityResult thay thế
            val googleSignInLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    viewModel.firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: Exception) {
                    // TODO: show error message
                }
            }

            LaunchedEffect(user) {
                if (user != null) {
                    navController.navigate("profile") {
                        popUpTo("login") { inclusive = true }
                    }
                } else {
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                }
            }

            NavHost(
                navController = navController,
                startDestination = if (user != null) "profile" else "login"
            ) {
                composable("login") {
                    AuthScreen(onLoginClick = {
                        val client = viewModel.getGoogleSignInClient()
                        googleSignInLauncher.launch(client.signInIntent)
                    })
                }
                composable("profile") {
                    user?.let {
                        ProfileScreen(user = it,
                            navController = navController,
                            onSignOut = { viewModel.signOut() })
                    }
                }
            }
        }
    }
}
