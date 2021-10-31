package uz.example.uzblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_item_layout.view.*
import uz.example.uzblogs.R
import uz.example.uzblogs.modem.UserModel

interface UserAdapterListener{
    fun onClick(item: UserModel)
}
class UsersAdapter (val items:List<UserModel>, val listener: UserAdapterListener): RecyclerView.Adapter<UsersAdapter.ItemHolder>(){
    inner class ItemHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.tvName.text = item.firstName
        Glide.with(holder.itemView.context).load(item.picture).into(holder.itemView.imageUser)
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }

    }
}