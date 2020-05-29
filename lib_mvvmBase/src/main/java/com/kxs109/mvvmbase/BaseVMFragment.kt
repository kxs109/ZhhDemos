package com.kxs109.mvvmbase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseVMFragment<VM : BaseViewModel>(useDataBinding: Boolean = true) : Fragment() {
    private var mUseDataBinding = useDataBinding
    private lateinit var mViewModel: VM
    private lateinit var mBinding: ViewDataBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (mUseDataBinding) {
            mBinding =
                DataBindingUtil.inflate<ViewDataBinding>(inflater, getResLayout(), container, false)

            mBinding.root
        } else {
            inflater.inflate(getResLayout(), container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = initViewModel()
        if (mUseDataBinding){
            mBinding.lifecycleOwner=this
        }
        observe()
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initViewModel(): VM

    abstract fun initData()

    abstract fun initView()

    abstract fun observe()

    abstract fun getResLayout(): Int
}