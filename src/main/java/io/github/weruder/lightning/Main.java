package io.github.weruder.lightning;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
		//Grab the player object from the event. The final keyword lets us use it inside of scheduleSyncDelayedTask
		final Player player = event.getPlayer();
		//Check if we have leather in our hand, and if we're on the ground. The player's isOnGround method is deprecated,
		//so we typecast it as an Entity to get access to the method.
		if(player.getItemInHand().getType() == Material.LEATHER && ((Entity)player).isOnGround())
		{
			Vector current_velocity = player.getVelocity();
			//We set the player's velocity to their current velocity plus a vector of 1 in the y direction.
			//This will send the player in the same direction they were going, but up into the air as well
			player.setVelocity(current_velocity.add(new Vector(0, 1, 0)));
			//Ask the server to run this code in 10 ticks
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
				public void run(){
					Vector current_velocity = player.getVelocity();
					//Take the direction the player is looking in, and multiply the Y component by 0
					//so that the player will only glide in the x and z axes, not get a super jump upwards
					Vector recoil_velocity = player.getLocation().getDirection().multiply(new Vector(1, 0, 1));
					player.setVelocity(current_velocity.add(recoil_velocity));
				}
			}, 10); //Change this number if you want to make the wait time longer or shorter
		}
		
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			if(player.getItemInHand().getType() == Material.STICK)
			{
				Fireball fire = player.getWorld().spawn(event.getPlayer().getLocation().add(new Vector(0.0D, 1.0D, 0.0D)), Fireball.class); 
				fire.setFireTicks(0); 
				fire.setShooter(player); 
			}
		}
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    if (player.getItemInHand().getType() == Material.FISHING_ROD) {
	        // Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
	        // Can only create lightning up to 200 blocks away.
	        player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
	    }
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		/*if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			event.getPlayer().sendMessage(ChatColor.AQUA + "You clicked a " + ChatColor.BOLD + event.getClickedBlock().getType().toString().toLowerCase().replace("_", ""));
		}*/
	}
}



