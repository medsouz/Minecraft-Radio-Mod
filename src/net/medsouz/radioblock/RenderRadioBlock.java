package net.medsouz.radioblock;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class RenderRadioBlock extends TileEntitySpecialRenderer{

	ModelRadio model = new ModelRadio();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glPushMatrix();
			switch(tileentity.getBlockMetadata()){
				default:
					GL11.glTranslatef((float)d0+0.45f,(float)d1+0.4f,(float)d2+0.4f);
					GL11.glRotatef(180f, 1f, 0f, 0f);
					GL11.glRotatef(180f, 0f, 1f, 0f);
					break;
				case 1:
					GL11.glTranslatef((float)d0+0.7f,(float)d1+0.4f,(float)d2+0.45f);
					GL11.glRotatef(180f, 1f, 0f, 0f);
					GL11.glRotatef(270f, 0f, 1f, 0f);
					break;
				case 2:
					GL11.glTranslatef((float)d0+0.55f,(float)d1+0.4f,(float)d2+0.7f);
					GL11.glRotatef(180f, 1f, 0f, 0f);
					GL11.glRotatef(360f, 0f, 1f, 0f);
					break;
				case 3:
					GL11.glTranslatef((float)d0+0.4f,(float)d1+0.4f,(float)d2+0.55f);
					GL11.glRotatef(180f, 1f, 0f, 0f);
					GL11.glRotatef(90f, 0f, 1f, 0f);
					break;
			}
			
			bindTextureByName("/mods/radioblock/textures/blocks/radioentity.png");
			model.render(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
        GL11.glPopMatrix();
	}

}
