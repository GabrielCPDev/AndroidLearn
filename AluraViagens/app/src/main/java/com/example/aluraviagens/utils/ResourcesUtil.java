package com.example.aluraviagens.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourcesUtil {

    public static final String DRAWABLE = "drawable";

    public static Drawable devolveDrawable(Context context, String drawableEmTexto) {
        Resources resources = context.getResources();
        int idDoDrawable = resources.getIdentifier(drawableEmTexto, DRAWABLE, context.getPackageName());
        Drawable drawableImagemPacote = resources.getDrawable(idDoDrawable);
        return drawableImagemPacote;
    }

}
