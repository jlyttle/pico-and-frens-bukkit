package io.github.weruder.twitter;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
	
	@Override
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
 	    getLogger().log(Level.INFO, "Player " + event.getPlayer().getName() + " is logging into twitter!");
 	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
	if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	event.getPlayer().sendMessage(ChatColor.AQUA + "You clicked a " + ChatColor.BOLD + event.getClickedBlock().getType().toString().toLowerCase().replace("_", ""));
	}
	}
}
