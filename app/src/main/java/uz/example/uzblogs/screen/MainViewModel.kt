package uz.example.uzblogs.screen

import android.util.MutableDouble
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.example.uzblogs.adapter.PostAdapter
import uz.example.uzblogs.adapter.UsersAdapter
import uz.example.uzblogs.api.ApiService
import uz.example.uzblogs.api.BaseResponse
import uz.example.uzblogs.modem.PostModel
import uz.example.uzblogs.modem.UserModel
import uz.example.uzblogs.repasitory.HomeRepasitory

class MainViewModel: ViewModel() {
    val repositry = HomeRepasitory()
    val error = MutableLiveData<String>()
    val userData = MutableLiveData<List<UserModel>>()
    val postData = MutableLiveData<List<PostModel>>()
    //val refresh = MutableLiveData<Boolean>()
    fun getUsers(){
        repositry.getUsersData(userData, error)
    }

    fun getPots(){
        repositry.getPostData(postData, error)
    }
}