package online.u148.common.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class HighlightView extends View implements OnTouchListener
{
	public static final String TAG = "HighlightView";
	protected List<View> 	blanks;
	protected List<View>	circleBlanks;
	protected Rect			selfRc;
	
	public HighlightView(Context context)
	{
		super(context);
		init();
	}
	
	public HighlightView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}
	
	public HighlightView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init();
	}
	
	protected void init()
	{
		blanks = new ArrayList<>();
		circleBlanks = new ArrayList<>();
		selfRc = new Rect();
		setClickable(false);
		if(Integer.parseInt(VERSION.SDK) > 10)
		{
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
	}
	
	public void setClickable(boolean clickable)
	{
		super.setClickable(clickable);
		if(clickable)
		{
			setOnTouchListener(this);
		}
		else
		{
			setOnTouchListener(null);
		}
	}
	
	public void destroy()
	{
		if(blanks != null)
		{
			for(View v : blanks)
			{
				v.setTag(null);
			}
			blanks.clear();
			blanks = null;
		}
		if(circleBlanks != null)
		{
			for(View v : circleBlanks)
			{
				v.setTag(null);
			}
			circleBlanks.clear();
			circleBlanks = null;
		}
		selfRc = null;
	}
	
	public void appendBlankRect(View v)
	{
		if(v != null && !blanks.contains(v))
		{
			v.setTag(new Rect());
			blanks.add(v);
		}
	}
	
	public void appendBlankCircle(View v)
	{
		if(v != null && !circleBlanks.contains(v))
		{
			v.setTag(new Holder());
			circleBlanks.add(v);
		}
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		getGlobalVisibleRect(selfRc);
		canvas.clipRect(0, 0, getWidth(), getHeight());
		if(blanks != null)
		{
			for(View v : blanks)
			{
				Rect rc = (Rect)v.getTag();
				if(v.getGlobalVisibleRect(rc))
				{
					rc.left -= selfRc.left;
					rc.right -= selfRc.left;
					rc.top -= selfRc.top;
					rc.bottom -= selfRc.top;
					canvas.clipRect(rc, Region.Op.DIFFERENCE);
				}
			}
		}
		if(circleBlanks != null)
		{
			for(View v : circleBlanks)
			{
				Holder holder = (Holder)v.getTag();
				if(v.getGlobalVisibleRect(holder.rc))
				{
					int radius = holder.rc.width() / 2;
					holder.path.reset();
					holder.path.addCircle(radius, radius, radius, Direction.CW);
					holder.rc.left -= selfRc.left;
					holder.rc.top -= selfRc.top;
					canvas.translate(holder.rc.left, holder.rc.top);
					canvas.clipPath(holder.path, Region.Op.DIFFERENCE);
				}
			}
		}
		canvas.drawARGB(178, 0, 0, 0);
	}

	@Override
	public boolean onTouch(View v, MotionEvent evt)
	{
		if(evt.getAction() == MotionEvent.ACTION_UP
			|| evt.getAction() == MotionEvent.ACTION_DOWN
			|| evt.getAction() == MotionEvent.ACTION_MOVE)
		{
			int x = (int)evt.getX();
			int y = (int)evt.getY();
			for(View view : blanks)
			{
				Rect rc = (Rect)view.getTag();
				if(x >= rc.left && x <= rc.right
					&& y >= rc.top && y <= rc.bottom)
				{
					if(evt.getAction() != MotionEvent.ACTION_MOVE)
					{
						view.dispatchTouchEvent(evt);
					}
					break;
				}
				else if(evt.getAction() == MotionEvent.ACTION_MOVE)
				{
					view.dispatchTouchEvent(evt);
				}
			}
		}
		return true;
	}
	
	protected class Holder
	{
		public Rect rc;
		public Path path;
		
		public Holder()
		{
			rc = new Rect();
			path = new Path();
		}
	}
}