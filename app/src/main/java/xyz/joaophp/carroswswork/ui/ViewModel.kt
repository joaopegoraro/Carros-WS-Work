package xyz.joaophp.carroswswork.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import xyz.joaophp.carroswswork.data.Perfil
import xyz.joaophp.carroswswork.service.repositories.CarroRepository
import xyz.joaophp.carroswswork.service.repositories.PerfilRepository
import xyz.joaophp.carroswswork.utils.ApiResult

class ViewModel(
    private val carroRepository: CarroRepository,
    private val perfilRepository: PerfilRepository
) : ViewModel() {

    val state = CarrosState(
        carros = carroRepository.listarCarros(),
        perfil = perfilRepository.buscar()
    )

    fun atualizarLista(
        setLoading: (Boolean) -> Unit = {},
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

    fun atualizarPerfil(perfil: Perfil) {
        viewModelScope.launch {
            perfilRepository.atualizar(perfil)
        }
    }
}
