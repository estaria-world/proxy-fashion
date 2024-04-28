package world.estaria.proxy.fashion.manager

import com.velocitypowered.api.proxy.Player
import world.estaria.proxy.fashion.config.ConfigMapHandler
import world.estaria.server.manager.api.ServerManagerApi

/**
 * @author Niklas Nieberler
 */

class TablistManager(
    private val configMapHandler: ConfigMapHandler
) {

    fun sendTablist(player: Player) {
        val server = ServerManagerApi.instance.serverManager
            .getServerByPlayer(player.uniqueId) ?: return
        val config = this.configMapHandler.getConfig()
        val tablist = config.getTablist(server.getVariant()) ?: config.getDefaultTablist()
        player.sendPlayerListHeaderAndFooter(tablist.getHeader(), tablist.getFooter())
    }

}