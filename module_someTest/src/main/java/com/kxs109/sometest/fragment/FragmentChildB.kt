package com.kxs109.sometest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kxs109.commonlib.config.utils.ext.logE
import com.kxs109.sometest.R

class FragmentChildB:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.some_test_fragment_child_a,container,false)
    }

    override fun onResume() {
        (this.javaClass.simpleName+"--onResume").logE()
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (this.javaClass.simpleName+"--onCreate").logE()

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (this.javaClass.simpleName+"--onViewCreated").logE()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (this.javaClass.simpleName+"--onActivityCreated").logE()

        super.onActivityCreated(savedInstanceState)
    }
    override fun onDestroy() {
        (this.javaClass.simpleName+"--onDestroy").logE()

        super.onDestroy()
    }

    override fun onDestroyView() {
        (this.javaClass.simpleName+"--onDestroyView").logE()

        super.onDestroyView()
    }
}