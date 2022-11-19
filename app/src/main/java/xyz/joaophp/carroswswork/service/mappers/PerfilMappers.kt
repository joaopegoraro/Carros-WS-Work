package xyz.joaophp.carroswswork.service.mappers

import xyz.joaophp.carroswswork.data.Perfil
import xyz.joaophp.carroswswork.service.local.data.DBPerfil

object PerfilMapper {
    fun Perfil.toEntity(): DBPerfil {
        return DBPerfil(
            id = 1,
            email = email
        )
    }
}

object DBPerfilMapper {
    fun DBPerfil.toPerfil(): Perfil {
        return Perfil(email = email ?: "")
    }
}
