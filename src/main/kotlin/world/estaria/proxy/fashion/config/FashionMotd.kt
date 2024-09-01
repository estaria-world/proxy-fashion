package world.estaria.proxy.fashion.config

import com.velocitypowered.api.proxy.InboundConnection
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import world.avionik.minecraft.common.extension.text
import world.estaria.proxy.manager.api.ProxyConfig
import world.estaria.proxy.manager.api.ProxyManagerApi

/**
 * @author Niklas Nieberler
 */

@Serializable
class FashionMotd(
    val type: Type,
    private val header: String,
    private val footer: String
) {

    object Default {
        fun get(type: Type): FashionMotd {
            return FashionMotd(
                type,
                "header",
                "footer"
            )
        }
    }

    enum class Type(
        private val requirementFunction: (ProxyConfig, Int) -> Boolean
    ) {
        MAINTENANCE({ config, _ -> config.maintenance }),

        LEGACY({ config, protocolVersion ->
            !config.supportedProtocolVersions.contains(protocolVersion)
        }),

        DEFAULT({ _, _ -> true });

        fun invoke(connection: InboundConnection): Boolean {
            val config = ProxyManagerApi.instance.configHandler.getConfig()
            val protocol = connection.protocolVersion.protocol
            return this.requirementFunction(config, protocol)
        }

        companion object {
            fun getType(connection: InboundConnection): Type {
                return entries.firstOrNull { it.invoke(connection) } ?: DEFAULT
            }
        }
    }

    fun toComponent(): Component {
        return text(this.header)
            .append(text("\n"))
            .append(text(this.footer))
    }

}