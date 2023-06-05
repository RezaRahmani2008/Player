package com.example.musicplayer

import com.google.android.exoplayer2.Player
import model.Music


val allMusiceList = mutableListOf<Music>()
val favoriteSongs = mutableListOf<Music>()
val mainList = mutableListOf<Music>()
var currentMusic: Music?= null
val playerList = mutableListOf<Player>()
