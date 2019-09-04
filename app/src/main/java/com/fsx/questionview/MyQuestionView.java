package com.fsx.questionview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Create by Fang ShiXian
 * on 2019/9/3
 */
public class MyQuestionView extends LinearLayout implements View.OnClickListener {

    private LinearLayout ll_daAn;
    private TextView tv_wt, tv_daAn, tv_useful, tv_unUse;
    private Context context;
    private boolean evaluate = false;//是否已经评价

    public MyQuestionView(Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.item_question_view, this);
        ll_daAn = findViewById(R.id.ll_da1);
        tv_wt = findViewById(R.id.tv_wt1);
        tv_daAn = findViewById(R.id.tv_da1);
        tv_useful = findViewById(R.id.tv_useful);
        tv_unUse = findViewById(R.id.tv_unUse);

        tv_wt.setOnClickListener(this);
        tv_useful.setOnClickListener(this);
        tv_unUse.setOnClickListener(this);
    }

    public MyQuestionView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setContent(int question, int answer) {
        tv_wt.setText(question);
        tv_daAn.setText(answer);
    }

    public void open() {
        Drawable right = context.getResources().getDrawable(R.mipmap.icon_xs);
        tv_wt.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
        ll_daAn.setVisibility(VISIBLE);

    }

    public void close() {
        Drawable right = context.getResources().getDrawable(R.mipmap.icon_xx);
        tv_wt.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
        ll_daAn.setVisibility(GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_wt1:
                mInterface.closeLast(number);
                break;
            case R.id.tv_useful:
                if (!evaluate) {
                    //没有评价
                    mInterface.doUseful(number);
                    evaluate = true;
                    tv_useful.setTextColor(context.getResources().getColor(R.color.color_red_txt));
                }
                break;
            case R.id.tv_unUse:
                if (!evaluate) {
                    //没有评价
                    mInterface.doUnUse(number);
                    evaluate = true;
                    tv_unUse.setTextColor(context.getResources().getColor(R.color.color_red_txt));
                }
                break;
            default:
                break;
        }
    }

    private int number;
    private OnClickListenerInterface mInterface;

    public void setmInterface(int i, OnClickListenerInterface mInterface) {
        this.mInterface = mInterface;
        number = i;
    }

    //回调接口
    public interface OnClickListenerInterface {
        void closeLast(int number);//关闭上一个显示的答案

        void doUseful(int number);//有用点赞

        void doUnUse(int number);//无用
    }
}
