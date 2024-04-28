package world.estaria.proxy.fashion.listener

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PostLoginEvent
import world.estaria.proxy.fashion.manager.TablistManager
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * @author Niklas Nieberler
 */

class PostLoginListener(
    private val scheduler: ScheduledExecutorService,
    private val tablistManager: TablistManager
) {

    @Subscribe
    fun handlePostLogin(event: PostLoginEvent) {
        val player = event.player

        scheduler.schedule({
            this.tablistManager.sendTablist(player)
        },150, TimeUnit.MILLISECONDS)
    }

}