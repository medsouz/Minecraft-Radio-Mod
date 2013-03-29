package net.medsouz.radioblock;

import net.medsouz.radioblock.player.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockRadio extends Block{

	public BlockRadio(int par1, Material par2Material) {
		super(par1, par2Material);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName("Radio");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public void registerIcons(IconRegister ir){
		blockIcon = ir.registerIcon("radioblock:radio");
	}

	private boolean debounce = false;
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		if(par1World.isRemote){//only run on the clients, the server admins might get scared if their servers start blasting music
			if(!debounce){
				debounce = true;
				System.out.println("The block at "+par2+", "+par3+", "+par4+" was right clicked "+par6);
				//TODO: Stream GUI
				if(ModRadioBlock.currentPlayer != null && ModRadioBlock.currentPlayer.isPlaying()){
					ModRadioBlock.currentPlayer.stop();
				}else{
					ModRadioBlock.currentPlayer = new MP3Player("http://listen.radiohyrule.com:8000/listen");
				}
			}
		}
		debounce = false;
		return true;
	}
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6){
		if(ModRadioBlock.currentPlayer != null){
			ModRadioBlock.currentPlayer.stop();
		}	
	}

}
