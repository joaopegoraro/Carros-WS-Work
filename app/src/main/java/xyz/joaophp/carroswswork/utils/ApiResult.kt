package xyz.joaophp.carroswswork.utils

import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

sealed interface ApiResult<out T> {

    data class Success<out T>(val data: T) : ApiResult<T>

    sealed interface Error : ApiResult<Nothing> {
        object FalhaConexao : Error
        object ErroServidor : Error
        object ErroDesconhecido : Error
    }

    companion object {
        suspend fun <T> getResultFor(apiMethod: suspend () -> Response<T>): ApiResult<T?> {
            return try {
                val response = apiMethod()
                when (response.code()) {
                    in 200..300 -> Success(data = response.body())
                    in 500..600 -> Error.ErroServidor
                    else -> Error.ErroDesconhecido
                }
            } catch (e: Exception) {
                if (e is ConnectException || e is UnknownHostException) {
                    Error.FalhaConexao
                } else {
                    Error.ErroDesconhecido
                }
            }
        }
    }
}
