package com.kxs109.editor;

import android.graphics.Color;

import com.kxs109.viewlearn.R;


/**
 * Created by wupengjian on 17/1/17.
 */
public class PoiRichParser extends NormalRichParser {

    public PoiRichParser() {
        this(null);
    }

    public PoiRichParser(OnSpannableClickListener listener) {
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
        return "位置";
    }
}