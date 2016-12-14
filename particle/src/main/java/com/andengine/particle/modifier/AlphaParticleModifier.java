package com.andengine.particle.modifier;


import com.andengine.particle.Particle;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 21:21:10 - 14.03.2010
 */
public class AlphaParticleModifier extends BaseSingleValueSpanParticleModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public AlphaParticleModifier(final float pFromTime, final float pToTime, final float pFromAlpha, final float pToAlpha) {
		this(pFromTime, pToTime, pFromAlpha, pToAlpha, EaseLinear.getInstance());
	}

	public AlphaParticleModifier(final float pFromTime, final float pToTime, final float pFromAlpha, final float pToAlpha, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromAlpha, pToAlpha, pEaseFunction);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetInitialValue(final Particle pParticle, final float pAlpha) {
		pParticle.getEntity().setAlpha(pAlpha);
	}

	@Override
	protected void onSetValue(final Particle pParticle, final float pPercentageDone, final float pAlpha) {
		pParticle.getEntity().setAlpha(pAlpha);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
