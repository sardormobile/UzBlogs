package uz.example.uzblogs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.example.uzblogs.utils.Constants

object ApiService{
    var retrofit: Retrofit? = null

    fun apiClient(): Api{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()
        }

        return retrofit!!.create(Api::class.java)
    }
}