package world.estaria.proxy.fashion.config

import world.estaria.github.file.manager.properties.GitHubYamlLoader

/**
 * @author Niklas Nieberler
 */

class ConfigHandler {

    private val yamlLoader = GitHubYamlLoader("estaria-world/proxy-configurations/master/fashion.yaml", Config.serializer())
    private var config = yamlLoader.getYaml()

    init {
        if (this.config == null)
            throw NullPointerException("failed to find fashion.yaml in github")
    }

    fun updateConfig() {
        this.config = this.yamlLoader.getYaml()
    }

    fun getConfig(): Config {
        return this.config ?: throw NullPointerException("failed to find fashion.yaml")
    }

}