package id.wulansari.dewi.applicationfinal.mainActivity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.wulansari.dewi.applicationfinal.databinding.ItemRowUsersBinding
import id.wulansari.dewi.applicationfinal.model.User


class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private val list = ArrayList<User>()
    private var onItemClickCallback: OnItemClickCallback? = null

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =  ItemRowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ListViewHolder(private val binding: ItemRowUsersBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(user: User){

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {

                tvItemUsername.text = user.login
                tvItemUrl.text = user.url
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(imgItemPhoto)

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<User>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(user: User)
    }
}