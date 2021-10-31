package uz.example.uzblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.post_item_layout.view.*
import uz.example.uzblogs.R
import uz.example.uzblogs.modem.PostModel

class PostAdapter(val items: List<PostModel>):RecyclerView.Adapter<PostAdapter.ItemHoder>(){

    inner class ItemHoder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHoder {
        return ItemHoder(LayoutInflater.from(parent.context).inflate(R.layout.post_item_layout, parent, false))

    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHoder, position: Int) {
        val item = items[position]
        holder.itemView.tvTitle.setText(item.text)
        holder.itemView.tvDate.setText(item.publishDate)
        Glide.with(holder.itemView.context).load(item.image).into(holder.itemView.imagePost)
    }


}
