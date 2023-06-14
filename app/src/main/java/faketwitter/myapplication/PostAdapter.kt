package faketwitter.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class RecuclerHolder(view: View) : ViewHolder(view)

class PostAdapter(val items: GetPost) : Adapter<RecuclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecuclerHolder {
        val viev: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_post, parent, false)
     return RecuclerHolder(viev)
    }

    override fun onBindViewHolder(holder: RecuclerHolder, position: Int) {
        val item: Post = items.posts[position]
        val avatar: RoundedImageView = holder.itemView.findViewById(R.id.avatar)
        val name: TextView = holder.itemView.findViewById(R.id.name)
        val text: TextView = holder.itemView.findViewById(R.id.text)
        val like: TextView = holder.itemView.findViewById(R.id.likeNum)
        val comment: TextView = holder.itemView.findViewById(R.id.comentNum)
        val share: TextView = holder.itemView.findViewById(R.id.shareNum)

        Picasso.get().load(item.author.avatar).into(avatar)
        name.text = item.author.name
        text.text = item.content
        like.text = item.likes.toString()
        comment.text = item.comments.toString()
        share.text = item.shares.toString()


    }

    override fun getItemCount(): Int {
        return items.posts.size
    }


}