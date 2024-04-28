package world.estaria.proxy.fashion.config

import kotlinx.serialization.Serializable
import world.estaria.server.manager.api.enums.ServerVariant

/**
 * @author Niklas Nieberler
 */

@Serializable
class Config(
    private val motds: List<FashionMotd>,
    private val tablists: List<FashionTablist>
) {

    fun getMotd(type: FashionMotd.Type): FashionMotd {
        return this.motds.first { it.type == type }
    }

    fun getTablist(variant: ServerVariant): FashionTablist? {
        return this.tablists.firstOrNull { it.variant == variant }
    }

    fun getDefaultTablist(): FashionTablist {
        return getTablist(ServerVariant.SERVER)
            ?: throw NullPointerException("failed to find default tablist")
    }

    object Default {
        fun get(): Config {
            return Config(
                FashionMotd.Type.entries.map { FashionMotd.Default.get(it) },
                ServerVariant.entries.map { FashionTablist.Default.get(it) }
            )
        }
    }

}