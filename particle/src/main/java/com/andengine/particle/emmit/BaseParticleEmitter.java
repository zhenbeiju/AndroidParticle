package com.andengine.particle.emmit;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 *
 * @author Nicolas Gramlich
 * @since 15:58:12 - 01.10.2010
 */
public abstract class BaseParticleEmitter implements IParticleEmitter {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    protected float mCenterX;
    protected float mCenterY;

    public static final int VERTEX_INDEX_X = 0;
    public static final int VERTEX_INDEX_Y = 1;
    // ===========================================================
    // Constructors
    // ===========================================================

    public BaseParticleEmitter(final float pCenterX, final float pCenterY) {
        this.mCenterX = pCenterX;
        this.mCenterY = pCenterY;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public float getCenterX() {
        return this.mCenterX;
    }

    public float getCenterY() {
        return this.mCenterY;
    }

    public void setCenterX(final float pCenterX) {
        this.mCenterX = pCenterX;
    }

    public void setCenterY(final float pCenterY) {
        this.mCenterY = pCenterY;
    }

    public void setCenter(final float pCenterX, final float pCenterY) {
        this.mCenterX = pCenterX;
        this.mCenterY = pCenterY;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
