package com.andengine.particle;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by zhanglin on 16-12-13.
 */

public class EntityFactory {
    private Bitmap bitmap;
    private Rect src;
    private Rect dst;

    public EntityFactory(Bitmap bitmap) {
        this.bitmap = bitmap;
        src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dst = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    public Entity createEnity(float x, float y) {
        Entity entity = new Entity();
        entity.setPosition(x, y);
        entity.setBitmap(bitmap);
        entity.setSrc(new Rect(src));
        entity.setDst(new Rect(dst));
        return entity;
    }

}
