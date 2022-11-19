package xyz.joaophp.carroswswork.service.mappers

import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.service.local.data.DBCarro
import xyz.joaophp.carroswswork.service.remote.data.NetworkCarro

object NetworkCarroMapper {
    fun NetworkCarro.toCarro(): Carro {
        return Carro(
            id = id,
            marcaId = marcaId ?: -1,
            marcaNome = marcaNome ?: "",
            nomeModelo = nomeModelo ?: "",
            ano = ano,
            combustivel = combustivel ?: "",
            numeroPortas = numeroPortas ?: 2,
            valorFipe = if (valorFipe != null && valorFipe < 2000) {
                valorFipe.times(1000)
            } else {
                valorFipe
            },
            cor = cor ?: "",
            timestampCadastro = timestampCadastro ?: 0
        )
    }

    fun List<NetworkCarro>?.toCarros(): List<Carro> {
        return this?.map { it.toCarro() } ?: emptyList()
    }

    fun NetworkCarro.toEntity(): DBCarro {
        return DBCarro(
            id = id,
            marcaId = marcaId ?: -1,
            marcaNome = marcaNome ?: "",
            nomeModelo = nomeModelo ?: "",
            ano = ano,
            combustivel = combustivel ?: "",
            numeroPortas = numeroPortas ?: 2,
            valorFipe = if (valorFipe != null && valorFipe < 2000) {
                valorFipe.times(1000)
            } else {
                valorFipe
            },
            cor = cor ?: "",
            timestampCadastro = timestampCadastro ?: 0
        )
    }

    fun List<NetworkCarro>?.toEntities(): List<DBCarro> {
        return this?.map { it.toEntity() } ?: emptyList()
    }

}


object CarroMapper {
    fun Carro.toEntity(): DBCarro {
        return DBCarro(
            id = id,
            marcaId = marcaId,
            marcaNome = marcaNome,
            nomeModelo = nomeModelo,
            ano = ano,
            combustivel = combustivel,
            numeroPortas = numeroPortas,
            valorFipe = valorFipe,
            cor = cor,
            timestampCadastro = timestampCadastro
        )
    }

    fun List<Carro>?.toEntities(): List<DBCarro> {
        return this?.map { it.toEntity() } ?: emptyList()
    }
}

object DBCarroMapper {
    fun DBCarro.toCarro(): Carro {
        return Carro(
            id = id,
            marcaId = marcaId ?: -1,
            marcaNome = marcaNome ?: "",
            nomeModelo = nomeModelo ?: "",
            ano = ano,
            combustivel = combustivel ?: "",
            numeroPortas = numeroPortas ?: 2,
            valorFipe = valorFipe,
            cor = cor ?: "",
            timestampCadastro = timestampCadastro ?: 0
        )
    }

    fun List<DBCarro>?.toCarros(): List<Carro> {
        return this?.map { it.toCarro() } ?: emptyList()
    }
}

