package myntrattest.viewutils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class CustomEditText extends EditText{

	 public KeyBackListener keyBackListener;
	 
	 public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	 
	    }
	 
	    public CustomEditText(Context context, AttributeSet attrs) {
	        super(context, attrs);
	 
	    }
	 
	    public CustomEditText(Context context) {
	        super(context);
	 
	    }
	    
	    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
	        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	            if(keyBackListener!=null) {
	            	keyBackListener.onKeyDownPressed();
	            }
	        }
	        return super.onKeyPreIme(keyCode, event);
	    }
	    
	    @Override
	    protected void onTextChanged(CharSequence text, int start,
	    		int lengthBefore, int lengthAfter) {
	    	/*if(lengthBefore==0 && lengthAfter==1) {
	    		keyBackListener.onFirstCharacter();
	    	}else if(lengthBefore==1 && lengthAfter==0) {
	    		keyBackListener.onClearText();
	    	}*/
	    	super.onTextChanged(text, start, lengthBefore, lengthAfter);
	    	
	    }
}
