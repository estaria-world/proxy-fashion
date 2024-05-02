package world.estaria.proxy.fashion.tablist

import net.kyori.adventure.text.Component
import world.avionik.minecraft.common.extension.text
import world.estaria.server.manager.api.server.Server
import world.estaria.translation.api.TranslationManager
import world.estaria.translation.api.placeholder.Placeholder
import java.util.*

/**
 * @author Niklas Nieberler
 */

class TablistHelper(
    private val translationManager: TranslationManager
) {

    fun getHeader(server: Server, locale: Locale): Component {
        return text("                                                                  ")
            .appendNewline()
            .append(this.translationManager.translate("header.network.info", locale))
            .appendNewline()
            .append(
                this.translationManager.translate(
                    "${server.getVariant().name.lowercase()}.header.current.server.info",
                    locale,
                    Placeholder.parsed("server", server.getName())
                )
            )
            .appendNewline()
            .append(text(" "))
    }

    fun getFooter(locale: Locale): Component {
        return text(" ")
            .appendNewline()
            .append(this.translationManager.translate("footer.join.discord", locale))
            .appendNewline()
            .append(this.translationManager.translate("footer.discord.url", locale))
            .appendNewline()
            .append(text(" "))
    }

}