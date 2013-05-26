// Date: 31/12/2012 15:51:05
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package com.mraof.minestuck.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPawn extends ModelBase
{
  //fields
    ModelRenderer LeftFoot;
    ModelRenderer RightFoot;
    ModelRenderer RightLegBottom;
    ModelRenderer RightKnee;
    ModelRenderer RightLegTop;
    ModelRenderer LeftLegBottom;
    ModelRenderer LeftKnee;
    ModelRenderer LeftLegTop;
    ModelRenderer TorsoTop;
    ModelRenderer TorsoBottom;
    ModelRenderer TorsoMid;
    ModelRenderer Head;
    ModelRenderer hatbase;
    ModelRenderer hattop;
  
  public ModelPawn()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      LeftFoot = new ModelRenderer(this, 44, 27);
      LeftFoot.addBox(0F, 10F, 0F, 1, 1, 4);
      LeftFoot.setRotationPoint(-3.5F, 13F, -1F);
      LeftFoot.setTextureSize(64, 32);
      LeftFoot.mirror = true;
      setRotation(LeftFoot, 0F, 0F, 0F);
      RightFoot = new ModelRenderer(this, 54, 27);
      RightFoot.addBox(0F, 10F, 0F, 1, 1, 4);
      RightFoot.setRotationPoint(2.5F, 13F, -1F);
      RightFoot.setTextureSize(64, 32);
      RightFoot.mirror = true;
      setRotation(RightFoot, 0F, 0F, 0F);
      RightLegBottom = new ModelRenderer(this, 57, 22);
      RightLegBottom.addBox(0F, 5.8F, 1.5F, 1, 4, 1);
      RightLegBottom.setRotationPoint(2.5F, 13F, -1F);
      RightLegBottom.setTextureSize(64, 32);
      RightLegBottom.mirror = true;
      setRotation(RightLegBottom, -0.1487144F, 0F, 0F);
      RightKnee = new ModelRenderer(this, 57, 20);
      RightKnee.addBox(0F, 5F, 0.6F, 1, 1, 1);
      RightKnee.setRotationPoint(2.5F, 13F, -1F);
      RightKnee.setTextureSize(64, 32);
      RightKnee.mirror = true;
      setRotation(RightKnee, 0F, 0F, 0F);
      RightLegTop = new ModelRenderer(this, 57, 15);
      RightLegTop.addBox(0F, 1.1F, -0.1F, 1, 4, 1);
      RightLegTop.setRotationPoint(2.5F, 13F, -1F);
      RightLegTop.setTextureSize(64, 32);
      RightLegTop.mirror = true;
      setRotation(RightLegTop, 0.1487144F, 0F, 0F);
      LeftLegBottom = new ModelRenderer(this, 47, 22);
      LeftLegBottom.addBox(0F, 5.833333F, 1.5F, 1, 4, 1);
      LeftLegBottom.setRotationPoint(-3.5F, 13F, -1F);
      LeftLegBottom.setTextureSize(64, 32);
      LeftLegBottom.mirror = true;
      setRotation(LeftLegBottom, -0.1487144F, 0F, 0F);
      LeftKnee = new ModelRenderer(this, 47, 20);
      LeftKnee.addBox(0F, 5F, 0.6F, 1, 1, 1);
      LeftKnee.setRotationPoint(-3.5F, 13F, -1F);
      LeftKnee.setTextureSize(64, 32);
      LeftKnee.mirror = true;
      setRotation(LeftKnee, 0F, 0F, 0F);
      LeftLegTop = new ModelRenderer(this, 47, 15);
      LeftLegTop.addBox(0F, 1.1F, -0.1F, 1, 4, 1);
      LeftLegTop.setRotationPoint(-3.5F, 13F, -1F);
      LeftLegTop.setTextureSize(64, 32);
      LeftLegTop.mirror = true;
      setRotation(LeftLegTop, 0.1487144F, 0F, 0F);
      TorsoTop = new ModelRenderer(this, 0, 5);
      TorsoTop.addBox(0F, 0F, 0F, 6, 4, 4);
      TorsoTop.setRotationPoint(-3F, -0.5F, -2.866667F);
      TorsoTop.setTextureSize(64, 32);
      TorsoTop.mirror = true;
      setRotation(TorsoTop, -0.0371786F, 0F, 0F);
      TorsoBottom = new ModelRenderer(this, 0, 21);
      TorsoBottom.addBox(0F, 0F, 0F, 8, 7, 4);
      TorsoBottom.setRotationPoint(-4F, 7.5F, -3F);
      TorsoBottom.setTextureSize(64, 32);
      TorsoBottom.mirror = true;
      setRotation(TorsoBottom, 0.1115358F, 0F, 0F);
      TorsoMid = new ModelRenderer(this, 0, 13);
      TorsoMid.addBox(0F, 0F, 0F, 7, 4, 4);
      TorsoMid.setRotationPoint(-3.5F, 3.5F, -3F);
      TorsoMid.setTextureSize(64, 32);
      TorsoMid.mirror = true;
      setRotation(TorsoMid, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 21, 0);
      Head.addBox(0F, 0F, 0F, 6, 6, 6);
      Head.setRotationPoint(-3F, -6F, -2F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      hatbase = new ModelRenderer(this, 23, 12);
      hatbase.addBox(0F, 0F, 0F, 5, 1, 5);
      hatbase.setRotationPoint(-3.5F, -7.066667F, 1F);
      hatbase.setTextureSize(64, 32);
      hatbase.mirror = true;
      setRotation(hatbase, 0F, 0.8179294F, 0F);
      hattop = new ModelRenderer(this, 27, 19);
      hattop.addBox(0F, 0F, 0F, 3, 3, 3);
      hattop.setRotationPoint(-2.066667F, -9F, 1F);
      hattop.setTextureSize(64, 32);
      hattop.mirror = true;
      setRotation(hattop, 0F, 0.8179294F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    LeftFoot.render(f5);
    RightFoot.render(f5);
    RightLegBottom.render(f5);
    RightKnee.render(f5);
    RightLegTop.render(f5);
    LeftLegBottom.render(f5);
    LeftKnee.render(f5);
    LeftLegTop.render(f5);
    TorsoTop.render(f5);
    TorsoBottom.render(f5);
    TorsoMid.render(f5);
    Head.render(f5);
    hatbase.render(f5);
    hattop.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
	  
  }

}
