package com.example.sdcardimage2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button btnBack,btnNext;
    TextView tvNumber;
    File imgFile[];   //5.sd카드에 있는 폴더
    String imgName;  //6.하나 이미지 이름
    int posNum=0;  //7. 이미지 배열의 인덱스  초기값0
    myImageView ivSDcardimage;
    ArrayList<File> imgList;   //8.sd카드에 있는 이미지 폴더중에 이미지만 담을 동적배열
    String sdcardpath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBack=(Button)findViewById(R.id.btnBack);
        btnNext=(Button)findViewById(R.id.btnNext);
        tvNumber=(TextView)findViewById(R.id.tvNumber);
        ivSDcardimage=(myImageView)findViewById(R.id.ivSDcardimage);
        imgList = new ArrayList<File>();  //인스턴스 객체생성

         int parmissimCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
         //다시한번 이중보안 설정
        if(parmissimCheck == PackageManager.PERMISSION_DENIED) { //9. parmissimCheck == PackageManager.PERMISSION_DENIED  한번도 퍼미션 을 체크하지 않았다면, 문장안을 실행
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        }else {  // 10. 퍼미션 체크가 이미 되어있다면, 문장안을 실행.   code 오버라이드 메소드 에서  onRequestPermissionsResult 선택
            sdcardProcess();   //12. sdcardProcess(); 실행되도록 설정
        }

 //23. 최종적으로 버튼에 대한 부분 설정한다. (이전으로/다음으로)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum<=0) {
                    posNum=imgList.size()-1;
                }else {
                    posNum--;
                }
                imgName=imgList.get(posNum).toString();
                ivSDcardimage.imagePath=imgName;
                ivSDcardimage.invalidate();
                tvNumber.setText((posNum+1)+"/"+imgList.size());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(posNum>=imgList.size()-1) {
                    posNum=0;
                }else  {
                    posNum++;
                }
                imgName=imgList.get(posNum).toString();
                ivSDcardimage.imagePath=imgName;
                ivSDcardimage.invalidate();
                tvNumber.setText((posNum+1)+"/"+imgList.size());
            }
        });
//23. 최종적으로 버튼에 대한 부분 설정한다. (이전으로/다음으로)

        //24. 이미지를 크게 변환시키고 싶다면, myImageView.java로 이동

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        sdcardProcess();// 13. sdcardProcess(); 실행되도록 설정
    }
    //11.void sdcardProcess() 메소드 생성
    void sdcardProcess() {
        //14.경로 설정
        sdcardpath= "/storage/2A2B-DA07/Pic";
        imgFile=new File(sdcardpath).listFiles();
        String fileName, extName;  //15.String 변수 2개 선언  -> 파일이름, 확장자를 가져올 변수
        for(File file :imgFile) {   //16. imgFile에있는 0번째 배열이 넘어감(첫번째파일)
            fileName=file.getName();
                    //17.파일이름만 담는다.
            extName=fileName.substring(fileName.length()-3);
            //18.파일 확장자만 받는다.
            if (extName.equals("png")|| extName.equals("gif")||extName.equals("jpg")) {  //19. extName이 받아온 확장자 명이 png,gif,jpg 면 문장 실행 아니면 빠져나감
                imgList.add(file);  // 20.리스트에 경로까지 포함한 것을 add한다.
            }
        }
            imgName=imgList.get(posNum).toString();
            ivSDcardimage.imagePath=imgName;
            //21.  imagePath에는 이미지 경로와 이미지를 전부 가지고 있다.
            ivSDcardimage.invalidate();//22. .invalidate(); = 무효화한다.  -> 전에 있던 캔버스에에서 새로운 받는 것을 의미
                                        // 22 myImageView클래스의 onDraw 메소드를 호출한다는 것을 의미
            tvNumber.setText((posNum+1)+"/"+imgList.size());





    }
}
