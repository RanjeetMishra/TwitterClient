package myntrattest.viewutils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by ranjeetmishra on 03/02/16.
 */
public class CustomTypeFace {

    public static enum FontType {
        REGULAR("fonts/OpenSans-Regular.ttf"),
        BOLD("fonts/OpenSans-Semibold.ttf"),
        ICON("fonts/pnpfontsregular.ttf");

        public String fontFilePath;
        public Typeface typeFace = null;
        private FontType(String path) {
            fontFilePath = path;
        }
    }

    public static Typeface getTypeFace(Context context, FontType fontType) {
        switch(fontType) {
            case REGULAR:
                if(fontType.typeFace==null) {
                    fontType.typeFace = Typeface.createFromAsset(context.getAssets(), fontType.fontFilePath);
                }
                break;
            case BOLD:
                if(fontType.typeFace==null) {
                    fontType.typeFace = Typeface.createFromAsset(context.getAssets(), fontType.fontFilePath);
                }
                break;
            case ICON:
                if(fontType.typeFace==null) {
                    fontType.typeFace = Typeface.createFromAsset(context.getAssets(), fontType.fontFilePath);
                }
                break;
        }
        return fontType.typeFace;
    }


}

