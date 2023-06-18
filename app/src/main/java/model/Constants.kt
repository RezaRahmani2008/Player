package com.example.musicplayer

import com.google.android.exoplayer2.Player
import model.Music


var allMusiceList = mutableListOf<Music>()
val favoriteSongs = mutableListOf<Music>()
var mainList = mutableListOf<Music>()
var currentMusic: Music?= null
val playerList = mutableListOf<Player>()

