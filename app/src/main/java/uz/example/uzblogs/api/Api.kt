package uz.example.uzblogs.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import uz.example.uzblogs.modem.PostModel
import uz.example.uzblogs.modem.UserModel
import uz.example.uzblogs.utils.Constants

interface Api {
    @Headers(Constants.HEADRS_KEY)
    @GET("user")
    fun getUsers(): Call<BaseResponse<List<UserModel>>>
    @Headers(Constants.HEADRS_KEY)
    @GET("post")
    fun getPost(): Call<BaseResponse<List<PostModel>>>
    @Headers(Constants.HEADRS_KEY)
    @GET("user/{user_id}/post")
    fun getPostByUser(@Path("user_id") id: String): Call<BaseResponse<List<PostModel>>>


}