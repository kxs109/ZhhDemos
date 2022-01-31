package com.kxs109.editor;

import android.util.Pair;

/**
 * Created by wupengjian on 17/1/17.
 */
public interface OnSpannableClickListener {

    void onClick(AbstractRichParser parser, String type, Pair<String, String> content, String sourceStr);
}