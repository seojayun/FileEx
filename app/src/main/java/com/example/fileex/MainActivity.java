package com.example.fileex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Button btnSave,btnRead;
    TextView tvResult;
    String str= "지금 이 내용이 저장됩니다. \n 즐거운 주말 되세요";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave=(Button)findViewById(R.id.btnSave);
        btnRead=(Button)findViewById(R.id.btnRead);
        tvResult=(TextView)findViewById(R.id.tvResult);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.파일에 관련된 클래스  FileOutputStream 저장장소에 보내는 것  FileInputStream  저장장소에서 읽어오는 것 .MODE_APPEND 새롭게 만들어서 저장한다는 의미 .MODE_PRIVATE 예를들어서 저장하려는 공간에 똑같은 파일이 있으면 덮어쓴다.
                //2.try-catch  오류를 잡아주는 역할
                try {
                    FileOutputStream fileos=openFileOutput("test.txt", Context.MODE_PRIVATE);
                    //4. fileos.write(str.getBytes()); 파일을 저장할떄는 Byte 단위로 저장된다.
                    fileos.write(str.getBytes());
                    //5.fileos.close(); 항상 반드시 파일을 종료시켜줘야 한다.
                    fileos.close();
                    showToast("test.txt 파일이 저장되었습니다.");
                // 3.IOException  입출력에 관한 모든 내용
                } catch (IOException e) {
                    showToast("파일을 저장 할 수 없습니다.");
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileIs=openFileInput("test.txt");
                    // 4. byte txt[] = new byte[fileIs.available()]; 바이트 배열로 생성되있기 때문에 바이트로 불러내야 한다.fileIs.available()파일의 내용을 전부 읽어온다. 일부만 읽어오고 싶으면 Byte만큼 숫자로 입력하면 된다.★★★
                    byte txt[] = new byte[fileIs.available()];
                    fileIs.read(txt);
                    //5.   String abc = new String(txt); txt 안에 있는 byte형태를 전부 String로 변환해줌
                    tvResult.setText(new String(txt));  //6. 익명으로 생성!
                    fileIs.close(); //7. 반드시 닫아줄 것
                } catch (IOException e) {
                    showToast("해당 파일을 읽을 수 없습니다.");
                }
            }
        });

    }
    void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        //toast가 여러번 나왔기 때문에 메소드를 하나 만들어서 해당 상황에 맞게 읽어 들여올 수 있도록 코딩하였다.
    }
}
