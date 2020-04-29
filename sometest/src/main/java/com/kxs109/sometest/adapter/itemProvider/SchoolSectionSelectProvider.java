package com.kxs109.sometest.adapter.itemProvider;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kxs109.sometest.R;
import com.kxs109.sometest.model.SchoolSection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SchoolSectionSelectProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_school_section_select_head;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @Nullable BaseNode data) {
        SchoolSection entity = (SchoolSection) data;
        assert entity != null;
        helper.setText(R.id.tv_title, entity.getTitle());
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        getAdapter().expandOrCollapse(position);
    }
}
