package com.unity3d.player;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class UnityPlayerLayout extends FrameLayout {
    // The child can be as large as it wants up to the specified size.
    // Magic number (2 * 2560) to limit the max size
    int mMaxWidthMeasure;
    int mMaxHeightMeasure;

    public UnityPlayerLayout(Context context) {
        super(context);

        setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        createMeasures(context);
    }

    private void createMeasures(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mMaxWidthMeasure = MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels - 1, MeasureSpec.AT_MOST);
        mMaxHeightMeasure = MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, MeasureSpec.AT_MOST);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//       super.dispatchDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(mMaxWidthMeasure, mMaxHeightMeasure);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, 0, 0, getMeasuredWidth(), getMeasuredHeight());
    }
}
