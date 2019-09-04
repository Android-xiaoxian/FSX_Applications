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


    private String[] question ;
    private String[] answer ;
    private int lastShow = -1;//记录上一个打开的答案
    private MyQuestionView[] views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        LinearLayout linearLayout = findViewById(R.id.ll_question_list);

        question = getResources().getStringArray(R.array.question);
        answer = getResources().getStringArray(R.array.answer);
        views = new MyQuestionView[question.length];
        MyQuestionView myQuestionView;
        for (int i = 0; i < answer.length; i++) {
            myQuestionView = new MyQuestionView(this);
            myQuestionView.setContent(question[i], answer[i]);
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
