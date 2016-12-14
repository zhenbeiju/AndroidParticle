package com.andengine.particle.init;


import com.andengine.particle.Particle;
import com.andengine.particle.math.MathUtils;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:58:29 - 04.05.2010
 */
public abstract class BaseTripleValueParticleInitializer extends BaseDoubleValueParticleInitializer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	protected float mMinValueC;
	protected float mMaxValueC;

	// ===========================================================
	// Constructors
	// ===========================================================

	public BaseTripleValueParticleInitializer(final float pMinValueA, final float pMaxValueA, final float pMinValueB, final float pMaxValueB, final float pMinValueC, final float pMaxValueC) {
		super(pMinValueA, pMaxValueA, pMinValueB, pMaxValueB);
		this.mMinValueC = pMinValueC;
		this.mMaxValueC = pMaxValueC;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract void onInitializeParticle(final Particle pParticle, final float pValueA, final float pValueB, final float pValueC);

	@Override
	protected final void onInitializeParticle(final Particle pParticle, final float pValueA, final float pValueB) {
		this.onInitializeParticle(pParticle, pValueA, pValueB, this.getRandomValueC());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected float getRandomValueC() {
		if(this.mMinValueC == this.mMaxValueC) {
			return this.mMaxValueC;
		} else {
			return MathUtils.random(this.mMinValueC, this.mMaxValueC);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
