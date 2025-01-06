package com.example.azeezahbrand.presentation.authentication

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.azeezahbrand.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(private val authrepository: AuthenticationRepository) : ViewModel() {

    private val _authenticationState = MutableStateFlow<AuthenticationState>(AuthenticationState.inActive)
    val authState: StateFlow<AuthenticationState> get() = _authenticationState

     fun signUp(email: String, password: String, fullName: String, onSuccess: () -> Unit,context: Context) {
        viewModelScope.launch {
            _authenticationState.value = AuthenticationState.loading
            val result = authrepository.signUpWithEmail(email, password)
            if (result.isSuccess) {
                val user = result.getOrNull()
                user?.let {
                    val firestoreResult = authrepository.addUserToFirestore(it.uid, fullName,)
                    if (firestoreResult.isSuccess) {
                        _authenticationState.value = AuthenticationState.Success(user)
                        onSuccess()
                    } else {
                        _authenticationState.value = AuthenticationState.Error(firestoreResult.exceptionOrNull()?.message ?: "Error storing user data")
                        Toast.makeText(context, result.exceptionOrNull()?.message ?: "Error retrieving user data", Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                _authenticationState.value = AuthenticationState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
                Toast.makeText(context, result.exceptionOrNull()?.message ?: "Error retrieving user data", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun signIn(email: String, password: String, onNavigate: () -> Unit, context: Context) {
        _authenticationState.value = AuthenticationState.loading
        viewModelScope.launch {
            val authResult = authrepository.signInWithEmail(email, password)
            if (authResult.isSuccess) {
                val user = authResult.getOrNull()
                user?.let {
                    val userId = it.uid
                    val userDocumentResult = authrepository.getUserDocument(userId)
                    if (userDocumentResult.isSuccess) {
                        Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
                        onNavigate()
                    } else {
                        Toast.makeText(context, userDocumentResult.exceptionOrNull()?.message ?: "Error retrieving user data", Toast.LENGTH_SHORT).show()

                    }
                    _authenticationState.value = AuthenticationState.Success(authResult.getOrNull())
                    _authenticationState.value = AuthenticationState.inActive
                }
            } else {
                _authenticationState.value = AuthenticationState.Error(authResult.exceptionOrNull()?.message ?: "Login failed")
                Toast.makeText(context, authResult.exceptionOrNull()?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

sealed class AuthenticationState {
    object loading : AuthenticationState()
    object inActive : AuthenticationState()
    data class Success(val response: Any?) : AuthenticationState()
    data class Error(val message: Any) : AuthenticationState()
}
