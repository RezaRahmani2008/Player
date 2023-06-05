package adabtore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.musicplayer.R
import com.example.musicplayer.currentMusic
import com.example.musicplayer.mediaItemInALLMusiceFragment
import com.example.musicplayer.menuItemInFavoriteSongsFragment

import model.Music

class MusicAdaptor (var musicList: MutableList<Music>, var context: Context): RecyclerView.Adapter<MusicAdaptor.viewHolder>() {
        inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView
            val singerName: TextView
            val image: ImageView

            init {
                view.apply {
                    title = findViewById(R.id.titleTV)
                    singerName = findViewById(R.id.artistTV)
                    image = findViewById(R.id.coverArt)

                }
                view.setOnClickListener {
                    currentMusic = musicList[position]
                    if (menuItemInFavoriteSongsFragment != null) {
                        menuItemInFavoriteSongsFragment!!.collapseActionView()
                        menuItemInFavoriteSongsFragment!!.isVisible = false
                    }

                    if (mediaItemInALLMusiceFragment != null){
                    mediaItemInALLMusiceFragment!!.collapseActionView()
                    mediaItemInALLMusiceFragment!!.isVisible = false
                    }
                    Navigation.findNavController(view)
                        .navigate(R.id.action_viewPagerFragment_to_currentPlayingFragment)

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, p1viewType: Int): viewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
            return viewHolder(view)
        }

    fun filterList(list: MutableList<Music>){
        musicList = list
        notifyDataSetChanged()
    }
        override fun onBindViewHolder(holder: viewHolder, position: Int) {
            holder.apply {
                title.text = musicList[position].title
                singerName.text = musicList[position].singerName
               Glide.with(context).load(musicList[position].albomUri)
                   .error(R.drawable.bb).transform(CenterCrop(), RoundedCorners(30)).into(image)

            }

        }
        override fun getItemCount(): Int {
            return musicList.size
        }
}




