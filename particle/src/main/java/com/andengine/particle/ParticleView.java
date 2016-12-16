package com.andengine.particle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.andengine.particle.modifier.AlphaParticleModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示粒子效果
 * Created by zhanglin on 16-12-13.
 */

public class ParticleView extends View {
    private boolean isPlaying;
    private long startTime;
    private long lastUpdateTime;
    private int animationTime;
    private List<ParticleSystem> particleSystems = new ArrayList<>();
    private IAnimationListener animationListener;

    public ParticleView(Context context) {
        super(context);
    }

    public ParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addParticleSystem(ParticleSystem particleSystem) {
        particleSystems.add(particleSystem);
    }

    public void setAnimationTime(int animationTime) {
        this.animationTime = animationTime;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0x00ffffff);
        if (isPlaying) {
            for (ParticleSystem particleSystem : particleSystems) {
                particleSystem.onManagedDraw(canvas);
            }
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
                if(animationListener!=null){
                    animationListener.onAnimationStart();
                }
                while ((current - startTime) < animationTime) {
                    for (ParticleSystem particleSystem : particleSystems) {
                        particleSystem.onManagedUpdate((current - lastUpdateTime) / 1000f);
                    }
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
                for (ParticleSystem particleSystem : particleSystems) {
                    particleSystem.reset();
                }
                postInvalidate();
                if(animationListener!=null){
                    animationListener.onAnimationEnd();
                }
            }
        }).start();
    }

    public void setAnimationListener(IAnimationListener listener) {
        this.animationListener = listener;
    }


    public interface IAnimationListener {
        void onAnimationStart();

        void onAnimationEnd();
    }
}
