package com.example.testex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView textViewQ,textanswer;
    RadioButton rdoQ[] = new RadioButton[4];
    //1.배열생성
    Integer rdoID[] = { R.id.rdo1,R.id.rdo2,R.id.rdo3, R.id.rdo4};
    Integer ivQID[]  = { R.drawable.q1,R.drawable.q2,R.drawable.q3,R.drawable.q4};
    //2.배열에 맞게 이미지 배치
    ImageView imgV;
    Button btnanswer, btnR,btnS;
    String qtext[];
    //3.텍스트에 대한 배열 생성
    String answer;
    //4. 결과를 보는 변수 선언
    LinearLayout linearM;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQ=(TextView)findViewById(R.id.textViewQ);
        textanswer=(TextView)findViewById(R.id.textanswer);
        for (int i=0; i<rdoQ.length; i++) {
            rdoQ[i]=(RadioButton)findViewById(rdoID[i]);
        }
        //5. 캐스팅 또한 배열로 처리하였다.
        imgV=(ImageView)findViewById(R.id.imgV);
        btnanswer=(Button)findViewById(R.id.btnanswer);
        btnR=(Button)findViewById(R.id.btnR);
        btnS=(Button)findViewById(R.id.btnS);
        linearM=(LinearLayout)findViewById(R.id.linearM);

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearM.setVisibility(View.VISIBLE);
            }
        });

        InputStream  inS = getResources().openRawResource(R.raw.test);
        try {
            byte txt[] = new byte[inS.available()];
            inS.read(txt);
            String str = new String(txt);
            //6. String str 변수선언
            qtext=str.split("#");
            //7.  qtext=str.split("#"); txt 안에 있는 내용을 #으로 구분하여 배열에 넣는다
            textViewQ.setText("문제 : "+qtext[0]);
            for (int i=0; i<rdoQ.length; i++) {
                rdoQ[i].setText(qtext[i+1]); }
                inS.close();
            for (int i = 0; i<rdoQ.length; i++) {
                final int index;
                index =i;
                rdoQ[index].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgV.setImageResource(ivQID[index]);
                        answer=qtext[index+5];
                    }
                });
                btnanswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textanswer.setText(answer);
                    }
                });

                btnR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      finish();
                    }
                });

            }
            //8.  for문 작성

         } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"파일을 읽을 수가 없습니다.",Toast.LENGTH_SHORT).show();
        }


    }
}
