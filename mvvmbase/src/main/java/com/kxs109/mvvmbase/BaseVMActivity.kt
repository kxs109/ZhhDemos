package com.kxs109.mvvmbase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseVMActivity<VM : BaseViewModel>(useDataBinding: Boolean = true) :
    AppCompatActivity() {
    private lateinit var mViewModel: VM
    private val mUseDataBinding = useDataBinding
    private lateinit var mBinding: ViewDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initViewModel()
        observe()
        if (mUseDataBinding) {
            mBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, getResLayout())
            mBinding.lifecycleOwner = this
        } else {
            setContentView(getResLayout())
        }
        initView()
        initData()

    }

    abstract fun initData()

    abstract fun initView()

    abstract fun getResLayout(): Int

    abstract fun observe()

    abstract fun initViewModel(): VM
}