package uz.example.uzblogs.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import uz.example.uzblogs.R
import uz.example.uzblogs.adapter.PostAdapter
import uz.example.uzblogs.adapter.UserAdapterListener
import uz.example.uzblogs.adapter.UsersAdapter
import uz.example.uzblogs.modem.UserModel
import uz.example.uzblogs.screen.posts.PostsActivity

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe.setOnRefreshListener(this)
        loadUser()
        loadPosts()
        loadData()
        swipe.isRefreshing = true

        viewModel.error.observe(this, Observer {
            swipe.isRefreshing = false
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
    fun loadUser(){
        rectanglesUsers.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        viewModel.userData.observe(this, Observer {
            swipe.isRefreshing = false

            rectanglesUsers.adapter = UsersAdapter(it, object : UserAdapterListener{
                override fun onClick(item: UserModel) {
                    val intent = Intent(this@MainActivity, PostsActivity::class.java)
                    intent.putExtra("extra_data", item)
                    startActivity(intent)
                }
            })

        })

    }
    fun loadPosts(){
        rectanglesPosts.layoutManager = LinearLayoutManager(this@MainActivity)
        viewModel.postData.observe(this, Observer {
            swipe.isRefreshing = false
            rectanglesPosts.adapter = PostAdapter(it)
        })
    }
    fun loadData(){
        viewModel.getUsers()
        viewModel.getPots()
    }

    override fun onRefresh() {
        loadUser()
        loadPosts()
        loadData()
    }


}