package net.medsouz.radioblock;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRadio extends ModelBase
{
  //fields
    ModelRenderer Body;
    ModelRenderer Speaker_Right;
    ModelRenderer Speaker_Left;
    ModelRenderer HandleBaseRight;
    ModelRenderer HandleBaseLeft;
    ModelRenderer HandleBar;
    ModelRenderer Antenna;
  
  public ModelRadio()
  {
    textureWidth = 32;
    textureHeight = 16;
    
      Body = new ModelRenderer(this, 0, 9);
      Body.addBox(0F, 0F, 0F, 9, 4, 3);
      Body.setRotationPoint(-5F, 0F, 0F);
      Body.setTextureSize(32, 16);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      Speaker_Right = new ModelRenderer(this, 7, 0);
      Speaker_Right.mirror = true;
      Speaker_Right.addBox(0F, 0F, 0F, 2, 2, 1);
      Speaker_Right.setRotationPoint(1F, 1F, -0.5333334F);
      Speaker_Right.setTextureSize(32, 16);
      Speaker_Right.mirror = true;
      setRotation(Speaker_Right, 0F, 0F, 0F);
      Speaker_Right.mirror = false;
      Speaker_Left = new ModelRenderer(this, 1, 0);
      Speaker_Left.addBox(0F, 0F, 0F, 2, 2, 1);
      Speaker_Left.setRotationPoint(-4F, 1F, -0.5F);
      Speaker_Left.setTextureSize(32, 16);
      Speaker_Left.mirror = true;
      setRotation(Speaker_Left, 0F, 0F, 0F);
      HandleBaseRight = new ModelRenderer(this, 0, 0);
      HandleBaseRight.addBox(0F, 0F, 0F, 1, 1, 1);
      HandleBaseRight.setRotationPoint(1F, -1F, 1F);
      HandleBaseRight.setTextureSize(32, 16);
      HandleBaseRight.mirror = true;
      setRotation(HandleBaseRight, 0F, 0F, 0F);
      HandleBaseLeft = new ModelRenderer(this, 0, 0);
      HandleBaseLeft.addBox(0F, 0F, -1F, 1, 1, 1);
      HandleBaseLeft.setRotationPoint(-3F, -1F, 2F);
      HandleBaseLeft.setTextureSize(32, 16);
      HandleBaseLeft.mirror = true;
      setRotation(HandleBaseLeft, 0F, 0F, 0F);
      HandleBar = new ModelRenderer(this, 0, 0);
      HandleBar.addBox(0F, 0F, 0F, 5, 1, 1);
      HandleBar.setRotationPoint(-3F, -2F, 1F);
      HandleBar.setTextureSize(32, 16);
      HandleBar.mirror = true;
      setRotation(HandleBar, 0F, 0F, 0F);
      Antenna = new ModelRenderer(this, 26, 0);
      Antenna.addBox(0F, 0F, 0F, 1, 5, 1);
      Antenna.setRotationPoint(-1F, -3.2F, 1.8F);
      Antenna.setTextureSize(32, 16);
      Antenna.mirror = true;
      setRotation(Antenna, 0F, 0F, -0.6457718F);
  }
  
  public void render(float f, float f1, float f2, float f3, float f4, float f5)
  {
    //super.render(entity, f, f1, f2, f3, f4, f5);
    //setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Body.render(f5);
    Speaker_Right.render(f5);
    Speaker_Left.render(f5);
    HandleBaseRight.render(f5);
    HandleBaseLeft.render(f5);
    HandleBar.render(f5);
    Antenna.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

}
