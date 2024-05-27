package cc.dsnb.bedrockplayersupport.form.basic

import cc.dsnb.bedrockplayersupport.BedrockPlayerSupport
import com.earth2me.essentials.Essentials
import com.earth2me.essentials.User
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.geysermc.cumulus.form.SimpleForm

/**
 * @author DongShaoNB
 */
class EssXForm {

    fun sendHomeForm(player: Player) {
        val user = User(player, Bukkit.getPluginManager().getPlugin("Essentials") as Essentials)
        val form = SimpleForm.builder()
            .title(
                ChatColor.translateAlternateColorCodes(
                    '&', BedrockPlayerSupport.langConfigManager.getConfigData().homeFormTitle()
                )
            )
            .validResultHandler { simpleFormResponse ->
                player.chat("/home " + simpleFormResponse.clickedButton().text())
            }
        for (homeName in user.homes) {
            form.button(homeName)
        }
        BedrockPlayerSupport.floodgateApi.sendForm(player.uniqueId, form)
    }

}