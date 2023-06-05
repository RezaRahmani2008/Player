package model

import android.net.Uri
import android.widget.ImageView
import com.example.musicplayer.databinding.FragmentFavoriteSongsBinding

data class Music(
    val title: String,
    val singerName: String,
    val albumName: String,
    val filePath: String,
    val albomUri: Uri,
    var isFavorite: Boolean
    )
