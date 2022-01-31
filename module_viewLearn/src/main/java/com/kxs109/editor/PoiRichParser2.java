package com.kxs109.editor;

import android.graphics.Color;

import com.kxs109.viewlearn.R;


/**
 * Created by wupengjian on 17/1/17.
 */
public class PoiRichParser2 extends NormalRichParser {

    public PoiRichParser2() {
        this(null);
    }

    public PoiRichParser2(OnSpannableClickListener listener) {
        super(listener);
    }

    @Override
    protected int getColor() {
        return Color.parseColor("#ff33b5e5");
    }

    @Override
    protected int getDrawableId() {
        return R.mipmap.ic_launcher;
    }

    @Override
    public String getType4Server() {
        return "redirect";
    }

    @Override
    public String getPattern4Server() {
        //\[redirect((\s+\w+=".*?")*)](.+?)\[\/redirect\]
        return "\\[redirect((\\s+\\w+=\".*?\")*)](.+?)\\[\\/redirect\\]";
    }
}