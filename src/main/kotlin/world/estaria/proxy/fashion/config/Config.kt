package world.estaria.proxy.fashion.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class Config(
    private val motds: List<FashionMotd>,
    val tablists: List<FashionTablist>
) {

    fun getMotd(type: FashionMotd.Type): FashionMotd {
        return this.motds.first { it.type == type }
    }

    object Default {
        fun get(): Config {
            return Config(
                arrayListOf(
                    FashionMotd(
                        FashionMotd.Type.MAINTENANCE,
                        "header",
                        "footer"
                    ),
                    FashionMotd(
                        FashionMotd.Type.LEGACY,
                        "header",
                        "footer"
                    ),
                    FashionMotd(
                        FashionMotd.Type.DEFAULT,
                        "header",
                        "footer"
                    )
                ),
                arrayListOf()
            )
        }
    }

}