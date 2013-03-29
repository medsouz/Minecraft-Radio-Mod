package net.medsouz.radioblock;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.medsouz.radioblock.player.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RadioBlock", name = "Radio Block", version = "0.1")
public class ModRadioBlock {
	@Instance
	public static ModRadioBlock instance;
	
	@SidedProxy(clientSide = "net.medsouz.radioblock.ClientProxy", serverSide="net.medsouz.radioblock.CommonProxy")
	public static CommonProxy proxy;
	
	public final static Block blockRadio = new BlockRadio(189, Material.wood);
	
	public static MP3Player currentPlayer;
	
	@Init
	public void init(FMLInitializationEvent evt){
		GameRegistry.registerBlock(blockRadio, "Radio");
		LanguageRegistry.addName(blockRadio, "Radio");
	}
}
