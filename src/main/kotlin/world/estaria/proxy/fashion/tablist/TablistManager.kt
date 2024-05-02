package world.estaria.proxy.fashion.tablist

import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import world.estaria.server.manager.api.ServerManagerApi
import world.estaria.translation.api.TranslationManager
import world.estaria.translation.api.extension.getTranslationLocale
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * @author Niklas Nieberler
 */

class TablistManager(
    private val server: ProxyServer,
    translationManager: TranslationManager
) {

    private val tablistHelper = TablistHelper(translationManager)

    fun executeTablistScheduler() {
        val scheduler = Executors.newScheduledThreadPool(0)
        scheduler.scheduleAtFixedRate({
            this.server.allPlayers.forEach { sendTablist(it) }
        }, 1, 1, TimeUnit.SECONDS)
    }

    fun sendTablist(player: Player) {
        player.getTranslationLocale().thenAccept { sendTablist(player, it) }
    }

    private fun sendTablist(player: Player, locale: Locale) {
        val server = ServerManagerApi.instance.serverManager
            .getServerByPlayer(player.uniqueId) ?: return
        player.sendPlayerListHeaderAndFooter(
            this.tablistHelper.getHeader(server, locale),
            this.tablistHelper.getFooter(locale)
        )
    }

}