package live.u14.app.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import live.u14.R;

/**
 * Created by Kevin Dong on 2017/4/12.
 */
public class FabToggle extends ImageButton {

    private int minOffset;

    public FabToggle(Context context) {
        this(context, null);
    }

    public FabToggle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FabToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        minOffset = context.getResources().getDimensionPixelSize(R.dimen.fab_min_offset);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        minOffset -= h / 2;
    }

    public void setOffset(int offset) {
        offset = Math.max(minOffset, offset);
        setTranslationY(offset);
    }
}