package com.qingqing.base.view.timeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changingedu.dev.uitest.R;

/**
 * Created by Wangxiaxin on 2016/3/2.
 * 
 * 用于列表展示的时间轴项
 * 
 */
public class ItemTimeLine extends RelativeLayout {
    
    private ItemTimeLineSpirit mItemSpiritTitle;
    private ItemTimeLineSpirit mItemSpiritSubTitle;
    private TextView mTVTitle;
    private TextView mTVSubTitle;
    private int mSpiritWidth;
    private int mShowState;
    private int mTitleHeight;
    
    public final static int STATE_BEGIN = 0x0;
    public final static int STATE_ING = 0x1;
    public final static int STATE_END = 0x2;
    
    public ItemTimeLine(Context context) {
        this(context, null);
    }
    
    public ItemTimeLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TimeLineItem);
        
        mSpiritWidth = ta.getDimensionPixelSize(R.styleable.TimeLineItem_spiritWidth,
                getResources().getDimensionPixelSize(R.dimen.dimen_12));
        mShowState = ta.getInt(R.styleable.TimeLineItem_timeState, STATE_ING);
        mTitleHeight = ta.getDimensionPixelSize(R.styleable.TimeLineItem_titleHeight,
                getResources().getDimensionPixelSize(R.dimen.dimen_24));
        ta.recycle();
        
        mItemSpiritTitle = new ItemTimeLineSpirit(context, attrs);
        mItemSpiritTitle.setId(R.id.timeline_spirit);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(mSpiritWidth,
                LayoutParams.WRAP_CONTENT);
        
        param.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        param.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.timeline_title);
        param.addRule(RelativeLayout.ALIGN_TOP, R.id.timeline_title);
        addView(mItemSpiritTitle, param);
        
        mTVTitle = new TextView(context, attrs);
        mTVTitle.setId(R.id.timeline_title);
        mTVTitle.setGravity(Gravity.CENTER_VERTICAL);
        param = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, mTitleHeight);
        // param.addRule(RelativeLayout.CENTER_VERTICAL);
        param.addRule(RelativeLayout.RIGHT_OF, R.id.timeline_spirit);
        addView(mTVTitle, param);
        updateSpiritShow();
    }
    
    private void updateSpiritShow() {
        switch (mShowState) {
            case STATE_BEGIN:
                mItemSpiritTitle.setShowTopLine(false);
                mItemSpiritTitle.setShowBottomLine(true);
                if (mItemSpiritSubTitle != null
                        && mItemSpiritSubTitle.getVisibility() == View.VISIBLE) {
                    mItemSpiritSubTitle.setShowTopLine(true);
                    mItemSpiritSubTitle.setShowBottomLine(true);
                }
                break;
            case STATE_ING:
                mItemSpiritTitle.setShowTopLine(true);
                mItemSpiritTitle.setShowBottomLine(true);
                if (mItemSpiritSubTitle != null
                        && mItemSpiritSubTitle.getVisibility() == View.VISIBLE) {
                    mItemSpiritSubTitle.setShowTopLine(true);
                    mItemSpiritSubTitle.setShowBottomLine(true);
                }
                break;
            case STATE_END:
                mItemSpiritTitle.setShowTopLine(true);
                mItemSpiritTitle.setShowBottomLine(false);
                if (mItemSpiritSubTitle != null
                        && mItemSpiritSubTitle.getVisibility() == View.VISIBLE) {
                    mItemSpiritSubTitle.setShowTopLine(false);
                    mItemSpiritSubTitle.setShowBottomLine(false);
                }
                break;
        }
    }
    
    public TextView getTitle() {
        return mTVTitle;
    }
    
    public ItemTimeLineSpirit getSpirit() {
        return mItemSpiritTitle;
    }
    
    public TextView getSubTitle() {
        return mTVSubTitle;
    }
    
    /**
     * 设置副标题
     * 
     * txt 为null或""可以设为隐藏
     *
     * 如果要进一步设置副标题的显示，请在调用该方法后，调用getSubTitle
     * */
    public ItemTimeLine setSubTitle(CharSequence txt) {
        
        if (TextUtils.isEmpty(txt)) {
            if (mTVSubTitle != null) {
                mTVSubTitle.setVisibility(View.GONE);
            }
            if (mItemSpiritSubTitle != null) {
                mItemSpiritSubTitle.setVisibility(View.GONE);
            }
        }
        else {
            setSubTitle(txt, mTVTitle.getCurrentTextColor(), mTVTitle.getTextSize());
        }
        return this;
        
    }
    
    private ItemTimeLine setSubTitle(CharSequence txt, int textColor, float size) {
        
        if (mTVSubTitle == null) {
            mTVSubTitle = new TextView(getContext());
            mTVSubTitle.setGravity(Gravity.CENTER_VERTICAL);
            mTVSubTitle.setId(R.id.timeline_subtitle);
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            param.addRule(RelativeLayout.ALIGN_LEFT, R.id.timeline_title);
            param.addRule(RelativeLayout.BELOW, R.id.timeline_title);
            addView(mTVSubTitle, param);
        }
        
        if (mItemSpiritSubTitle == null) {
            mItemSpiritSubTitle = new ItemTimeLineSpirit(getContext(), mItemSpiritTitle);
            mItemSpiritSubTitle.setShowSolid(false);
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            param.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.timeline_subtitle);
            param.addRule(RelativeLayout.ALIGN_TOP, R.id.timeline_subtitle);
            param.addRule(RelativeLayout.ALIGN_LEFT, R.id.timeline_spirit);
            param.addRule(RelativeLayout.ALIGN_RIGHT, R.id.timeline_spirit);
            param.addRule(RelativeLayout.BELOW, R.id.timeline_spirit);
            param.addRule(RelativeLayout.LEFT_OF, R.id.timeline_subtitle);
            addView(mItemSpiritSubTitle, param);
        }
        
        mTVSubTitle.setText(txt);
        mTVSubTitle.setTextColor(textColor);
        mTVSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        
        updateSpiritShow();
        return this;
    }
}
