package com.example.musicplayer

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bumptech.glide.disklrucache.DiskLruCache.Value
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.flow.first

import model.Music


fun getAllAudioFromDevice(context: Context) {
    val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    val projection = arrayOf(
        MediaStore.Audio.AudioColumns.DATA,
        MediaStore.Audio.AudioColumns.TITLE,
        MediaStore.Audio.AudioColumns.ALBUM,
        MediaStore.Audio.ArtistColumns.ARTIST,
        MediaStore.Audio.Media.ALBUM_ID
    )

    val cursor: Cursor? = context.contentResolver.query(
        uri,
        projection,
        null,
        null,
        null
    )
    if (cursor != null) {
        while (cursor.moveToNext()) {
            val path: String = cursor.getString(0)
            val title: String = cursor.getString(1)
            val album: String = cursor.getString(2)
            val artist: String = cursor.getString(3)


            val pathUri = Uri
                .parse("content://media/external/audio/albumart")
            val albomArtUri = ContentUris.withAppendedId(pathUri, cursor.getLong(4))
            allMusiceList.add(Music(title, artist, album, path, albomArtUri,false))
        }
        cursor.close()
    }
}

fun playMusic(context: Context, filPath: String){
    val player = ExoPlayer.Builder(context).build()

    val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(filPath))
    player.setMediaItem(mediaItem)
    player.prepare()
    player.play()
    player.addListener(object : Player.Listener{
        override fun onPlaybackStateChanged(playbackState: Int) {
            if(playbackState == Player.STATE_ENDED){
                player.release()
            }
        }
    })
    playerList.add(player)

}

suspend fun writeToDataStore(context: Context, key: String, value: String) {
    val preferencesKey = stringPreferencesKey(key)
    context.datastore.edit {
        it[preferencesKey] = value
    }
}

suspend fun deleteFromDataStore(context: Context, key: String) {
    val preferencesKey = stringPreferencesKey(key)
    context.datastore.edit {
        it[preferencesKey] = ""
    }
}

suspend fun readFromDataStore(context: Context, key: String):String? {
        val preferencesKey = stringPreferencesKey(key)
        val preferences = context.datastore.data.first()
        return preferences[preferencesKey]
}
