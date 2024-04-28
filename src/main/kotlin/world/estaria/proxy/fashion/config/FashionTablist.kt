package world.estaria.proxy.fashion.config

import kotlinx.serialization.Serializable

/**
 * @author Niklas Nieberler
 */

@Serializable
class FashionTablist(
    val name: String,
    private val headers: List<String>,
    private val footers: List<String>
) {



}