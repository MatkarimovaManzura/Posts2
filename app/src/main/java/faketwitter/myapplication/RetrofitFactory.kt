package faketwitter.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val retroFit: Retrofit = Retrofit.Builder()
        .baseUrl("https://plain-cod-wetsuit.cyclic.app")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postService: PostService = retroFit.create(PostService::class.java)
}