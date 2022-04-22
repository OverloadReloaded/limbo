package net.overload.limbo.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("deprecation")
public class GlobalLimboEvents implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);

		p.setCustomName(" ");
		p.setDisplayName(" ");
		p.setPlayerListName(" ");
		p.setAllowFlight(false);
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setHealth(20.0D);
		p.setFoodLevel(20);
		p.setSaturation(Float.MAX_VALUE);
		p.teleport(new Location(p.getWorld(), 0.5D, 100.5D, 0.5D));
		p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2147483647, 9, false, false));
		p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 0, false, false));
		p.setWalkSpeed(0.0F);
		p.setFlySpeed(0.0F);
		
		try {
			p.setFlying(true);
		} catch (Exception exception) {
		}

		p.hidePlayer(p);
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.hidePlayer(p);
			p.hidePlayer(players);
		}
	}

	@EventHandler
	public void onPlayerToggleFly(PlayerToggleFlightEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
	}

	@EventHandler
	public void PlayerToggleSneakEvent(PlayerToggleSneakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void PlayerToggleSprintEvent(PlayerToggleSprintEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onChat(PlayerChatEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onCommande(PlayerCommandPreprocessEvent e) {
		e.setCancelled(true);
	}
}
