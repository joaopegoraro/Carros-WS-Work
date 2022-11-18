package xyz.joaophp.carroswswork.ui.carros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.joaophp.carroswswork.service.repositories.CarroRepository
import xyz.joaophp.carroswswork.utils.ApiResult

class CarrosViewModel(
    private val carroRepository: CarroRepository
) : ViewModel() {

    val state = CarrosState(
        carros = carroRepository.listarCarros()
    )

    fun atualizarLista(
        onSuccess: () -> Unit = {},
        onFailure: (ApiResult.Error) -> Unit = {}
    ) {
        state.isLoading = true
        viewModelScope.launch {
            carroRepository.buscarCarros(
                onSuccess = {
                    state.isLoading = false
                    onSuccess()
                },
                onFailure = { err ->
                    state.isLoading = false
                    onFailure(err)
                }
            )
        }
    }
}