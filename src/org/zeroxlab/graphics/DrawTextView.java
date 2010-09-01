/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.zeroxlab.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;


class DrawTextView extends SurfaceView {

    public final String TEXT1 = "0xbench";
    public final String TEXT2 = "0xlab";

    class PaintText {
        public int x;
        public int y;
        public Paint paint;
        public boolean text;
        PaintText(Paint paint, int x, int y, boolean text) {
            this.paint = paint;
            this.x = x;
            this.y = y;
            this.text = text;
        }
    }

    private SurfaceHolder mSurfaceHolder;
    private ArrayList<PaintText> rectengleList = new ArrayList<PaintText>();

    protected void doDraw() {
        Canvas canvas = mSurfaceHolder.lockCanvas();
        generateNewText(canvas);
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void generateNewText(Canvas canvas) {
        Random mRandom = new Random();
        int height = getHeight();
        int width  = getWidth();

        int cx = (int)((mRandom.nextInt() % (width*0.8) ) + (width*0.1));
        int cy = (int)((mRandom.nextInt() % (height*0.8) ) + (height*0.1));

        int color = (0x00353535 | mRandom.nextInt() ) | Color.BLACK; 
        Paint p = new Paint();
        p.setAntiAlias(false);
        p.setStyle(Paint.Style.FILL);
        p.setTextAlign(Paint.Align.CENTER);

        if(mRandom.nextInt()%2 == 0)
            p.setFakeBoldText(true);

        if(mRandom.nextInt()%2 == 0)
            p.setTextSkewX((float)-0.35);

        p.setColor(color);
        p.setTextSize(32 + (mRandom.nextInt()%24));

        if(mRandom.nextInt()%2 == 0)
            canvas.drawText(TEXT1, cx, cy, p);
        else
            canvas.drawText(TEXT2, cx, cy, p);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
    }
}

