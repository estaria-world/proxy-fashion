package world.estaria.proxy.fashion.config

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import world.avionik.minecraft.common.extension.text
import world.estaria.server.manager.api.enums.ServerVariant

/**
 * @author Niklas Nieberler
 */

@Serializable
class FashionTablist(
    val variant: ServerVariant,
    private val headers: List<String>,
    private val footers: List<String>
) {

    object Default {
        fun get(variant: ServerVariant): FashionTablist {
            return FashionTablist(
                variant,
                listOf("asd"),
                listOf("asd")
            )
        }
    }

    fun getHeader(): Component {
        return text(this.headers.joinToString("\n"))
    }

    fun getFooter(): Component {
        return text(this.footers.joinToString("\n"))
    }

}