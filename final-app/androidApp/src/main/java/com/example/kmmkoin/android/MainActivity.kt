package com.example.kmmkoin.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.kmmkoin.core.Lookup
import com.example.kmmkoin.core.Greeting
import com.example.kmmkoin.core.User
import com.example.kmmkoin.core.UserModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

data class UserState(
    val name: String? = null,
    val loggedIn: Boolean = false
)

class UserViewModel : ViewModel() {
    private val userModule: UserModule by inject(UserModule::class.java)
    private val _userState = MutableStateFlow(UserState(
        userModule.userName, userModule.isLoggedIn
    ))
    val userState: StateFlow<UserState> = _userState.asStateFlow()
    val user: User?
        get() = userModule.user

    fun logout() {
        userModule.logout()
        _userState.value = UserState(userModule.userName, userModule.isLoggedIn)
    }
    fun login(name: String) {
        userModule.login(name)
        _userState.value = UserState(userModule.userName, userModule.isLoggedIn)
    }
}

class MainActivity : ComponentActivity() {

    private val environment: Lookup by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val greet = Greeting().greet() +" : "+ environment.baseUrl
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GreetingView(greet)
                        UserView()
                    }
                }
            }
        }
    }
}

@Composable
fun UserView(
    viewModel: UserViewModel = viewModel(),
) {
    val userState by viewModel.userState.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(userState.loggedIn) {
            Text(text = viewModel.user?.toString() ?: "No User")
            Text(text = userState.name ?: "No User")
            Button(onClick = { viewModel.logout() }) {
                Text(text = "Logout")
            }
        }
        else {
            Text(text = viewModel.user?.toString() ?: "No User")
            Text(text = userState.name ?: "No User")
            Button(onClick = { viewModel.login("Bob") }) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
