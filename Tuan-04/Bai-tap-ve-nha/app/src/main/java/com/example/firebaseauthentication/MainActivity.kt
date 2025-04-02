package com.example.firebaseauthentication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthentication.ui.screen.AuthScreen
import com.example.firebaseauthentication.ui.screen.ProfileScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val googleSignInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data // Get the result data from the intent

            if (result.resultCode == RESULT_OK && data != null) {
                // Try to get the signed-in account from the intent data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)

                try {
                    // Get the account from the task result
                    val account = task.getResult(ApiException::class.java)
                    // If account is successfully retrieved, proceed with Firebase authentication
                    account?.let {
                        firebaseAuthWithGoogle(it)
                    }
                } catch (e: ApiException) {
                    // Handle the exception (sign-in failed)
                    Log.e("Google Sign-In", "signInResult:failed code=" + e.statusCode)
                    Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Handle the case where the result is not OK or data is null
                Log.e("Google Sign-In", "Result failed or data is null")
                Toast.makeText(this, "Google Sign-In failed or canceled", Toast.LENGTH_SHORT).show()
            }
        }


    // Declare a state variable to hold the Firebase user
    private var user by mutableStateOf<FirebaseUser?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Initialize Firebase
        setContent {
            val navController = rememberNavController()
            var user by remember { mutableStateOf(firebaseAuth.currentUser) }

            // Use Navigation to handle screen transitions
            NavHost(navController, startDestination = "authScreen") {
                composable("authScreen") {
                    AuthScreen({ signInWithGoogle() }, navController)
                }
                composable("profileScreen") {
                    ProfileScreen(user)
                }
            }

            // Observe user authentication state and navigate to ProfileScreen after login
            LaunchedEffect(user) {
                if (user != null) {
                    navController.navigate("profileScreen")
                }
            }
        }
    }

    private fun signInWithGoogle() {
        // Get Web Client ID from strings.xml
        val webClientId = getString(R.string.default_web_client_id)

        // Build GoogleSignInOptions with Web Client ID
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)  // Use the correct Web Client ID
            .requestEmail()  // Request the user's email
            .build()

        // Log the Web Client ID for debugging purposes
        Log.d("Google Sign-In", "Web Client ID: $webClientId")

        // Get the GoogleSignInClient and create the sign-in intent
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent

        // Launch the sign-in intent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Handle successful authentication
                    val user = firebaseAuth.currentUser
                    Log.d("FirebaseAuth", "Signed in as: ${user?.displayName}")
                    Toast.makeText(this, "Authentication successful", Toast.LENGTH_SHORT).show()
                    // Update user state
                    user?.let {
                        // Assign the authenticated user to the state
                        this@MainActivity.user = it
                    }
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    // Handle sign-in failure
                    Log.e("FirebaseAuth", "signInWithCredential:failure", task.exception)
                }
            }
    }
}
