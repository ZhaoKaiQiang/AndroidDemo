package com.socks.androiddemo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

public class ConfirmView extends View {

    //绘制√和×及快速旋转弧形的动画时间
    private static final long NORMAL_ANIMATION_DURATION = 350L;
    //绘制正常弧形的动画时间
    private static final long NORMAL_ANGLE_ANIMATION_DURATION = 1000L;
    //绘制偏移角度的动画时间
    private static final long NORMAL_CIRCLE_ANIMATION_DURATION = 2000L;
    //绘制×需要的path数量
    private static final int PATH_SIZE_TWO = 2;

    public static final int STROKEN_WIDTH = 20;

    private int[] colors = {0xFF0099CC, 0xFF99CC00, 0xFFCC0099, 0xFFCC9900, 0xFF9900CC, 0xFF00CC99};
    private int colorCursor = 0;

    private State mCurrentState = State.Success;

    private Path mSuccessPath;
    private PathMeasure mPathMeasure;
    private ArrayList<Path> mRenderPaths;
    private Paint mPaint;

    private int mCenterX;
    private int mCenterY;

    private int mRadius;
    private int mSignRadius;
    private float mPhare;
    private float mStartAngle;
    private float mEndAngle;
    private float mCircleAngle;
    private RectF oval;

    private ValueAnimator mPhareAnimator;
    private ValueAnimator mStartAngleAnimator;
    private ValueAnimator mEndAngleAnimator;
    private ValueAnimator mCircleAnimator;

    public ConfirmView(Context context) {
        this(context, null);
    }

