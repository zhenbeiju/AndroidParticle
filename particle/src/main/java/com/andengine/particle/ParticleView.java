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
    private int backgroudColor = 0x00ffffff;
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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(backgroudColor);
        if (isPlaying) {
            synchronized (particleSystems) {
                for (ParticleSystem particleSystem : particleSystems) {
                    particleSystem.onManagedDraw(canvas);
                }
            }
        }
    }

    public void setBackgroudColor(int backgroudColor) {
        this.backgroudColor = backgroudColor;
    }

    public void setAnimationTime(int animationTime) {
        this.animationTime = animationTime;
    }

    public void addParticleSystem(ParticleSystem particleSystem) {
        if (!particleSystems.contains(particleSystem)) {
            synchronized (particleSystems) {
                particleSystems.add(particleSystem);
            }
        }
    }

    public void clearParticleSystem() {
        synchronized (particleSystems) {
            particleSystems.clear();
        }
    }

    public void removeParticleSystem(ParticleSystem particleSystem) {
        synchronized (particleSystems) {
            particleSystems.remove(particleSystem);
        }
    }


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
                if (animationListener != null) {
                    animationListener.onAnimationStart();
                }
                while ((current - startTime) < animationTime) {
                    synchronized (particleSystems) {
                        for (ParticleSystem particleSystem : particleSystems) {
                            particleSystem.onManagedUpdate((current - lastUpdateTime) / 1000f);
                        }
                    }
                    lastUpdateTime = current;
                    postInvalidate();
                    try {
                        long useTime = System.currentTimeMillis() - current;
                        if (useTime < 40) {
                            Thread.sleep(40 - useTime);
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
                if (animationListener != null) {
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
