package model

import android.net.Uri

data class Music(
    val title: String,
    val singerName: String,
    val albumName: String,
    val filePath: String,
    val albomUri: Uri,
    var isFavorite: Boolean,
    var count: Int = 0
    )
