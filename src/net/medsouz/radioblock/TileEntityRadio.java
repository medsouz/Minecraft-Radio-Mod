package net.medsouz.radioblock;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.medsouz.radioblock.player.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRadio extends TileEntity{
	
	private MP3Player player = null;
	private boolean isPlaying = false;
	public String streamURL = "";
	
	public Block getBlockType(){
		return ModRadioBlock.blockRadio;
	}
	
	public void startStream(){
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(!isPlaying){
			isPlaying = true;
			if(side == Side.CLIENT){
				player = new MP3Player(streamURL);
				ModRadioBlock.playerList.add(player);
			}
		}else{
			//System.err.println("Tried to play a stream twice out of one radio!");
		}
	}
	
	public void stopStream(){
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(isPlaying){
			if(side == Side.CLIENT){
				player.stop();
				ModRadioBlock.playerList.remove(player);
			}
			isPlaying = false;//player.isPlaying();
		}else{
			//System.err.println("Tried to stop a nonplaying radio!");
		}
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void invalidate(){
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(ModRadioBlock.setPacket(xCoord, yCoord, zCoord, streamURL, !isPlaying()));
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
		streamURL = par1NBTTagCompound.getString("streamurl");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setString("streamurl", streamURL);
	}
	
	@Override
	public Packet getDescriptionPacket(){
		return ModRadioBlock.setPacket(xCoord, yCoord, zCoord, streamURL, isPlaying);
	}
}
