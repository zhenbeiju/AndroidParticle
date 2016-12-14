package com.andengine.particle.modifier;


import com.andengine.particle.Particle;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:36:18 - 29.06.2010
 */
public class RotationParticleModifier extends BaseSingleValueSpanParticleModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public RotationParticleModifier(final float pFromTime, final float pToTime, final float pFromRotation, final float pToRotation) {
		this(pFromTime, pToTime, pFromRotation, pToRotation, EaseLinear.getInstance());
	}

	public RotationParticleModifier(final float pFromTime, final float pToTime, final float pFromRotation, final float pToRotation, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromRotation, pToRotation, pEaseFunction);
	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetInitialValue(final Particle pParticle, final float pRotation) {
		pParticle.getEntity().setRotation(pRotation);
	}

	@Override
	protected void onSetValue(final Particle pParticle, final float pPercentageDone, final float pRotation) {
		pParticle.getEntity().setRotation(pRotation);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
