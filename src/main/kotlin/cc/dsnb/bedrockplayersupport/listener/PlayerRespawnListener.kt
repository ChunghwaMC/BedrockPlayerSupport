package cc.dsnb.bedrockplayersupport.listener

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent

/**
 * @author DongShaoNB
 */
class PlayerRespawnListener : Listener {

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        val player = event.player
        val uuid = player.uniqueId
        if (BedrockPlayerSupport.floodgateApi.isFloodgatePlayer(uuid)) {
            val config = BedrockPlayerSupport.mainConfigManager.getConfigData()
            if (config.enableBackDeathLocForm()) {
                BedrockPlayerSupport.scheduler
                    .runTaskLaterAsynchronously(
                        { BedrockPlayerSupport.mainForm.openBedrockBackDeathLocForm(player) },
                        config.backDeathLocFormOpenDelayTick()
                    )
            }
        }
    }

}