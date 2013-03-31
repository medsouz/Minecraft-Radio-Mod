package net.medsouz.radioblock;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.medsouz.radioblock.player.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "RadioBlock", name = "Radio Block", version = "0.1")
@NetworkMod(channels = {"RadioBlock"},clientSideRequired = true,serverSideRequired = false,packetHandler = PacketHandler.class, connectionHandler = ConnectionHandler.class)
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
		GameRegistry.addRecipe(new ItemStack(blockRadio), "  y", "xyx", "xzx", 'x', new ItemStack(Block.planks), 'y', new ItemStack(Item.ingotIron), 'z', new ItemStack(Item.diamond));
		LanguageRegistry.addName(blockRadio, "Radio");
		proxy.initTileEntities();
	}
	
	@ServerStopped
	public void serverStop(FMLServerStoppedEvent event) {
		//System.out.println("Stopped!");
		killAllStreams();
	}
	
	public static void killAllStreams(){
		for(MP3Player p : playerList){
			p.stop();
		}
	}
	
	public static Packet250CustomPayload setPacket(int x, int y, int z, String streamURL, boolean playing){
		Packet250CustomPayload p = new Packet250CustomPayload();
		p.channel = "RadioBlock";
		ByteArrayOutputStream baos = new ByteArrayOutputStream(8);
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(0x01);//0x01 will identify this as setting the stream
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeUTF(streamURL);
			dos.writeBoolean(playing);
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.length = baos.toByteArray().length;
		p.data = baos.toByteArray();
		return p;
	}
}
