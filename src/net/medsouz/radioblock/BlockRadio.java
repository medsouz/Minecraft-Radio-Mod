package net.medsouz.radioblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.medsouz.radioblock.player.MP3Player;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRadio extends Block implements ITileEntityProvider{

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
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		if(par1World.isRemote){//only run on the clients, the server admins might get scared if their servers start blasting music
			//System.out.println("The block at "+par2+", "+par3+", "+par4+" was right clicked "+par6);
			TileEntityRadio ter = (TileEntityRadio) par1World.getBlockTileEntity(par2, par3, par4);
			//TODO: Stream GUI
			Minecraft.getMinecraft().displayGuiScreen(new GuiRadio(ter));
		}
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileEntityRadio();
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
		
	}
	
	@Override
    public int getRenderType()
    {
        return 189;
    }
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		switch(par1IBlockAccess.getBlockTileEntity(par2, par3, par4).getBlockMetadata()){
			default:
				setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 0.5F, 0.7F);
				break;
			case 1:
				setBlockBounds(0.4F, 0.5F, 0.0F, 0.7F, 0.0F, 1.0F);
				break;
			case 2:
				setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 0.5F, 0.7F);
				break;
			case 3:
				setBlockBounds(0.4F, 0.5F, 0.0F, 0.7F, 0.0F, 1.0F);
				break;
		}
		
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack){
		int dir = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, dir, 0);
	}

}
