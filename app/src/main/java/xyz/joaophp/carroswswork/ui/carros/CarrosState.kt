package xyz.joaophp.carroswswork.ui.carros

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import xyz.joaophp.carroswswork.data.Carro

data class CarrosState(
    val carros: Flow<List<Carro>>
) {
    var isLoading by mutableStateOf(false)
}