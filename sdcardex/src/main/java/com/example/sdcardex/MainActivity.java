package com.example.sdcardex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnSdCardRead,btnMkdir,btnRMder;
    TextView tvResult;
    // 1. sd카드(외부저장소)는 파일을 불러오려면 보안권한이 필요하다.
    // 2. 좌측 manifests 폴더 더블클릭하여 AndroidManifest.xml 들어가서 퍼미션을 설정한다.  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    String sdcardPath;
    //5. sd카드 경로가 핸드폰마다 다르기때문에, 어느 핸드폰이든 접속할 수 있도록 String변수 선언
    File myDir;
    //8. 파일을 생성하고 관리하거나 하기 위해, 파일 클래스의 변수를 선언하였다.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSdCardRead=(Button)findViewById(R.id.btnSdCardRead);
        btnMkdir=(Button)findViewById(R.id.btnMkdir);
        btnRMder=(Button)findViewById(R.id.btnRMder);
        tvResult=(TextView)findViewById(R.id.tvResult);

        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        // 3. 6.0버전 이후로는 이중보한 때문에 자바에서 한번더 선언해야 한다.

        sdcardPath= Environment.getExternalStorageDirectory().getAbsolutePath();
        //6.  Environment.getExternalStorageDirectory().getAbsolutePath();  외부기억장치의 절대 경로를 얻어온다.
        myDir = new File("/storage/2A2B-DA07/JiYoo");
        //9. 선언한 변수에 /(이름) 폴더를 생성해서 집어넣음

        btnSdCardRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Toast.makeText(getApplicationContext(),"경로;"+sdcardPath,Toast.LENGTH_SHORT).show();
                    FileInputStream fileis = new FileInputStream("/storage/2A2B-DA07/sdcast.txt");
                    // 4. 이 부분이 다르다 new FileInputStream()을 생성해야한다. 또한 불러올 곳을 지정할 때 확장자 까지 전부 입력해야 한다.폴더구분은 / 로 구분한다.
                    byte txt[] = new byte[fileis.available()];
                    fileis.read(txt);
                    tvResult.setText(new String(txt));
                    fileis.close();
                    //7. 마무리
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"파일을 읽어올 수 없습니다.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDir.mkdir();
                //10.폴더 생성 명령어 작성  .mkdir() 디렉토리를 만들겠다는 메소드
                Toast.makeText(getApplicationContext(),"폴더가 생성되었습니다.",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"경로;"+sdcardPath,Toast.LENGTH_SHORT).show();
            }
        });

        btnRMder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDir.delete();
                //11. .delete(); 디렉토리를 지우겠다는(삭제) 메소드
                Toast.makeText(getApplicationContext(),"폴더가 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"경로;"+sdcardPath,Toast.LENGTH_SHORT).show();
            }
        });
    }

}



