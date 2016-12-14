package com.andengine.particle.modifier;

import com.andengine.particle.Particle;
import com.andengine.particle.init.IParticleInitializer;

/**
 * Created by zhanglin on 16-12-13.
 */

public interface IParticleModifier extends IParticleInitializer{
    void onUpdateParticle(final Particle pParticle);
}
