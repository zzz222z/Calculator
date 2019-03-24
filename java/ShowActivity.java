package com.example.yyy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {
    public Button bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9, bt_pt;
    public Button bt_mul, bt_div, bt_add, bt_sub;
    public Button bt_clr, bt_eq;
    public TextView et_input;
    boolean input;//给显示框清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        bt_0 = (Button) findViewById(R.id.num_0);
        bt_1 = (Button) findViewById(R.id.num_1);
        bt_2 = (Button) findViewById(R.id.num_2);
        bt_3 = (Button) findViewById(R.id.num_3);
        bt_4 = (Button) findViewById(R.id.num_4);
        bt_5 = (Button) findViewById(R.id.num_5);
        bt_6 = (Button) findViewById(R.id.num_6);
        bt_7 = (Button) findViewById(R.id.num_7);
        bt_8 = (Button) findViewById(R.id.num_8);
        bt_9 = (Button) findViewById(R.id.num_9);
        bt_pt = (Button) findViewById(R.id.point);
        bt_add = (Button) findViewById(R.id.add);
        bt_sub = (Button) findViewById(R.id.minus);
        bt_mul = (Button) findViewById(R.id.mul);
        bt_div = (Button) findViewById(R.id.div);
        bt_eq = (Button) findViewById(R.id.equal);
        bt_clr = (Button) findViewById(R.id.clr);
        et_input = (TextView) findViewById(R.id.face);

        //设置点击事件
        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_pt.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_sub.setOnClickListener(this);
        bt_mul.setOnClickListener(this);
        bt_div.setOnClickListener(this);
        bt_eq.setOnClickListener(this);
        bt_clr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String info = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.num_0:
            case R.id.num_1:
            case R.id.num_2:
            case R.id.num_3:
            case R.id.num_4:
            case R.id.num_5:
            case R.id.num_6:
            case R.id.num_7:
            case R.id.num_8:
            case R.id.num_9:
            case R.id.point:
                if (input) {
                    input = false;
                    info = "";
                    et_input.setText("");
                }
                et_input.setText(info + ((Button) v).getText());
                break;
            case R.id.add:
            case R.id.minus:
            case R.id.mul:
            case R.id.div:
                if (input) {
                    input = false;
                    info = "";
                }
                if (info.contains("+") || info.contains("-") || info.contains("×") || info.contains("÷")) {
                    info = info.substring(0, info.indexOf(" "));
                }
                et_input.setText(info + " " + ((Button) v).getText() + " ");
                break;
            case R.id.clr:
                if (!input) {
                    input = true;
                    info = "";
                    et_input.setText("");
                }
                break;
            case R.id.equal:
                getResult();
                break;
        }
    }
    private void getResult(){
        String exp=et_input.getText().toString();
        if(exp==null||exp.equals("")) return ;

        if(!exp.contains(" ")){
            return ;
        }
        if(input){
            input=false;
            return;
        }
        input=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        double cnt=0;
        String r = "";
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0){
                    r = "不能除以0";
                    et_input.setText(r);
                }
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                if(r!=""){

                }else
                    et_input.setText(cnt+"");}
        }
        //s1不为空但s2为空
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //s1是空但s2不是空
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else {
            et_input.setText("");
        }
    }
}
