package io.github.weruder.lightning;

import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class Main extends JavaPlugin implements Listener 
{

	@Override
	//Whenever we enable the plugin for the first time, this method will be called
 	public void onEnable() 
	{ 
	    //We need to make sure that our plugin manager knows that we're listening for events, so we call this method
 	    getServer().getPluginManager().registerEvents(this, this);
 	}

	@EventHandler
	//Whenever a player logs in, this event will be called
	public void onLogin(PlayerLoginEvent event) 
	{
	    //We're going to write out to the console that the player has logged in.
 	    getLogger().log(Level.INFO, "Player " + event.getPlayer().getName() + " is logging in!");
 	}
	
	@EventHandler
	(priority=EventPriority.HIGH) 
	public void onPlayerUse(PlayerInteractEvent event)
	{
		getLogger().info("Player has used an item!");
		Player p = event.getPlayer();
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
		getLogger().info("Air has been right-clicked!");
			if(p.getItemInHand().getType() == Material.STICK)
			{
				getLogger().info("BLASTIN'!");
				Fireball fire = p.getWorld().spawn(event.getPlayer().getLocation().add(new Vector(0.0D, 1.0D, 0.0D)), Fireball.class); 
				fire.setFireTicks(0); 
				fire.setShooter(p); 
			}
		}
	}
}
