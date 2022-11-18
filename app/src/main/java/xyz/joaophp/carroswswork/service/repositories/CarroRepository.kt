package xyz.joaophp.carroswswork.service.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.service.local.dao.CarroDao
import xyz.joaophp.carroswswork.service.mappers.CarroMapper.toEntities
import xyz.joaophp.carroswswork.service.mappers.DBCarroMapper.toCarros
import xyz.joaophp.carroswswork.service.mappers.NetworkCarroMapper.toCarros
import xyz.joaophp.carroswswork.service.remote.ApiService
import xyz.joaophp.carroswswork.utils.ApiResult

class CarroRepository(
    private val localDataSource: CarroDao,
    private val remoteDataSource: ApiService
) {

    suspend fun buscarCarros(
        onSuccess: () -> Unit,
        onFailure: (ApiResult.Error) -> Unit,
    ) {
        when (val result = ApiResult.getResultFor { remoteDataSource.getCarros() }) {
            is ApiResult.Success -> {
                val networkCarros = result.data
                val carros = networkCarros.toCarros()
                val carrosEntities = carros.toEntities()
                localDataSource.insertAll(carrosEntities)
                onSuccess()
            }
            is ApiResult.Error -> {
                onFailure(result)
            }
        }
    }

    fun listarCarros(): Flow<List<Carro>> {
        return localDataSource.getAll().mapNotNull { entities ->
            entities.toCarros()
        }
    }
}