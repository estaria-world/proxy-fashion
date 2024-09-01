package world.estaria.proxy.fashion.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class Config(
    private val motds: List<FashionMotd>,
) {

    fun getMotd(type: FashionMotd.Type): FashionMotd {
        return this.motds.first { it.type == type }
    }

}