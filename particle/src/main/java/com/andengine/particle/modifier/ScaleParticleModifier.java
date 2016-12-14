package com.andengine.particle.modifier;


import com.andengine.particle.Particle;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 20:37:27 - 04.05.2010
 */
public class ScaleParticleModifier extends BaseDoubleValueSpanParticleModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScale, final float pToScale) {
		this(pFromTime, pToTime, pFromScale, pToScale, EaseLinear.getInstance());
	}

	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScale, final float pToScale, final IEaseFunction pEaseFunction) {
		this(pFromTime, pToTime, pFromScale, pToScale, pFromScale, pToScale, pEaseFunction);
	}

	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScaleX, final float pToScaleX, final float pFromScaleY, final float pToScaleY) {
		this(pFromTime, pToTime, pFromScaleX, pToScaleX, pFromScaleY, pToScaleY, EaseLinear.getInstance());
	}

	public ScaleParticleModifier(final float pFromTime, final float pToTime, final float pFromScaleX, final float pToScaleX, final float pFromScaleY, final float pToScaleY, final IEaseFunction pEaseFunction) {
		super(pFromTime, pToTime, pFromScaleX, pToScaleX, pFromScaleY, pToScaleY, pEaseFunction);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onSetInitialValues(final Particle pParticle, final float pScaleX, final float pScaleY) {
		pParticle.getEntity().setScale(pScaleX, pScaleY);
	}

	@Override
	protected void onSetValues(final Particle pParticle, final float pPercentageDone, final float pScaleX, final float pScaleY) {
		pParticle.getEntity().setScale(pScaleX, pScaleY);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
