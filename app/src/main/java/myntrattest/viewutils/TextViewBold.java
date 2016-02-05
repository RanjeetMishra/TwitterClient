package myntrattest.viewutils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewBold extends TextView{
	
	public TextViewBold(Context context)
	  {
	    super(context);
	    if(!this.isInEditMode())
	    	setTypeface(CustomTypeFace.getTypeFace(context, CustomTypeFace.FontType.BOLD));
	    setPaintFlags(0x1 | (0x1 | getPaintFlags()));
	  }

	  public TextViewBold(Context context, AttributeSet paramAttributeSet)
	  {
	    super(context, paramAttributeSet);
	    if(!this.isInEditMode())
	    	setTypeface(CustomTypeFace.getTypeFace(context, CustomTypeFace.FontType.BOLD));
	    setPaintFlags(0x1 | (0x1 | getPaintFlags()));
	  }

	  public TextViewBold(Context context, AttributeSet paramAttributeSet, int paramInt)
	  {
	    super(context, paramAttributeSet, paramInt);
	    if(!this.isInEditMode())
	    	setTypeface(CustomTypeFace.getTypeFace(context, CustomTypeFace.FontType.BOLD));
	    setPaintFlags(0x1 | (0x1 | getPaintFlags()));
	  }
}
