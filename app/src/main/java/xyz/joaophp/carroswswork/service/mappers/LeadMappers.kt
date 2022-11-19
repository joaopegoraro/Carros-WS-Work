package xyz.joaophp.carroswswork.service.mappers

import xyz.joaophp.carroswswork.data.Lead
import xyz.joaophp.carroswswork.service.local.data.DBLead
import xyz.joaophp.carroswswork.service.remote.data.NetworkLead

object NetworkLeadMapper {
    fun NetworkLead.toLead(): Lead {
        return Lead(
            emailUsuario = emailUsuario,
            carroId = carroId,
            timestamp = timestamp
        )
    }

    fun List<NetworkLead>?.toLeads(): List<Lead> {
        return this?.map { it.toLead() } ?: emptyList()
    }

    fun NetworkLead.toEntity(): DBLead {
        return DBLead(
            emailUsuario = emailUsuario,
            carroId = carroId,
            timestamp = timestamp
        )
    }

    fun List<NetworkLead>?.toEntities(): List<DBLead> {
        return this?.map { it.toEntity() } ?: emptyList()
    }

}


object LeadMapper {
    fun Lead.toEntity(): DBLead {
        return DBLead(
            emailUsuario = emailUsuario,
            carroId = carroId,
            timestamp = timestamp
        )
    }

    fun List<Lead>?.toEntities(): List<DBLead> {
        return this?.map { it.toEntity() } ?: emptyList()
    }

    fun Lead.toNetworkLead(): NetworkLead {
        return NetworkLead(
            emailUsuario = emailUsuario,
            carroId = carroId,
            timestamp = timestamp
        )
    }

    fun List<Lead>?.toNetworkLeads(): List<NetworkLead> {
        return this?.map { it.toNetworkLead() } ?: emptyList()
    }
}

object DBLeadMapper {
    fun DBLead.toLead(): Lead {
        return Lead(
            emailUsuario = emailUsuario ?: "",
            carroId = carroId,
            timestamp = timestamp ?: 0
        )
    }

    fun List<DBLead>?.toLeads(): List<Lead> {
        return this?.map { it.toLead() } ?: emptyList()
    }


    fun DBLead.toNetworkLead(): NetworkLead {
        return NetworkLead(
            emailUsuario = emailUsuario ?: "",
            carroId = carroId,
            timestamp = timestamp ?: 0
        )
    }

    fun List<DBLead>?.toNetworkLeads(): List<NetworkLead> {
        return this?.map { it.toNetworkLead() } ?: emptyList()
    }
}

