package xyz.joaophp.carroswswork.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.data.Lead
import xyz.joaophp.carroswswork.data.Perfil
import xyz.joaophp.carroswswork.service.repositories.CarroRepository
import xyz.joaophp.carroswswork.service.repositories.LeadRepository
import xyz.joaophp.carroswswork.service.repositories.PerfilRepository
import xyz.joaophp.carroswswork.utils.ApiResult

class CarroViewModel(
    private val carroRepository: CarroRepository,
    private val perfilRepository: PerfilRepository,
    private val leadRepository: LeadRepository
) : ViewModel() {

    val state = CarrosState(
        carros = carroRepository.listarCarros(),
        perfil = perfilRepository.buscar(),
        leads = leadRepository.listarLeads()
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

    fun favoritarCarro(carro: Carro) {
        viewModelScope.launch {
            val lead = Lead(
                emailUsuario = perfilRepository.buscar().firstOrNull()?.email ?: "",
                carroId = carro.id,
                timestamp = System.currentTimeMillis()
            )
            leadRepository.adicionar(lead)
            leadRepository.enviarLead(lead)
        }
    }

    fun removerFavoritoCarro(carro: Carro) {
        viewModelScope.launch {
            leadRepository.removerWithCarroId(carro.id)
        }
    }
}
