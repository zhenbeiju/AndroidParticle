package com.andengine.particle.init;


import com.andengine.particle.Particle;

/**
 * (c) Zynga 2011
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 10:03:29 - 19.11.2011
 */
public class ScaleParticleInitializer extends BaseSingleValueParticleInitializer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public ScaleParticleInitializer(final float pScale) {
		super(pScale, pScale);
	}

	public ScaleParticleInitializer(final float pMinScale, final float pMaxScale) {
		super(pMinScale, pMaxScale);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onInitializeParticle(final Particle pParticle, final float pScale) {
		pParticle.getEntity().setScale(pScale);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
