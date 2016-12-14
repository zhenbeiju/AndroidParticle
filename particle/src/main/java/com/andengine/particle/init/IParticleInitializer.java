package com.andengine.particle.init;

import com.andengine.particle.Particle;

/**
 * Created by zhanglin on 16-12-13.
 */

public interface IParticleInitializer {
    void onInitializeParticle(final Particle pParticle);
}
