package net.medsouz.radioblock;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		//MinecraftForgeClient.preloadTexture(RADIO_PNG);
	}
	
	@Override
	public void initTileEntities(){
		System.out.println("registering renderer");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRadio.class, new RenderRadioBlock());
	}
}
