package net.overload.limbo;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;

import net.overload.limbo.events.GlobalLimboEvents;
import net.overload.limbo.logger.LogLevel;
import net.overload.limbo.logger.Logger;

public class LimboPlugin extends JavaPlugin {

	private static LimboPlugin instance;

	public Logger log = new Logger("Limbo");

	/**
	 * Plugin interface
	 */

	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {
		instance = this;
		log.send(LogLevel.SUCCESS, "Enabled Limbo plugin !");
		
		Bukkit.getPluginManager().registerEvents(new GlobalLimboEvents(), this);
	}

	@Override
	public void onDisable() {
		log.send(LogLevel.INFO, "Disabling Limbo plugin!");
	}

	/**
	 * Custom functions
	 */

	public void registerPacketsListener() {
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Server.PLAYER_INFO, PacketType.Play.Server.WORLD_PARTICLES }) {
			public void onPacketSending(PacketEvent event) {
				if (event.getPacketType() == PacketType.Play.Server.PLAYER_INFO) {
					List<PlayerInfoData> values = event.getPacket().getPlayerInfoDataLists().read(0);
					PlayerInfoData playerInfoData = values.get(0);
					playerInfoData = new PlayerInfoData(((PlayerInfoData) playerInfoData).getProfile(), -1, EnumWrappers.NativeGameMode.ADVENTURE, null);
					values.set(0, playerInfoData);
					event.getPacket().getPlayerInfoDataLists().write(0, values);
					event.getPacket().getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.REMOVE_PLAYER);
				} else if (event.getPacketType() == PacketType.Play.Server.WORLD_PARTICLES) {
					event.setCancelled(true);
				}
			}
		});
	}

	/**
	 * Getters & Setters
	 */

	public Logger logger() {
		return log;
	}

	public static LimboPlugin get() {
		return instance;
	}

}
