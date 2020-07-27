package com.kxs109.lib_ksyplayer

class KsyPlayerFactory : PlayerFactory {
    override fun getPlayer(): IPlayer {
        return FloatingPlayer.getInstance()
    }
}