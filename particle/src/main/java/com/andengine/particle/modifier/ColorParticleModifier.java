package com.andengine.particle.modifier;


import com.andengine.particle.Particle;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:22:26 - 29.06.2010
 */
public class ColorParticleModifier extends BaseTripleValueSpanParticleModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ColorParticleModifier(final float pFromTime, final float pToTime, final float pFromRed, final float pToRed, final float pFromGreen, final float pToGreen, final float pFromBlue, final float pToBlue) {
		this(pFromTime, pToTime, pFromRed, pToRed, pFromGreen, pToGreen, pFromBlue, pToBlue, EaseLinear.getInstance());
	}

	public ColorParticleModifier(final float pFromTime, final float pToTime, final float pFromRed, final float pToRed, final float pFromGreen, final float pToGreen, final float pFromBlue, final float pToBlue, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromRed, pToRed, pFromGreen, pToGreen, pFromBlue, pToBlue, pEaseFunction);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetInitialValues(final Particle pParticle, final float pRed, final float pGreen, final float pBlue) {
		pParticle.getEntity().setColor(pRed, pGreen, pBlue);
	}

	@Override
	protected void onSetValues(final Particle pParticle, final float pPercentageDone, final float pRed, final float pGreen, final float pBlue) {
		pParticle.getEntity().setColor(pRed, pGreen, pBlue);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
