package live.u14.app.views.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import live.u14.app.animation.RevealViewGroup;
import live.u14.app.animation.ViewRevealManager;

/**
 * Created by Kevin Dong on 2017/4/12.
 */
public class RevealLinearLayout extends LinearLayout implements RevealViewGroup {
    private ViewRevealManager manager;

    public RevealLinearLayout(Context context) {
        this(context, null);
    }

    public RevealLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RevealLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        manager = new ViewRevealManager();
    }

    @Override protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        try {
            canvas.save();

            manager.transform(canvas, child);
            return super.drawChild(canvas, child, drawingTime);
        } finally {
            canvas.restore();
        }
    }

    public void setViewRevealManager(ViewRevealManager manager) {
        if (manager == null) {
            throw new NullPointerException("ViewRevealManager is null");
        }

        this.manager = manager;
    }

    @Override public ViewRevealManager getViewRevealManager() {
        return manager;
    }
}