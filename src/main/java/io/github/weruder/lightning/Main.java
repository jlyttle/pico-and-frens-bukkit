package io.github.weruder.lightning;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if (player.getItemInHand().getType() == Material.STICK) {
	        // Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
	        // Can only create lightning up to 200 blocks away.
	        player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
	    }
	}
}
