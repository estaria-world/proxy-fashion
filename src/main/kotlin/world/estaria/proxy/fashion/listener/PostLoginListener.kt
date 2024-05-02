package world.estaria.proxy.fashion.listener

import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PostLoginEvent
import world.estaria.proxy.fashion.tablist.TablistManager
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * @author Niklas Nieberler
 */

class PostLoginListener(
    private val tablistManager: TablistManager
) {

    private val scheduler = Executors.newScheduledThreadPool(0)

    @Subscribe
    fun handlePostLogin(event: PostLoginEvent) {
        val player = event.player

        this.scheduler.schedule({
            try {
                this.tablistManager.sendTablist(player)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        },150, TimeUnit.MILLISECONDS)
    }

}