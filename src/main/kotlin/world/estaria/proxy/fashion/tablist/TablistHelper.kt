package world.estaria.proxy.fashion.tablist

import net.kyori.adventure.text.Component
import world.avionik.minecraft.common.extension.text
import world.estaria.server.manager.api.server.Server
import world.estaria.translation.api.namespace.TranslationService
import world.estaria.translation.api.placeholder.Placeholder
import java.util.*

/**
 * @author Niklas Nieberler
 */

class TablistHelper {

    private val namespace = TranslationService.fromNamespace("tablist")

    fun getTablistComponent(key: String, locale: Locale, server: Server): Component {
        var component = text("                                                                  ")

        this.namespace.getTranslationsFromStartKey(
            key,
            locale,
            Placeholder.parsed("server", server.getGameName() ?: server.getName())
        ).forEach {
            component = component.append(it)
        }
        return component
    }

}