package net.montoyo.mcef;

import net.montoyo.mcef.utilities.Log;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCEF", name = "MCEF", version = MCEF.VERSION)
public class MCEF {
	
	public static final String VERSION = "0.5";
	public static boolean ENABLE_EXAMPLE;
	
	@Mod.Instance
	public static MCEF INSTANCE;
	
	@SidedProxy(serverSide = "net.montoyo.mcef.BaseProxy", clientSide = "net.montoyo.mcef.client.ClientProxy")
	public static BaseProxy PROXY;
	
	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent ev) {
		Log.info("Loading MCEF config...");
		
		Configuration cfg = new Configuration(ev.getSuggestedConfigurationFile());
		ENABLE_EXAMPLE = cfg.getBoolean("exampleBrowser", "main", true, "Set this to false if you don't want to enable the F10 browser.");
		cfg.save();
	}
	
	@Mod.EventHandler
	public void onInit(FMLInitializationEvent ev) {
		Log.info("Now initializing MCEF v%s...", VERSION);
		PROXY.onInit();
	}

}