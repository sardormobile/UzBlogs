package uz.example.uzblogs.screen.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_posts.*
import retrofit2.Call
import retrofit2.Response
import uz.example.uzblogs.R
import uz.example.uzblogs.adapter.PostAdapter
import uz.example.uzblogs.api.ApiService
import uz.example.uzblogs.api.BaseResponse
import uz.example.uzblogs.modem.PostModel
import uz.example.uzblogs.modem.UserModel
import javax.security.auth.callback.Callback

class PostsActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {
    lateinit var  user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        swipe.setOnRefreshListener(this)
        swipe.isRefreshing= true
        user = intent.getSerializableExtra("extra_data") as UserModel

        tvTitle.setText(user.firstName.plus(" ${user.lastName}"))
        loadPost()
    }

    override fun onRefresh() {
        loadPost()
    }
    fun loadPost(){
        swipe.isRefreshing = true
        ApiService.apiClient().getPostByUser(user.id).enqueue(object :
            retrofit2.Callback<BaseResponse<List<PostModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<PostModel>>>,
                response: Response<BaseResponse<List<PostModel>>>
            ) {
                if (response.isSuccessful) {
                    rectanglesByPosts.layoutManager = LinearLayoutManager(this@PostsActivity)
                    rectanglesByPosts.adapter = PostAdapter(response.body()?. data ?: emptyList())
                    swipe.isRefreshing = false
                } else {
                    Toast.makeText(this@PostsActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                    swipe.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<PostModel>>>, t: Throwable) {
                Toast.makeText(this@PostsActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}