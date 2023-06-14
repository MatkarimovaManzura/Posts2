package faketwitter.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostService {
    @GET("posts")
    fun getPost() : Call<GetPost>

    @POST("posts")
    fun setPost(@Body post: SetPost): Call<PostResponse>
}