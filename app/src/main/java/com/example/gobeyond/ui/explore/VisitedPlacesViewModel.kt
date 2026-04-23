package com.example.gobeyond.ui.explore

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VisitedPlacesViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    var visitedPlaces = mutableStateOf<List<String>>(emptyList())
        private set

    fun visitedPlace(destinationId: String){
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .collection("visited_places")
            .document(destinationId)
            .set(mapOf("id" to destinationId))
    }

    fun observeVisitedPlaces(){
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .collection("visited_places")
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null){
                    visitedPlaces.value = snapshot.documents.map {it.id}
                }
            }
    }

    fun removePlace(destinationId: String){
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .collection("visited_places")
            .document(destinationId)
            .delete()
    }

    fun togglePlace(destinationId: String) {
        if (visitedPlaces.value.contains(destinationId)) {
            removePlace(destinationId)
            visitedPlaces.value = visitedPlaces.value - destinationId
        } else {
            visitedPlace(destinationId)
            visitedPlaces.value = visitedPlaces.value + destinationId
        }
    }

    fun isSaved(destinationId: String): Boolean {
        return visitedPlaces.value.contains(destinationId)
    }
}