package com.kxs109.lib_ksyplayer

interface IPlayerListener {
    fun onPrepared()
    fun onError()
    fun onInfo()
    fun onPlayOver()
}