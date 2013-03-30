package net.medsouz.radioblock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.medsouz.radioblock.player.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRadio extends TileEntity{
	
	private MP3Player player = null;
	private boolean isPlaying = false;
	public String streamURL = "derp";
	
	public Block getBlockType(){
		return ModRadioBlock.blockRadio;
	}
	
	@SideOnly(Side.CLIENT)
	public void startStream(){
		if(!isPlaying){
			isPlaying = true;
			player = new MP3Player(streamURL);
			ModRadioBlock.playerList.add(player);
		}else{
			System.err.println("Tried to play a stream twice out of one radio!");
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void stopStream(){
		if(isPlaying){
			player.stop();
			ModRadioBlock.playerList.remove(player);
			isPlaying = false;//player.isPlaying();
		}else{
			System.err.println("Tried to stop a nonplaying radio!");
		}
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void invalidate(){
		if(isPlaying){
			stopStream();
		}
		super.invalidate();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void onChunkUnload(){
		if(isPlaying){
			stopStream();
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateEntity() {
		if(Minecraft.getMinecraft().thePlayer != null && player != null && !isInvalid()){
			float vol = (float)getDistanceFrom(Minecraft.getMinecraft().thePlayer.posX,Minecraft.getMinecraft().thePlayer.posY,Minecraft.getMinecraft().thePlayer.posZ);
			if(vol > 10000){
				player.setVolume(0f);
			}else{
				float v2 = (10000f / vol) / 100f;
				if(v2 > 1){
					player.setVolume(1);
				}else{
					player.setVolume(v2);
				}
			}
			//System.out.println("streamurl: \""+streamURL+"\"");
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readFromNBT(par1NBTTagCompound);
		//TODO: read last used stream
		//streamURL = par1NBTTagCompound.getString("streamurl");
		System.out.println("Read: \""+streamURL+"\"");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);
		//TODO: write last used stream
		par1NBTTagCompound.setString("streamurl", streamURL);
		System.out.println("Wrote: \""+streamURL+"\"");
	}
}
