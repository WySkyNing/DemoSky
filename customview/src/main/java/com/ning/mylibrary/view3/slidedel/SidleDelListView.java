package com.ning.mylibrary.view3.slidedel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.ning.mylibrary.R;

/**
 * Created by wy on 2016/11/23.
 */

public class SidleDelListView extends ListView {

    /**
     * 用户滑动的最小距离
     */
    private int touchSlop;

    /**
     * 是否响应滑动
     */
    private boolean isSliding;

    /**
     * 手指按下的 x,y 坐标
     */
    private int xDown, yDown;

    /**
     * 手指移动的 x,y 坐标
     */
    private int xMove, yMove;

    private LayoutInflater layoutInflater;

    private PopupWindow popupWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;

    private Button btnDelete;

    /**
     * 为删除按钮提供一个接口
     */
    private DeleteBtnClickListener deleteBtnClickListener;

    private View mCurrentView;

    private int mCurrentViewPosition;


    public SidleDelListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        layoutInflater = LayoutInflater.from(context);

        /** 表示移动的时候，手移动的距离要大于这个距离才开始移动控件
         *  ViewConfiguration 里面一堆长量
         *  */
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        View view = layoutInflater.inflate(R.layout.item_delete_btn, null);

        btnDelete = (Button) view.findViewById(R.id.id_item_btn);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        /**
         * 先调用下 measure 否则拿不到宽高
         * 参数 意义
         *  MeasureSpec.UNSPECIFIED = 0
         *  MeasureSpec.EXACTLY = 1
         *  MeasureSpec.AT_MOST = 2
         * */
        popupWindow.getContentView().measure(2, 2);

        mPopupWindowWidth = popupWindow.getContentView().getWidth();
        mPopupWindowHeight = popupWindow.getContentView().getHeight();
    }

    public void setDeleteBtnClickListener(DeleteBtnClickListener deleteBtnClickListener){
        this.deleteBtnClickListener = deleteBtnClickListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (action) {

            case MotionEvent.ACTION_DOWN:

                xDown = x;
                yDown = y;

                /**
                 * 如果当前 popupWindow 正在显示，则直接隐藏，
                 * 然后屏蔽 ListView 的 Touch 事件继续往下传递
                 * */
                if (popupWindow.isShowing()){
                    dismissPopWindow();
                    return false;
                }

                /**
                 * 获得当前手指按下的 item 的位置
                 * */
                mCurrentViewPosition = pointToPosition(xDown,yDown);

                /**
                 * 获得当前手指按下时的 item
                 * mCurrentViewPosition 点击位置在整个 listView 中 item　总数中的位置
                 * getFirstVisiblePosition（）　获取当前屏幕显示的第一个　item 在　item　总数的位置
                 * 差为　当前点击位置　在当前屏幕中　item　数量的位置
                 *
                 * mCurrentView　获取当前屏幕点击的那个　view
                 * */
                mCurrentView = getChildAt(mCurrentViewPosition - getFirstVisiblePosition());

                break;

            case MotionEvent.ACTION_MOVE:

                xMove = x;
                yMove = y;

                int dx = xMove - xDown;
                int dy = yMove - yDown;

                /**
                 * 判断是否是从右到左的滑动
                 * */
                if (xMove < xDown && Math.abs(dx) > touchSlop && Math.abs(dy) < touchSlop){

                    isSliding = true;
                }
                break;
        }


        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (isSliding){
            switch (ev.getAction()){

                case MotionEvent.ACTION_MOVE:
                    int [] location  = new int[2];

                    mCurrentView.getLocationOnScreen(location);
                    popupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
                    popupWindow.update();

                    popupWindow.showAtLocation(mCurrentView, Gravity.START | Gravity.TOP,
                            location[0] + mCurrentView.getWidth() ,
                            location[1] );

                    btnDelete.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (deleteBtnClickListener != null){

                                deleteBtnClickListener.onDeleteBtnClick(mCurrentViewPosition);
                                popupWindow.dismiss();
                            }
                        }
                    });
                    break;

                case MotionEvent.ACTION_UP:

                    isSliding = false;
                    break;
            }

            //响应滑动期间屏幕 onItemClick 事件,避免发生冲突
            return true;
        }

        return super.onTouchEvent(ev);
    }
    /**
     * 在 dispatchTouchEvent 中设置当前是否响应用户的滑动，然后在 onTouchEvent 中判断是否响应，
     * 如果响应则 popupWindow 以动画的形式展现出来。
     * 当然屏幕上如果存在 popupWindow 则屏屏蔽 ListView 的滚动与 item 的点击，以及从右到左滑动时
     * 屏蔽 item 的点击事件
     * */

    private void dismissPopWindow(){
        if (null != popupWindow && popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }

    interface DeleteBtnClickListener{
        void onDeleteBtnClick(int position);
    }
}
