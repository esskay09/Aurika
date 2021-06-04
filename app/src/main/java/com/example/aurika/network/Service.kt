package com.example.aurika.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BooksApiService {

    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): Items
}

object BooksApi {
    val retrofitService : BooksApiService by lazy { retrofit.create(BooksApiService::class.java) }
}
