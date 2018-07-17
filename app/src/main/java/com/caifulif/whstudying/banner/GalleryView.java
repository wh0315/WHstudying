package com.caifulif.whstudying.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * Created by Administrator on 2017/6/20.
 */

public class GalleryView extends Gallery {
    public GalleryView(Context paramContext) {
        super(paramContext);
    }

    public GalleryView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() < 0.0F) {
            onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
        } else {
            onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
        return true;
    }

}
