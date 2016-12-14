package com.andengine.particle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.andengine.particle.modifier.AlphaParticleModifier;

/**
 * 展示粒子效果
 * Created by zhanglin on 16-12-13.
 */

public class ParticleView extends View {
    private boolean isPlaying;
    private long startTime;
    private long lastUpdateTime;
    private ParticleSystem particleSystem;
    private int animationTime;

    public ParticleView(Context context) {
        super(context);
    }

    public ParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setParticleSystem(ParticleSystem particleSystem) {
        this.particleSystem = particleSystem;
    }

    public void setAnimationTime(int animationTime) {
        this.animationTime = animationTime;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0x00ffffff);
        if (isPlaying) {
            particleSystem.onManagedDraw(canvas);
        }
    }


    /**
     *
     */
    public void startAnimation() {
        if (isPlaying) {
            return;
        }

        isPlaying = true;
        startTime = System.currentTimeMillis();
        lastUpdateTime = startTime;
        new Thread(new Runnable() {
            @Override
            public void run() {
                long current = System.currentTimeMillis();
                while ((current - startTime) < animationTime) {
                    particleSystem.onManagedUpdate((current - lastUpdateTime) / 1000f);
                    lastUpdateTime = current;
                    postInvalidate();
                    try {
                        long useTime = System.currentTimeMillis() - current;
                        if (useTime < 50) {
                            Thread.sleep(50 - useTime);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    current = System.currentTimeMillis();
                }
                isPlaying = false;
                particleSystem.reset();
                postInvalidate();
            }
        }).start();
    }
}
