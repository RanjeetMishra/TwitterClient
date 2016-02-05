package myntrattest.viewutils;

/**
 * Created by ranjeetmishra on 03/02/16.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewRegular extends TextView{

    public TextViewRegular(Context context)
    {
        super(context);
        if(!this.isInEditMode())
            setTypeface(CustomTypeFace.getTypeFace(context, CustomTypeFace.FontType.REGULAR));
        setPaintFlags(0x1 | (0x1 | getPaintFlags()));
    }

    public TextViewRegular(Context context, AttributeSet paramAttributeSet)
    {
        super(context, paramAttributeSet);
        if(!this.isInEditMode())
            setTypeface(CustomTypeFace.getTypeFace(context, CustomTypeFace.FontType.REGULAR));
        setPaintFlags(0x1 | (0x1 | getPaintFlags()));
    }

    public TextViewRegular(Context context, AttributeSet paramAttributeSet, int paramInt)
    {
        super(context, paramAttributeSet, paramInt);
        if(!this.isInEditMode())
            setTypeface(CustomTypeFace.getTypeFace(context, CustomTypeFace.FontType.REGULAR));
        setPaintFlags(0x1 | (0x1 | getPaintFlags()));
    }

}

