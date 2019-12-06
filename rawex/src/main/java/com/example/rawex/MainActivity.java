package com.example.rawex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btnRawRead;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRawRead = (Button)findViewById(R.id.btnRawRead);
        tvResult=(TextView)findViewById(R.id.tvResult);

        btnRawRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream ins = getResources().openRawResource(R.raw.info);
                //4. InputStream 읽어온다.(로딩) OutputStream 내보낸다(저장)  변수 ins 선언 후, getResources() 리소스를 얻어온다..openRawResource(R.raw.info) R.raw라는 폴더안에 info 를 읽어온다.
                try {
                    byte txt[]=new byte[ins.available()];

                    //5.Byte 안의 수량만큼 읽어온다[ins.available()];  전부 다 읽어온다는 의미 이 부분은 try/catch 문을 요구한다. 이유는 해당 폴더 안의 파일(지금의 경우txt)의 확장자(.txt)
                    // 를 임의로 변경해 놓았을 수 있기 때문에 그러한 경우를 대비하여   try/catch 문을 작성한다.
                    ins.read(txt);
                    tvResult.setText(new String(txt));
                    ins.close();
                    //6. txt로 읽어올땐 String으로 읽어들여와야한다. 항상 .close(); 열었으면 닫는다.

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"파일을 읽을 수가 없습니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

// String str = "냉장고,에어컨,세탁기";
// String product[];
// product=str.split(",");   <-


