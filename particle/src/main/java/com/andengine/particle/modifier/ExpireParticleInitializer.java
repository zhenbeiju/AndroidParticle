package com.andengine.particle.modifier;


import com.andengine.particle.Particle;
import com.andengine.particle.init.IParticleInitializer;
import com.andengine.particle.math.MathUtils;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 21:21:10 - 14.03.2010
 */
public class ExpireParticleInitializer implements IParticleInitializer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private float mMinLifeTime;
	private float mMaxLifeTime;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ExpireParticleInitializer(final float pLifeTime) {
		this(pLifeTime, pLifeTime);
	}

	public ExpireParticleInitializer(final float pMinLifeTime, final float pMaxLifeTime) {
		this.mMinLifeTime = pMinLifeTime;
		this.mMaxLifeTime = pMaxLifeTime;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public float getMinLifeTime() {
		return this.mMinLifeTime;
	}

	public float getMaxLifeTime() {
		return this.mMaxLifeTime;
	}

	public void setLifeTime(final float pLifeTime) {
		this.mMinLifeTime = pLifeTime;
		this.mMaxLifeTime = pLifeTime;
	}

	public void setLifeTime(final float pMinLifeTime, final float pMaxLifeTime) {
		this.mMinLifeTime = pMinLifeTime;
		this.mMaxLifeTime = pMaxLifeTime;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onInitializeParticle(final Particle pParticle) {
		pParticle.setExpireTime((MathUtils.RANDOM.nextFloat() * (this.mMaxLifeTime - this.mMinLifeTime) + this.mMinLifeTime));
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
