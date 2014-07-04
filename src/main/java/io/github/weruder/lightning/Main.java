package io.github.weruder.lightning;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class Main extends JavaPlugin implements Listener{

	@EventHandler
	(priority=EventPriority.HIGH) 
	public void onPlayerUse(PlayerInteractEvent event){
		getLogger().info("Player has used an item!");
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
		getLogger().info("Air has been right-clicked!");
			if(p.getItemInHand().getType() == Material.STICK){
				getLogger().info("BLASTIN'!");
				Fireball fire = p.getWorld().spawn(event.getPlayer().getLocation().add(new Vector(0.0D, 1.0D, 0.0D)), Fireball.class); 
				fire.setFireTicks(0); 
				fire.setShooter(p); 
			}
		}
	}

	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerListeners(this, this);
	}
 
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
}
