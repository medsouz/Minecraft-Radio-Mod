package net.medsouz.radioblock;

import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.medsouz.radioblock.player.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RadioBlock", name = "Radio Block", version = "0.1")
public class ModRadioBlock {
	@Instance
	public static ModRadioBlock instance;
	
	@SidedProxy(clientSide = "net.medsouz.radioblock.ClientProxy", serverSide="net.medsouz.radioblock.CommonProxy")
	public static CommonProxy proxy;
	
	public final static Block blockRadio = new BlockRadio(189, Material.wood);
	
	public static List<MP3Player> playerList = new ArrayList<MP3Player>();
	
	@Init
	public void init(FMLInitializationEvent evt){
		GameRegistry.registerBlock(blockRadio, "Radio");
		GameRegistry.registerTileEntity(TileEntityRadio.class, "Radio");
		LanguageRegistry.addName(blockRadio, "Radio");
		proxy.initTileEntities();
	}
	
	@ServerStopped
	public void serverStop(FMLServerStoppedEvent event) {
		System.out.println("Stopped!");
		for(MP3Player p : playerList){
			p.stop();
		}
	}
}
