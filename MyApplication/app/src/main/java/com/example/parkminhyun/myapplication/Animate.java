package com.example.parkminhyun.myapplication;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import static com.example.parkminhyun.myapplication.MenuWorldCupActivity.menuLayout;

public class Animate extends Animation implements Animation.AnimationListener {
    public int height, width;

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        // TODO Auto-generated method stub
        super.initialize(width, height, parentWidth, parentHeight);
        this.width = width;
        this.height = height;
        setDuration(100);
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    Camera camera = new Camera();

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        // TODO Auto-generated method stub
        super.applyTransformation(interpolatedTime, t);

        Matrix matrix = t.getMatrix();
        camera.save();

        camera.getMatrix(matrix);
        matrix.setTranslate(-width *interpolatedTime, 0);
        matrix.preTranslate((float) (-width*0.01), 0);
        camera.restore();

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        menuLayout.removeView(menuLayout);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}