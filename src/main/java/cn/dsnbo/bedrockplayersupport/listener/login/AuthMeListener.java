package cn.dsnbo.bedrockplayersupport.listener.login;

import cn.dsnbo.bedrockplayersupport.BedrockPlayerSupport;
import cn.dsnbo.bedrockplayersupport.util.Forms;
import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

/**
 * @author DongShaoNB
 */
public class AuthMeListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID playerUuid = player.getUniqueId();
        String playerName = player.getName();
        if (FloodgateApi.getInstance().isFloodgatePlayer(playerUuid)) {
            if (!AuthMeApi.getInstance().isAuthenticated(player)) {
                if (AuthMeApi.getInstance().isRegistered(playerName)) {
                    AuthMeApi.getInstance().forceLogin(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', BedrockPlayerSupport.getInstance().getConfig().getString("login.auto-message")));
                } else {
                    Forms.openRegisterForm(player);
                    BedrockPlayerSupport.getInstance().getLogger().info("1");
                }
            }
        }
    }
}
