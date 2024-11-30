package com.example.azeezahbrand.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthenticationRepository(){

    private val firebaseAuth = FirebaseAuth.getInstance();
    private val firestore:FirebaseFirestore= FirebaseFirestore.getInstance();


    suspend fun signUpWithEmail(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signInWithEmail(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun addUserToFirestore(userId: String, fullName: String): Result<Unit> {
        return try {
            val userMap = mapOf("fullName" to fullName)
            firestore.collection("userProfile").document(userId).set(userMap).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserDocument(userId: String): Result<Map<String, Any>?> {
        return try {
            val documentSnapshot = firestore.collection("userProfile").document(userId).get().await()
            if (documentSnapshot.exists()) {
                Result.success(documentSnapshot.data)
            } else {
                Result.failure(Exception("User document not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}