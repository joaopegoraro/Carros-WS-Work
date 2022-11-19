package xyz.joaophp.carroswswork.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.Flow
import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.data.Lead
import xyz.joaophp.carroswswork.data.Perfil

data class CarrosState(
    val carros: Flow<List<Carro>>,
    val perfil: Flow<Perfil?>,
    val leads: Flow<List<Lead>>
) {
    var isLoading by mutableStateOf(false)
    var isLoadingSwipeToRefresh by mutableStateOf(false)
}