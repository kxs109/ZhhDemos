package com.kxs109.lib_ksyplayer;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

import com.ksyun.media.player.KSYTextureView;
import com.kxs109.lib_ksyplayer.widget.FloatWindowView;

public class FloatingPlayer implements IPlayer {
    private KSYTextureView mKsyTextureView;
    private static FloatingPlayer _instance;

    private FloatWindowView mFloatingView;
    private WindowManager.LayoutParams mFloatingViewParams;
    private WindowManager mWindowManager;

    private FloatingPlayer() {}

    public static FloatingPlayer getInstance() {
        if (_instance == null) {
            synchronized (FloatingPlayer.class) {
                if (_instance == null)
                    _instance = new FloatingPlayer();
            }
        }

        return _instance;
    }

    public void init(final Context context) {
        if (mKsyTextureView != null) {
            mKsyTextureView.release();
            mKsyTextureView = null;
        }
        mKsyTextureView=new KSYTextureView(context);
    }
    public KSYTextureView getKSYTextureView() {
        return mKsyTextureView;
    }

    public void destroy() {
        if (mKsyTextureView != null)
            mKsyTextureView.release();

        mKsyTextureView = null;
    }


    private void createFloatingWindow(Context context) {
        if (context == null)
            return;

        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (mFloatingView == null) {
            mFloatingView = new FloatWindowView(context);
            mFloatingView.addFloatingWindow(FloatingPlayer.getInstance().getKSYTextureView());
//            mFloatingView.setHandler(mHandler);
            if (mFloatingViewParams == null) {
                mFloatingViewParams = new WindowManager.LayoutParams();
                mFloatingViewParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                mFloatingViewParams.format = PixelFormat.RGBA_8888;
                mFloatingViewParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
                mFloatingViewParams.gravity = Gravity.LEFT | Gravity.TOP;
                mFloatingViewParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                mFloatingViewParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                mFloatingViewParams.x = screenWidth;
                mFloatingViewParams.y = screenHeight;
            }

            mFloatingView.updateViewLayoutParams(mFloatingViewParams);
            windowManager.addView(mFloatingView, mFloatingViewParams);
        }
    }

    public void removeFloatingWindow(Context context) {
        if (mFloatingView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(mFloatingView);
            mFloatingView.removeFloatingWindow(FloatingPlayer.getInstance().getKSYTextureView());
            mFloatingView = null;
        }
    }

    private WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }


}
