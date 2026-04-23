package com.example.gobeyond.ui.explore

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class SavedPlacesViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    var savedPlaces = mutableStateOf<List<String>>(emptyList())
        private set

    fun savePlace(destinationId: String) {
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .collection("saved_places")
            .document(destinationId)
            .set(mapOf("id" to destinationId))
    }

    fun observeSavedPlaces() {
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .collection("saved_places")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    savedPlaces.value = snapshot.documents.map { it.id }
                }
            }
    }

    fun removePlace(destinationId: String) {
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .collection("saved_places")
            .document(destinationId)
            .delete()
    }

    fun togglePlace(destinationId: String) {
        if (savedPlaces.value.contains(destinationId)) {
            removePlace(destinationId)
            savedPlaces.value = savedPlaces.value - destinationId
        } else {
            savePlace(destinationId)
            savedPlaces.value = savedPlaces.value + destinationId
        }
    }

    fun isSaved(destinationId: String): Boolean {
        return savedPlaces.value.contains(destinationId)
    }
}