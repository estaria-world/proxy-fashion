package world.estaria.proxy.fashion.plugin

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyReloadEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import world.estaria.proxy.fashion.config.ConfigMapHandler
import world.estaria.proxy.fashion.listener.PostLoginListener
import world.estaria.proxy.fashion.listener.ProxyPingListener
import world.estaria.proxy.fashion.tablist.TablistManager
import world.estaria.proxy.manager.api.ProxyManagerApi
import world.estaria.translation.api.TranslationInitializer

/**
 * @author Niklas Nieberler
 */

@Plugin(id = "proxy-fashion", name = "proxy-fashion", version = "1.0.1", authors = ["MrManHD"])
class VelocityPlugin @Inject constructor(
    private val server: ProxyServer
) {

    private val configMapHandler = ConfigMapHandler()

    @Subscribe
    fun handleInitialize(event: ProxyInitializeEvent) {
        TranslationInitializer("tablist", "proxy")
            .initialize()

        val tablistManager = TablistManager(this.server)
        tablistManager.executeTablistScheduler()

        val configHandler = ProxyManagerApi.instance.configHandler
        val eventManager = this.server.eventManager
        eventManager.register(this, ProxyPingListener(configHandler, this.configMapHandler))
        eventManager.register(this, PostLoginListener(tablistManager))
    }

    @Subscribe
    fun handleProxyReload(event: ProxyReloadEvent) {
        this.configMapHandler.updateConfig()
    }

}