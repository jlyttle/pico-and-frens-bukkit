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

public final class Main extends JavaPlugin {

	@EventHandler
	(priority=EventPriority.HIGH) 
	public void onPlayerUse(PlayerInteractEvent event){
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
			if(p.getItemInHand().getType() == Material.STICK){
				Fireball fire = p.getWorld().spawn(event.getPlayer().getLocation().add(new Vector(0.0D, 1.0D, 0.0D)), Fireball.class); fire.setFireTicks(0); fire.setShooter(p); } 
			else if(p.getItemInHand().getType() == Material.STICK){ //Do whatever } } }
			}
		}
	}
}
