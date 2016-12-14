package com.andengine.particle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by zhanglin on 16-12-13.
 */

public class Entity {
    protected boolean mVisible = true;

    protected float mX;
    protected float mY;
    protected float mRotation;

    protected float mScaleX = 1;
    protected float mScaleY = 1;

    protected float mScaleCenterX = 0;
    protected float mScaleCenterY = 0;

    protected float mSkewCenterX = 0;
    protected float mSkewCenterY = 0;


    protected float mAlpha = 1f;
    protected float mRed = 255;
    protected float mBlue = 255;
    protected float mGreen = 255;

    private Bitmap bitmap;
    private Rect src;
    private Rect dst;
    private Paint paint = new Paint();

    public Entity() {

    }

    public Entity(float x, float y) {
        setPosition(x, y);

    }


    public boolean isVisible() {
        return mVisible;
    }

    public void setPosition(float x, float y) {
        this.mX = x;
        this.mY = y;
    }


    public void setVisible(boolean mVisible) {
        this.mVisible = mVisible;
    }

    public float getX() {
        return mX;
    }

    public void setX(float mX) {
        this.mX = mX;
    }

    public float getY() {
        return mY;
    }

    public void setY(float mY) {
        this.mY = mY;
    }

    public float getRotation() {
        return mRotation;
    }

    public void setRotation(float mRotation) {
        this.mRotation = mRotation;
    }

    public float getScaleX() {
        return mScaleX;
    }

    public void setScaleX(float mScaleX) {
        this.mScaleX = mScaleX;
    }

    public float getScaleY() {
        return mScaleY;
    }

    public void setScaleY(float mScaleY) {
        this.mScaleY = mScaleY;
    }

    public float getScaleCenterX() {
        return mScaleCenterX;
    }

    public void setScaleCenterX(float mScaleCenterX) {
        this.mScaleCenterX = mScaleCenterX;
    }

    public float getScaleCenterY() {
        return mScaleCenterY;
    }

    public void setScaleCenterY(float mScaleCenterY) {
        this.mScaleCenterY = mScaleCenterY;
    }

    public float getSkewCenterX() {
        return mSkewCenterX;
    }

    public void setSkewCenterX(float mSkewCenterX) {
        this.mSkewCenterX = mSkewCenterX;
    }

    public float getSkewCenterY() {
        return mSkewCenterY;
    }

    public void setSkewCenterY(float mSkewCenterY) {
        this.mSkewCenterY = mSkewCenterY;
    }

    public float getAlpha() {
        return mAlpha;
    }

    public void setAlpha(float mAlpha) {
        this.mAlpha = mAlpha;
    }

    public float getRed() {
        return mRed;
    }

    public void setRed(float mRed) {
        this.mRed = mRed;
    }

    public float getBlue() {
        return mBlue;
    }

    public void setBlue(float mBlue) {
        this.mBlue = mBlue;
    }

    public float getGreen() {
        return mGreen;
    }

    public void setGreen(float mGreen) {
        this.mGreen = mGreen;
    }

    public void setScale(float scale) {
        this.mScaleX = scale;
        this.mScaleY = scale;
    }

    public void setScale(float scaleX, float scaleY) {
        this.mScaleX = scaleX;
        this.mScaleY = scaleY;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setSrc(Rect src) {
        this.src = src;
    }

    public void setDst(Rect dst) {
        this.dst = dst;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setColor(float pRed, float pGreen, float pBlue) {
        this.mBlue = pBlue;
        this.mRed = pRed;
        this.mGreen = pGreen;
    }


    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(mX, mY);
        Matrix matrix = new Matrix();
        matrix.postScale(mScaleX, mScaleY);
        matrix.postRotate(mRotation);
//        canvas.setMatrix(matrix);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                mRed / 255, 0, 0, 0, 0,
                0, mGreen / 255, 0, 0, 0,
                0, 0, mBlue / 255, 0, 0,
                0, 0, 0, mAlpha, 0,
        });
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, matrix, paint);
        canvas.restore();

    }

}

