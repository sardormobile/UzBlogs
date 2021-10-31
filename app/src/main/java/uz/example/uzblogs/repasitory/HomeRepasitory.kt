package uz.example.uzblogs.repasitory

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.example.uzblogs.api.ApiService
import uz.example.uzblogs.api.BaseResponse
import uz.example.uzblogs.modem.PostModel
import uz.example.uzblogs.modem.UserModel

class HomeRepasitory {
    val refresh = MutableLiveData<Boolean>()
    fun getUsersData( userData:MutableLiveData<List<UserModel>>, error: MutableLiveData<String>){
        ApiService.apiClient().getUsers().enqueue(object : Callback<BaseResponse<List<UserModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<UserModel>>>,
                response: Response<BaseResponse<List<UserModel>>>
            ) {
                if (response.isSuccessful) {
                    refresh.value = false
                    userData.value = response.body()?.data ?: emptyList()
                } else {
                    refresh.value = false
                    error.value = response.message()
                }

            }

            override fun onFailure(call: Call<BaseResponse<List<UserModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })

    }

    fun getPostData(postData: MutableLiveData<List<PostModel>>, error: MutableLiveData<String>){
        ApiService.apiClient().getPost().enqueue(object : Callback<BaseResponse<List<PostModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<PostModel>>>,
                response: Response<BaseResponse<List<PostModel>>>
            ) {
                if (response.isSuccessful) {
                    postData.value = response.body()?.data ?: emptyList()
                } else {
                    error.value = response.message()
                }

            }

            override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                error.value = t.localizedMessage
            }
        })
    }
}