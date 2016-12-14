package com.andengine.particle.init;


import com.andengine.particle.Particle;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 18:53:41 - 02.10.2010
 */
public class AlphaParticleInitializer extends BaseSingleValueParticleInitializer {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public AlphaParticleInitializer(final float pAlpha) {
        super(pAlpha, pAlpha);
    }

    public AlphaParticleInitializer(final float pMinAlpha, final float pMaxAlpha) {
        super(pMinAlpha, pMaxAlpha);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onInitializeParticle(final Particle pParticle, final float pAlpha) {
        pParticle.getEntity().setAlpha(pAlpha);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
