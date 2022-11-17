package xyz.joaophp.carroswswork.service.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import xyz.joaophp.carroswswork.data.Carro
import xyz.joaophp.carroswswork.data.Lead
import xyz.joaophp.carroswswork.service.remote.data.NetworkCarro
import xyz.joaophp.carroswswork.service.remote.data.NetworkLead
import xyz.joaophp.carroswswork.service.remote.data.ReturnPost

interface ApiService {

    /**
     *  GET
     */

    @GET("cars.json")
    suspend fun getCarros(): Response<List<NetworkCarro>>


    /**
     *  POST
     */

    @POST("cars/leads")
    suspend fun postLeads(@Body leads: List<NetworkLead>): Response<ReturnPost>
}
