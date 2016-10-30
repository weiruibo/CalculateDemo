package cn.edu.gdmec.w07150837.calculatedemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioButton man;
    private RadioButton woman;
    private EditText weightButton;
    private TextView result;
    private Button calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightButton = (EditText) findViewById(R.id.weight);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        calculator = (Button) findViewById(R.id.calculator);
        result = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        registerEvent();
        super.onStart();
    }

    private void showMessage(String message) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统提示");
        alert.setMessage(message);
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }
        );
        alert.show();
    }

    private double evaluateHeight(double weight, String sex) {

        double height;

        if (sex == "男") {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.6;
        }

        return height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void registerEvent() {
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weightButton.getText().toString().trim().equals("")){
                    if(woman.isChecked()||man.isChecked()){
                        double weight=Double.parseDouble(weightButton.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("----------------评估结果----------------\n");
                        if(man.isChecked()) {
                            sb.append("男性标准身高:");
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "(厘米)");
                        }
                        if(woman.isChecked()){
                           sb.append("女性标准身高:");
                            double result=evaluateHeight(weight,"女");
                            sb.append((int)result+"(厘米)");
                        }

                        result.setText(sb.toString());
                    }else {

                        showMessage("请选择性别!!");
                    }


                }else {
                    showMessage("请输入体重!!");
                }
            }
        });
    }
}
