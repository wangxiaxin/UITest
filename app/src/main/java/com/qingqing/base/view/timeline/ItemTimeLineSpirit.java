package com.qingqing.base.view.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.changingedu.dev.uitest.R;

/**
 * Created by Wangxiaxin on 2016/3/2.
 *
 * 纵向的时间轴 便签
 *
 * 具体使用时，可以直接使用
 *
 * | o |
 */
public class ItemTimeLineSpirit extends View {
    
    private int mRoundSolidColor;
    private int mLineColor;
    private boolean mNeedShowTop = true;
    private boolean mNeedShowBottom = true;
    private int mGap;
    private int mDefaultMinWidth;
    private int mCircleRadius;
    private Paint mPaint;
    private int mLineWidth;
    private Drawable mRoundForegroundDrawable;
    private boolean mIsInit = true;
    private boolean mShowSolid = true;
    
    public ItemTimeLineSpirit(Context context) {
        this(context, (AttributeSet) null);
    }
    
    public ItemTimeLineSpirit(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public ItemTimeLineSpirit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        int defaultColor = getResources().getColor(R.color.gray);
        
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TimeLineItem);
        mLineColor = ta.getColor(R.styleable.TimeLineItem_momentLineColor, defaultColor);
        mRoundSolidColor = ta.getColor(R.styleable.TimeLineItem_momentSolidColor,
                defaultColor);
        
        mGap = ta.getDimensionPixelSize(R.styleable.TimeLineItem_lineMomentGap,
                getResources().getDimensionPixelSize(R.dimen.dimen_2));
        mDefaultMinWidth = getResources().getDimensionPixelSize(R.dimen.dimen_5);
        setMinimumWidth(mDefaultMinWidth);
        setLineWidth(ta.getDimensionPixelSize(R.styleable.TimeLineItem_lineWidth,
                getResources().getDimensionPixelSize(R.dimen.dimen_1)));
        
        setRadius(ta.getDimensionPixelSize(R.styleable.TimeLineItem_momentRadius, 0));
        
        setShowTopLine(ta.getBoolean(R.styleable.TimeLineItem_showTopLine, true));
        setShowBottomLine(ta.getBoolean(R.styleable.TimeLineItem_showBottomLine, true));
        
        setForegroundRes(ta
                .getResourceId(R.styleable.TimeLineItem_momentForegroundRes, 0));
        
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(mLineWidth);
        
        ta.recycle();
        
        mIsInit = false;
    }
    
    public ItemTimeLineSpirit(Context context, ItemTimeLineSpirit spirit) {
        this(context);
        
        mIsInit = true;
        mLineColor = spirit.mLineColor;
        mRoundSolidColor = spirit.mRoundSolidColor;
        mGap = spirit.mGap;
        mDefaultMinWidth = spirit.mDefaultMinWidth;
        setMinimumWidth(mDefaultMinWidth);
        setLineWidth(spirit.mLineWidth);
        setRadius(spirit.mCircleRadius);
        setShowTopLine(spirit.mNeedShowTop);
        setShowBottomLine(spirit.mNeedShowBottom);
        mPaint.setStrokeWidth(mLineWidth);
        mIsInit = false;
    }
    
    public ItemTimeLineSpirit setSolidColor(int color) {
        mRoundSolidColor = color;
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public ItemTimeLineSpirit setLineColor(int color) {
        mLineColor = color;
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public ItemTimeLineSpirit setLineWidth(int width) {
        mLineWidth = width;
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public int getLineWidth() {
        return mLineWidth;
    }
    
    public ItemTimeLineSpirit setForegroundRes(int resid) {
        try {
            mRoundForegroundDrawable = getResources().getDrawable(resid);
        } catch (Exception e) {
            mRoundForegroundDrawable = null;
        }
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public ItemTimeLineSpirit setGap(int gap) {
        mGap = gap;
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public ItemTimeLineSpirit setRadius(int radius) {
        mCircleRadius = radius;
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public ItemTimeLineSpirit setShowTopLine(boolean show) {
        mNeedShowTop = show;
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public ItemTimeLineSpirit setShowBottomLine(boolean show) {
        mNeedShowBottom = show;
        if (!mIsInit)
            postInvalidate();
        return this;
    }
    
    public ItemTimeLineSpirit setShowSolid(boolean ifShow) {
        mShowSolid = ifShow;
        return this;
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        
        int width, height;
        
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        else {
            width = mDefaultMinWidth;
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }
        
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }
        else {
            height = (int) (width * 1.7f) + (mGap << 1);
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }
        
        setMeasuredDimension(width, height);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.save();
        
        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();
        
        final int halfWidth = width >> 1;
        final int halfHeight = height >> 1;
        
        if (!mShowSolid) {
            mPaint.setColor(mLineColor);
            if (mNeedShowTop) {
                // 绘制上半部分竖线
                canvas.drawLine(halfWidth, 0, halfWidth, halfHeight, mPaint);
            }
            
            if (mNeedShowBottom) {
                // 绘制下半部分竖线
                canvas.drawLine(halfWidth, halfHeight, halfWidth, height, mPaint);
            }
        }
        else {
            // 绘制中间的圆圈
            if (mCircleRadius <= 0) {
                mCircleRadius = (halfWidth >> 1);
            }
            int radius = mCircleRadius;
            if (mRoundForegroundDrawable != null) {
                radius = Math.max(mCircleRadius,
                        (mRoundForegroundDrawable.getIntrinsicWidth() >> 1) + 4);
            }
            
            radius = Math.min(radius, halfWidth - 4);
            mPaint.setColor(mRoundSolidColor);
            canvas.drawCircle(halfWidth, halfHeight, radius, mPaint);
            
            if (mRoundForegroundDrawable != null) {
                final int resWidth = mRoundForegroundDrawable.getIntrinsicWidth();
                final int resHeight = mRoundForegroundDrawable.getIntrinsicHeight();
                mRoundForegroundDrawable.setBounds(0, 0, resWidth, resHeight);
                canvas.save();
                canvas.translate(halfWidth - (resWidth >> 1), halfHeight
                        - (resHeight >> 1));
                mRoundForegroundDrawable.draw(canvas);
                canvas.restore();
            }
            
            final int lineYStop1 = halfHeight - radius - mGap;
            
            mPaint.setColor(mLineColor);
            if (mNeedShowTop) {
                // 绘制上半部分竖线
                canvas.drawLine(halfWidth, 0, halfWidth, lineYStop1, mPaint);
            }
            
            if (mNeedShowBottom) {
                // 绘制下半部分竖线
                canvas.drawLine(halfWidth, halfHeight + radius + mGap, halfWidth, height,
                        mPaint);
            }
        }
        
        canvas.restore();
    }
}
