package com.example.gobeyond.ui.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

sealed class AuthState {
    object Loading : AuthState()
    object Unauthenticated : AuthState()
    data class Authenticated(val userId: String) : AuthState()
}

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var authState = mutableStateOf<AuthState>(AuthState.Loading)
        private set

    init {
        checkAuth()
    }

    private fun checkAuth() {
        val user = auth.currentUser
        authState.value = if (user != null) {
            AuthState.Authenticated(user.uid)
        } else {
            AuthState.Unauthenticated
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                authState.value = AuthState.Authenticated(it.user!!.uid)
            }
            .addOnFailureListener {
                authState.value = AuthState.Unauthenticated
            }
    }

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                authState.value = AuthState.Authenticated(it.user!!.uid)
            }
            .addOnFailureListener {
                authState.value = AuthState.Unauthenticated
            }
    }

    fun logout() {
        auth.signOut()
        authState.value = AuthState.Unauthenticated
    }
}