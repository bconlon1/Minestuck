package com.mraof.minestuck.entity;

import java.util.ArrayList;
import java.util.List;

import com.mraof.minestuck.entity.ai.EntityAIAttackByDistance;
import com.mraof.minestuck.entity.ai.EntityAINearestAttackableTargetWithHeight;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityBishop extends EntityCarapacian implements IRangedAttackMob, IMob
{
	private EntityAIAttackByDistance entityAIAttackByDistance = new EntityAIAttackByDistance(this, 0.25F, 30, 64.0F);
	int burnTime;

	public EntityBishop(World par1World) 
	{
		super(par1World);
		this.moveSpeed = 0.2F;
		this.setSize(1.9F, 4.1F);
		this.experienceValue = 3;
	}
	
	@Override
	public int getMaxHealth() 
	{
		return 40;
	}

	@Override
	public void initCreature() 
	{
		this.setCurrentItemOrArmor(0, new ItemStack(Item.blazeRod));
		this.targetTasks.addTask(4, entityAIAttackByDistance);
	}
	
	@Override
	public void attackEntityWithRangedAttack(EntityLiving entityliving, float f) 
	{

        double distanceX = entityliving.posX - this.posX;
        double distanceY = entityliving.boundingBox.minY + (double)(entityliving.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
        double distanceZ = entityliving.posZ - this.posZ;
		
//        float distance = entityliving.getDistanceToEntity(this);
//        System.out.println("attacking");
//        this.motionY = 20;
//
//        EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.worldObj, this, distanceX + this.rand.nextGaussian() * (double)distance, distanceY, distanceZ + this.rand.nextGaussian() * (double)distance);
//        entitysmallfireball.posY = this.posY + (double)(this.height) + 0.5D;
//        this.worldObj.spawnEntityInWorld(entitysmallfireball);
        EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, distanceX, distanceY, distanceZ);
        entitylargefireball.field_92057_e = 1;
        double d8 = (double)this.width;
        Vec3 vec3 = this.getLook(1.0F);
        entitylargefireball.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0F  + vec3.xCoord * d8;
        entitylargefireball.posY = this.posY + (double)(this.height / 2.0F);
        entitylargefireball.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0F + vec3.zCoord * d8;
        this.worldObj.spawnEntityInWorld(entitylargefireball);
	}
	public int getAttackStrength(Entity par1Entity)
	{
		ItemStack var2 = this.getHeldItem();
		int var3 = 0;
		
		if (var2 != null)
			var3 += var2.getDamageVsEntity(this);
		
		return var3;
	}
	
	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	protected void attackEntity(Entity par1Entity, float par2)
	{

		System.out.println(this.getAttackTarget().toString());
		if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackTime = 20;
			this.attackEntityAsMob(par1Entity);
		}
	}

	public boolean attackEntityAsMob(Entity par1Entity)
	{
		int damage = this.getAttackStrength(par1Entity);
		int knockback = 6;
		par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)knockback * 0.5F));
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
	}
	protected void setCombatTask() 
	{
		if(entityAIAttackByDistance == null)
			entityAIAttackByDistance = new EntityAIAttackByDistance(this, 0.25F, 30, 64.0F);
		this.tasks.removeTask(this.entityAIAttackByDistance);
		this.tasks.addTask(4, this.entityAIAttackByDistance);
	}
	public void setCurrentItemOrArmor(int slot, ItemStack par2ItemStack)
	{
		super.setCurrentItemOrArmor(slot, par2ItemStack);

		if (!this.worldObj.isRemote && slot == 0)
		{
			this.setCombatTask();
		}
	}
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) 
	{
		if(par1DamageSource.isFireDamage())
		{
			burnTime += par2;
			if(burnTime <= 3)
				return false;
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}
	@Override
	EntityAINearestAttackableTargetWithHeight entityAINearestAttackableTargetWithHeight() 
	{
		EntityAINearestAttackableTargetWithHeight ai = new EntityAINearestAttackableTargetWithHeight(this, EntityLiving.class, 256.0F, 0, true, false, attackEntitySelector);
		ai.setTargetHeightDistance(64);
		return ai;
	}
}
