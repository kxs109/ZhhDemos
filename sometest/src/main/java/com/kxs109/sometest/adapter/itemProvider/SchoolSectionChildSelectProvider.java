package com.kxs109.sometest.adapter.itemProvider;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kxs109.sometest.R;
import com.kxs109.sometest.model.SchoolSectionChild;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SchoolSectionChildSelectProvider extends BaseNodeProvider {
    private int mSelectPosition = -1;

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_school_section_select_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @Nullable BaseNode data) {
        if (data == null) {
            return;
        }
        SchoolSectionChild entity = (SchoolSectionChild) data;
        helper.setText(R.id.tv_name, entity.getName());
        if (mSelectPosition == helper.getLayoutPosition()) {
            helper.setBackgroundResource(R.id.tv_name, R.drawable.drawable_button_theme1_p1_bg_down);
            helper.setTextColor(R.id.tv_name,context.getResources().getColor(R.color.white));
        } else {
            helper.setBackgroundResource(R.id.tv_name,0);
            helper.setTextColor(R.id.tv_name,context.getResources().getColor(R.color.color_text_main));

        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        mSelectPosition = position;
        Objects.requireNonNull(this.getAdapter()).notifyDataSetChanged();
    }
}
