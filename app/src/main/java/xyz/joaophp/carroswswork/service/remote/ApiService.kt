package xyz.joaophp.carroswswork.service.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import xyz.joaophp.carroswswork.service.data.Carro
import xyz.joaophp.carroswswork.service.data.Lead
import xyz.joaophp.carroswswork.service.data.ReturnPost

interface ApiService {

    /**
     *  GET
     */

    @GET("cars.json")
    suspend fun getCarros(): Response<List<Carro>>


    /**
     *  POST
     */

    @POST("cars/leads")
    suspend fun postLeads(@Body leads: List<Lead>): Response<ReturnPost>
}