    public ConfirmView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConfirmView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mSuccessPath = new Path();
        mPathMeasure = new PathMeasure(mSuccessPath, false);
        mRenderPaths = new ArrayList<>();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xFF0099CC);
        mPaint.setStrokeWidth(STROKEN_WIDTH);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        oval = new RectF();
    }


    private void setPhare(float phare) {
        mPhare = phare;
        updatePhare();
        invalidate();
    }

    private void setStartAngle(float startAngle) {
        this.mStartAngle = startAngle;
        invalidate();
    }

    private void setEndAngle(float endAngle) {
        this.mEndAngle = endAngle;
        invalidate();
    }

    private void setCircleAngle(float circleAngle) {
        this.mCircleAngle = circleAngle;
        invalidate();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Phare Animation
    ///////////////////////////////////////////////////////////////////////////

    public void startPhareAnimation() {
        if (mPhareAnimator == null) {
            mPhareAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);
            mPhareAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (Float) animation.getAnimatedValue();
                    setPhare(value);
                }
            });

            mPhareAnimator.setDuration(NORMAL_ANIMATION_DURATION);
            mPhareAnimator.setInterpolator(new LinearInterpolator());
        }
        mPhare = 0;
        mPhareAnimator.start();
    }

    public void stopPhareAnimation() {
        if (mPhareAnimator != null) {
            mPhareAnimator.end();
        }
    }

    private void updatePhare() {

        if (mSuccessPath != null) {

            switch (mCurrentState) {
                case Success: {
                    if (mPathMeasure.getSegment(0, mPhare * mPathMeasure.getLength(), mRenderPaths.get(0), true)) {
                        mRenderPaths.get(0).rLineTo(0, 0);
                    }
                }
                break;
                case Fail: {
                    //i = 0,画一半，i=1,画另一半
                    float seg = 1.0F / PATH_SIZE_TWO;

                    for (int i = 0; i < PATH_SIZE_TWO; i++) {
                        float offset = mPhare - seg * i;
                        offset = offset < 0 ? 0 : offset;
                        offset *= PATH_SIZE_TWO;
                        Log.d("i:" + i + ",seg:" + seg, "offset:" + offset + ", mPhare:" + mPhare + ", size:" + PATH_SIZE_TWO);
                        boolean success = mPathMeasure.getSegment(0, offset * mPathMeasure.getLength(), mRenderPaths.get(i), true);

                        if (success) {
                            mRenderPaths.get(i).rLineTo(0, 0);
                        }

                        mPathMeasure.nextContour();
                    }
                    mPathMeasure.setPath(mSuccessPath, false);
                }
                break;
            }

        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Progressing Circle
    ///////////////////////////////////////////////////////////////////////////

    public void startCircleAnimation() {
        if (mCircleAnimator == null || mStartAngleAnimator == null || mEndAngleAnimator == null) {
            initAngleAnimation();
        }

        mStartAngleAnimator.setDuration(NORMAL_ANGLE_ANIMATION_DURATION);
        mEndAngleAnimator.setDuration(NORMAL_ANGLE_ANIMATION_DURATION);
        mCircleAnimator.setDuration(NORMAL_CIRCLE_ANIMATION_DURATION);
        mStartAngleAnimator.start();
        mEndAngleAnimator.start();
        mCircleAnimator.start();
    }


    private void initAngleAnimation() {

        mStartAngleAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);
        mEndAngleAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);
        mCircleAnimator = ValueAnimator.ofFloat(0.0F, 1.0F);

        mStartAngleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setStartAngle(value);
            }
        });
        mEndAngleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setEndAngle(value);
            }
        });

        mStartAngleAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                if (mCurrentState == State.Progressing) {
                    if (mEndAngleAnimator != null) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mEndAngleAnimator.start();
                            }
                        }, 400L);
                    }
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mCurrentState != State.Progressing && mEndAngleAnimator != null && !mEndAngleAnimator.isRunning() && !mEndAngleAnimator.isStarted()) {
                    startPhareAnimation();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        mEndAngleAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (mStartAngleAnimator != null) {
                    if (mCurrentState != State.Progressing) {
                        mStartAngleAnimator.setDuration(NORMAL_ANIMATION_DURATION);
                    }
                    colorCursor++;
                    if (colorCursor >= colors.length) colorCursor = 0;
                    mPaint.setColor(colors[colorCursor]);
                    mStartAngleAnimator.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        mCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setCircleAngle(value);
            }
        });


        mStartAngleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mEndAngleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());


        mCircleAnimator.setInterpolator(new LinearInterpolator());
        mCircleAnimator.setRepeatCount(-1);
    }

    public void animatedWithState(State state) {

        if (mCurrentState != state) {
            mCurrentState = state;
            if (mPhareAnimator != null && mPhareAnimator.isRunning()) {
                stopPhareAnimation();
            }
            switch (state) {
                case Fail:
                case Success:
                    updatePath();
                    if (mCircleAnimator != null && mCircleAnimator.isRunning()) {
                        mCircleAngle = (Float) mCircleAnimator.getAnimatedValue();
                        mCircleAnimator.end();
                    }

                    if ((mStartAngleAnimator == null || !mStartAngleAnimator.isRunning() || !mStartAngleAnimator.isStarted()) &&
                            (mEndAngleAnimator == null || !mEndAngleAnimator.isRunning() || !mEndAngleAnimator.isStarted())) {
                        mStartAngle = 360;
                        mEndAngle = 0;
                        startPhareAnimation();
                    }

                    break;
                case Progressing:
                    mCircleAngle = 0;
                    startCircleAnimation();
                    break;
            }
        }

    }

    private void updatePath() {

        int offset = (int) (mSignRadius * 0.15F);
        mRenderPaths.clear();

        switch (mCurrentState) {
            case Success:
                mSuccessPath.reset();
                mSuccessPath.moveTo(mCenterX - mSignRadius, mCenterY + offset);
                mSuccessPath.lineTo(mCenterX - offset, mCenterY + mSignRadius - offset);
                mSuccessPath.lineTo(mCenterX + mSignRadius, mCenterY - mSignRadius + offset);
                mRenderPaths.add(new Path());
                break;
            case Fail:
                mSuccessPath.reset();
                float failRadius = mSignRadius * 0.8F;
                mSuccessPath.moveTo(mCenterX - failRadius, mCenterY - failRadius);
                mSuccessPath.lineTo(mCenterX + failRadius, mCenterY + failRadius);
                mSuccessPath.moveTo(mCenterX + failRadius, mCenterY - failRadius);
                mSuccessPath.lineTo(mCenterX - failRadius, mCenterY + failRadius);
                for (int i = 0; i < PATH_SIZE_TWO; i++) {
                    mRenderPaths.add(new Path());
                }
                break;
            default:
                mSuccessPath.reset();
        }

        mPathMeasure.setPath(mSuccessPath, false);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = w / 2;
        mCenterY = h / 2;

        mRadius = mCenterX > mCenterY ? mCenterY : mCenterX;
        mSignRadius = (int) (mRadius * 0.55F);

        int realRadius = mRadius - (STROKEN_WIDTH / 2);

        oval.left = mCenterX - realRadius;
        oval.top = mCenterY - realRadius;
        oval.right = mCenterX + realRadius;
        oval.bottom = mCenterY + realRadius;

        updatePath();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (mCurrentState) {
            case Fail:
                for (int i = 0; i < PATH_SIZE_TWO; i++) {
                    Path p = mRenderPaths.get(i);
                    if (p != null) {
                        canvas.drawPath(p, mPaint);
                    }
                }
                drawCircle(canvas);
                break;
            case Success:
                Path p = mRenderPaths.get(0);
                if (p != null) {
                    canvas.drawPath(p, mPaint);
                }
                drawCircle(canvas);
                break;
            case Progressing:
                drawCircle(canvas);
                break;
        }

    }

    private void drawCircle(Canvas canvas) {
        float offsetAngle = mCircleAngle * 360;
        float startAngle = mEndAngle * 360;
        float sweepAngle = mStartAngle * 360;

        if (startAngle == 360)
            startAngle = 0;
        sweepAngle = sweepAngle - startAngle;
        startAngle += offsetAngle;

        if (sweepAngle < 0)
            sweepAngle = 1;

        canvas.drawArc(oval, startAngle, sweepAngle, false, mPaint);
    }

    public enum State {
        Success, Fail, Progressing
    }
}
