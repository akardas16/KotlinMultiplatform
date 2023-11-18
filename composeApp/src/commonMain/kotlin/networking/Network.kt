package networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import models.DataResponseModel

class Network {


    private val client = HttpClient {
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(HttpTimeout){//10 seconds
            requestTimeoutMillis = 10_000
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 10_000
        }
    }

    suspend fun fetchData(result: (response: DataResponseModel?, error: String?) -> Unit){

        val httpResponse = client.get("https://fakestoreapi.com/products")

        try {
            result(httpResponse.body<DataResponseModel>(),null)
        }catch (e:Exception){
            result(null,"Error Http Status Code --> ${httpResponse.status.value}\nException--> ${e.message} \nError body Text--> ${httpResponse.bodyAsText()}")
        }

    }

    companion object{
        val shared = Network()
    }
}