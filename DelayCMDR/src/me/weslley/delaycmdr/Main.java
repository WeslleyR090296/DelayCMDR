package me.weslley.delaycmdr;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	public static ArrayList<String> timingCMD = new ArrayList<String>();
	public void onEnable()
	{
		System.out.println("[DelayCMDR] O plugin foi iniciado!");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	public void onDisable()
	{
		System.out.println("[DelayCMDR] O plugin foi desligado!");
	}
	@EventHandler
	public void preProcessCommand(PlayerCommandPreprocessEvent e) {
		String p = e.getPlayer().getName();
		if(timingCMD.contains(p)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("[DelayCMDR] O intervalo entre comandos deve ser de 5 segundos!");
		}
		else {
			timingCMD.add(p);
			this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				 
				  public void run() {
				      timingCMD.remove(p);
				      e.getPlayer().sendMessage("[DelayCMDR] Ja pode fazer comandos novamente!");
				  }
				}, 100L);
		}
	}
	
}
