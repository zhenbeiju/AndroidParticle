package com.andengine.particle;

import android.graphics.Canvas;

import com.andengine.particle.physics.PhysicsHandler;

/**
 * Created by zhanglin on 16-12-13.
 */

public class Particle {
    protected Entity mEntity;
    private float mLifeTime;
    private float mExpireTime = -1;
    boolean mExpired;

    private PhysicsHandler physicsHandler = new PhysicsHandler();

    void onUpdate(float pSecondsElapsed) {
        mLifeTime += pSecondsElapsed;
        if ((mExpireTime != -1) && mLifeTime >= mExpireTime) {
            mExpired = true;
            return;
        }
        physicsHandler.onUpdate(pSecondsElapsed, mEntity);
    }


    public PhysicsHandler getPhysicsHandler() {
        return physicsHandler;
    }

    public Entity getEntity() {
        return mEntity;
    }

    public float getLifeTime() {
        return mLifeTime;
    }

    public void setExpireTime(float expireTime) {
        this.mExpireTime = expireTime;
    }

    public void setEntity(Entity mEntity) {
        this.mEntity = mEntity;
    }

    public void reset() {
        //TODO
        mLifeTime = 0;
        mExpired = false;
    }

    public void onDraw(Canvas canvas) {
        if (!mExpired) {
            mEntity.draw(canvas);
        }
    }
}
