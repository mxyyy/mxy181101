package com.bwie.ballsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*1.自绘式自定义控件：View
* 2.组合式自定义控件: ViewGroup的子类（LinearLayout,RelativeLayout）
* 3.继承式自定义控件: TextView Button ImageView ListView  Android的原生控件*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
