package com.fsx.questionview;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Create by Fang ShiXian
 * on 2019/9/4
 */
public class MainActivity extends AppCompatActivity {


    private LinearLayout linearLayout;

    private int[] stringIds = new int[]{R.string.str01, R.string.str01, R.string.str01, R.string.str01,
            R.string.str01, R.string.str01, R.string.str01, R.string.str01, R.string.str01,
            R.string.str01, R.string.str01, R.string.str01};
    private int[] stringIds2 = new int[]{R.string.str02, R.string.str02, R.string.str02, R.string.str02,
            R.string.str02, R.string.str02, R.string.str02, R.string.str02, R.string.str02,
            R.string.str02, R.string.str02, R.string.str02};
    private MyQuestionView[] views = new MyQuestionView[stringIds.length];
    private int lastShow = -1;//记录上一个打开的答案


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        linearLayout = findViewById(R.id.ll_question_list);

        MyQuestionView myQuestionView;
        for (int i = 0; i < stringIds.length; i++) {
            myQuestionView = new MyQuestionView(this);
            myQuestionView.setContent(stringIds[i], stringIds2[i]);
            linearLayout.addView(myQuestionView, i);
            views[i] = myQuestionView;//将每个myQuestionView添加到数组里
            myQuestionView.setmInterface(i, new MyQuestionView.OnClickListenerInterface() {

                @Override
                public void closeLast(int number) {
                    Log.e("test", "lastShow=" + lastShow + "   number=" + number);
                    if (lastShow == -1) {
                        views[number].open();//打开当前点击的
                        lastShow = number;
                    } else if (lastShow != number) {
                        views[lastShow].close();//关闭上一个显示的
                        views[number].open();//打开当前点击的
                        lastShow = number;
                    } else {
                        views[number].close();//点击已打开的就关闭
                        lastShow = -1;
                    }

                }

                @Override
                public void doUseful(int number) {
                    Log.e("test", "第" + number + "个点赞了");
                }

                @Override
                public void doUnUse(int number) {
                    Log.e("test", "第" + number + "个点踩了");
                }
            });
        }
    }

}
