package com.andengine.particle;

import android.graphics.Canvas;

import java.util.ArrayList;

import com.andengine.particle.emmit.BaseParticleEmitter;
import com.andengine.particle.emmit.IParticleEmitter;
import com.andengine.particle.init.IParticleInitializer;
import com.andengine.particle.math.MathUtils;
import com.andengine.particle.modifier.IParticleModifier;

/**
 * Created by zhanglin on 16-12-13.
 */

public class ParticleSystem {
// ===========================================================
    // Constants
    // ===========================================================

    private static final float[] POSITION_OFFSET_CONTAINER = new float[2];

    // ===========================================================
    // Fields
    // ===========================================================

    protected final IParticleEmitter mParticleEmitter;

    protected final Particle[] mParticles;

    protected final ArrayList<IParticleInitializer> mParticleInitializers = new ArrayList<IParticleInitializer>();
    protected final ArrayList<IParticleModifier> mParticleModifiers = new ArrayList<IParticleModifier>();

    private final float mRateMinimum;
    private final float mRateMaximum;

    private boolean mParticlesSpawnEnabled = true;

    protected final int mParticlesMaximum;
    protected int mParticlesAlive;
    private float mParticlesDueToSpawn;
    private EntityFactory entityFactory;

    // ===========================================================
    // Constructors
    // ===========================================================

    @SuppressWarnings("unchecked")
    public ParticleSystem(final EntityFactory factory, final IParticleEmitter pParticleEmitter, final float pRateMinimum, final float pRateMaximum, final int pParticlesMaximum) {
        this.entityFactory = factory;
        this.mParticleEmitter = pParticleEmitter;
        this.mParticles = new Particle[pParticlesMaximum];
        this.mRateMinimum = pRateMinimum;
        this.mRateMaximum = pRateMaximum;
        this.mParticlesMaximum = pParticlesMaximum;

//        this.registerUpdateHandler(this.mParticleEmitter);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public boolean isParticlesSpawnEnabled() {
        return this.mParticlesSpawnEnabled;
    }

    public void setParticlesSpawnEnabled(final boolean pParticlesSpawnEnabled) {
        this.mParticlesSpawnEnabled = pParticlesSpawnEnabled;
    }

    public IParticleEmitter getParticleEmitter() {
        return this.mParticleEmitter;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    public void reset() {
        this.mParticlesDueToSpawn = 0;
        this.mParticlesAlive = 0;
    }

    protected void onManagedDraw(Canvas canvas) {
        for (int i = this.mParticlesAlive - 1; i >= 0; i--) {
            this.mParticles[i].onDraw(canvas);
        }
    }

    protected void onManagedUpdate(final float pSecondsElapsed) {

        if (this.isParticlesSpawnEnabled()) {
            this.spawnParticles(pSecondsElapsed);
        }

        final int particleModifierCountMinusOne = this.mParticleModifiers.size() - 1;
        for (int i = this.mParticlesAlive - 1; i >= 0; i--) {
            final Particle particle = this.mParticles[i];

			/* Apply all particleModifiers */
            for (int j = particleModifierCountMinusOne; j >= 0; j--) {
                this.mParticleModifiers.get(j).onUpdateParticle(particle);
            }

            particle.onUpdate(pSecondsElapsed);
            if (particle.mExpired) {
                this.mParticlesAlive--;
                this.moveParticleToEnd(i);
            }
        }
    }

    protected void moveParticleToEnd(final int pIndex) {
        final Particle particle = this.mParticles[pIndex];

        final int particlesToCopy = this.mParticlesAlive - pIndex;
        if (particlesToCopy > 0) {
            System.arraycopy(this.mParticles, pIndex + 1, this.mParticles, pIndex, particlesToCopy);
        }
        this.mParticles[this.mParticlesAlive] = particle;

		/* This mode of swapping particles is faster than copying tons of array elements,
         * but it doesn't respect the 'lifetime' of the particles. */
//		particles[i] = particles[this.mParticlesAlive];
//		particles[this.mParticlesAlive] = particle;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void addParticleModifier(final IParticleModifier pParticleModifier) {
        this.mParticleModifiers.add(pParticleModifier);
    }

    public void removeParticleModifier(final IParticleModifier pParticleModifier) {
        this.mParticleModifiers.remove(pParticleModifier);
    }

    public void addParticleInitializer(final IParticleInitializer pParticleInitializer) {
        this.mParticleInitializers.add(pParticleInitializer);
    }

    public void removeParticleInitializer(final IParticleInitializer pParticleInitializer) {
        this.mParticleInitializers.remove(pParticleInitializer);
    }

    private void spawnParticles(final float pSecondsElapsed) {
        final float currentRate = this.determineCurrentRate();
        final float newParticlesThisFrame = currentRate * pSecondsElapsed;

        this.mParticlesDueToSpawn += newParticlesThisFrame;

        final int particlesToSpawnThisFrame = Math.min(this.mParticlesMaximum - this.mParticlesAlive, (int) Math.floor(this.mParticlesDueToSpawn));
        this.mParticlesDueToSpawn -= particlesToSpawnThisFrame;

        for (int i = 0; i < particlesToSpawnThisFrame; i++) {
            this.spawnParticle();
        }
    }

    private void spawnParticle() {
        if (this.mParticlesAlive < this.mParticlesMaximum) {
            Particle particle = this.mParticles[this.mParticlesAlive];

			/* New particle needs to be created. */
            this.mParticleEmitter.getPositionOffset(ParticleSystem.POSITION_OFFSET_CONTAINER);

            final float x = ParticleSystem.POSITION_OFFSET_CONTAINER[BaseParticleEmitter.VERTEX_INDEX_X];
            final float y = ParticleSystem.POSITION_OFFSET_CONTAINER[BaseParticleEmitter.VERTEX_INDEX_Y];

            if (particle == null) {
                particle = new Particle();
                this.mParticles[this.mParticlesAlive] = particle;
                Entity entity = entityFactory.createEnity(x, y);
                particle.setEntity(entity);
            } else {
                particle.reset();
                particle.getEntity().setPosition(x, y);
            }

			/* Apply particle initializers. */
            {
                for (int i = this.mParticleInitializers.size() - 1; i >= 0; i--) {
                    this.mParticleInitializers.get(i).onInitializeParticle(particle);
                }

                for (int i = this.mParticleModifiers.size() - 1; i >= 0; i--) {
                    this.mParticleModifiers.get(i).onInitializeParticle(particle);
                }
            }

            this.mParticlesAlive++;
        }
    }

    protected float determineCurrentRate() {
        if (this.mRateMinimum == this.mRateMaximum) {
            return this.mRateMinimum;
        } else {
            return MathUtils.random(this.mRateMinimum, this.mRateMaximum);
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
