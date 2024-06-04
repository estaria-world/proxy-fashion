package world.estaria.proxy.fashion.listener

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyPingEvent
import world.estaria.proxy.fashion.config.ConfigMapHandler
import world.estaria.proxy.fashion.config.FashionMotd
import world.estaria.proxy.manager.api.ProxyConfigHandler

/**
 * @author Niklas Nieberler
 */

class ProxyPingListener(
    private val proxyConfigHandler: ProxyConfigHandler,
    private val configMapHandler: ConfigMapHandler
) {

    @Subscribe
    fun handleProxyPing(event: ProxyPingEvent) {
        val ping = event.ping

        val hostAddress = event.connection.remoteAddress.address.hostAddress
        if (hostAddress == "0:0:0:0:0:0:0:1%0")
            return

        val proxyConfig = this.proxyConfigHandler.getConfig()
        val config = this.configMapHandler.getConfig()

        val serverPing = ping.asBuilder()
            .description(config.getMotd(FashionMotd.Type.getType(event.connection)).toComponent())
            .maximumPlayers(proxyConfig.maximumPlayers)
            .build()
        event.ping = serverPing
    }

}