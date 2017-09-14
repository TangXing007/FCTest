package hml.come.fucheng.appApplication;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TX on 2017/8/29.
 */

public  final class FontsOverride {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        //android 5.0及以上我们反射修改Typeface.sSystemFontMap变量
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<>();
            newMap.put(staticTypefaceFieldName, newTypeface);
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField(staticTypefaceFieldName);
                staticField.setAccessible(true);
                staticField.set(null, newTypeface);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
