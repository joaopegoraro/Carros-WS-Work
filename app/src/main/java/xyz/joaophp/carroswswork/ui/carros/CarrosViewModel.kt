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
        setLoading: (Boolean) -> Unit = { state.isLoading = it },
        onSuccess: () -> Unit = {},
        onFailure: (ApiResult.Error) -> Unit = {}
    ) {
        setLoading(true)
        viewModelScope.launch {
            carroRepository.buscarCarros(
                onSuccess = {
                    setLoading(false)
                    onSuccess()
                },
                onFailure = { err ->
                    setLoading(false)
                    onFailure(err)
                }
            )
        }
    }
}