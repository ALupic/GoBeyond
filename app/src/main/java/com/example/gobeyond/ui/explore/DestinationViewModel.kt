package com.example.gobeyond.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gobeyond.ui.data.DestinationRepository
import com.example.gobeyond.ui.model.Destination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DestinationViewModel(
    private val repository: DestinationRepository,
    countryId: String
) : ViewModel() {

    val destinations = repository
        .getDestinations(countryId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.seedDestinationsIfEmpty(countryId)
        }
    }
}
