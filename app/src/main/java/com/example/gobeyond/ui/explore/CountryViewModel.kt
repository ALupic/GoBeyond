package com.example.gobeyond.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gobeyond.ui.data.CountryRepository
import com.example.gobeyond.ui.model.Country
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountryViewModel(
    private val repository: CountryRepository
) : ViewModel() {

    val countries = repository.getAllCountries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            repository.seedCountriesIfEmpty()
        }
    }
}

