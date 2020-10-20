1.androidx懒加载,见module_someTest里面代码。运行module_someTest即可。
FragmentPagerAdapter构造器增加了一个标记BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT来调用setMaxLifecycle()方法控制当前fragment最大生命周
```
@Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = (Fragment)object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                if (mBehavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                    if (mCurTransaction == null) {
                        mCurTransaction = mFragmentManager.beginTransaction();
                    }
                    mCurTransaction.setMaxLifecycle(mCurrentPrimaryItem, Lifecycle.State.STARTED);
                } else {
                    mCurrentPrimaryItem.setUserVisibleHint(false);
                }
            }
            fragment.setMenuVisibility(true);
            if (mBehavior == BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                if (mCurTransaction == null) {
                    mCurTransaction = mFragmentManager.beginTransaction();
                }
                mCurTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }

            mCurrentPrimaryItem = fragment;
        }
    }
```

![life](https://github.com/kxs109/ZhhDemos/raw/master/module_someTest/src/main/art/life.jpg)
![life2](https://github.com/kxs109/ZhhDemos/raw/master/module_someTest/src/main/art/life2.jpg)
![life3](https://github.com/kxs109/ZhhDemos/raw/master/module_someTest/src/main/art/life3.jpg)



2.鲤鱼动画，主要复习path相关绘制及屏幕坐标计算，详细代码见module_viewLearn。分三步绘制：①绘制一条静止的鱼②绘制一条会摆动的鱼③游动路线计算
![fish1](https://github.com/kxs109/ZhhDemos/raw/master/module_viewLearn/src/art/fish1.gif)
![fish2](https://github.com/kxs109/ZhhDemos/raw/master/module_viewLearn/src/art/fish2.gif)

