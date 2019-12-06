package com.example.sdcardimage;

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
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //선행작업으로 이미지파일 이외에 다른 파일을 읽어오지 못하도록 해당 디렉토리(폴더)에 다른 파일 한개를 넣었다.)
    Button btnPrev,btnNext;
    ImageView ivSDImage;
    TextView tvRound;

    int posNum=0;
    //10. 초기값을 0으로 주었다.

    File imageFiles[];
    String imageFname,extName, sdPath;

    //1. int 변수 file 변수 string 변수 선언
    ArrayList<File> imgList;
    //3. 동적배열 생성

    //배열의 정의 방법 3가지
    //(1)
    //String str[] = new String[5];
    //str[0] = "Hello";....
    //(2)
    //String str[] = {"hello","hi","hoho"}
    // System.out.println(str[0]);
    //(3)동적배열  늘어나는 배열?
    // ArrayList<String> str;
    //str.add("hello");
    //str.add("korea");.....
    //str.add("babo");
    //System.out.println(str.get(0)); []가 아닌 get()로 접근한다.
    //str.remove(1);   ->   배열을 제거하는 것
    //System.out.println(str.get(1));  이 경우에는 babo가 뜬다. 즉 배열을 추가하고 줄이거나 제거가 가능하다.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        ivSDImage=(ImageView)findViewById(R.id.ivSDImage);
        tvRound=(TextView)findViewById(R.id.tvRound);
        imgList= new ArrayList<File>();
        //3-1 동적배열 생성 후 선언한다.
        int permissionCheck=ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_DENIED) {  // if(permissionCheck != PackageManager.PERMISSION_GRANTED)  같은의미
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        }else {
            sdcardProcess();
        }
//        imageFiles=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/storage/2A2B-DA07/Pic").listFiles();


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum<=0) {
//                    posNum = posNum+5;  내가쓴정답
                      posNum=imgList.size()-1;  //마지막 인덱스로
                }else {
                    posNum--;
                }
                    imageFname = imgList.get(posNum).toString();
                    Bitmap bm = BitmapFactory.decodeFile(imageFname);
                    ivSDImage.setImageBitmap(bm);

                    tvRound.setText((posNum+1)+"/"+imgList.size());
                    //13 이건 이전으로 돌아가는것이기 때문에 --

//                    tvRound.setText((posNum+1)+"/5");   내가쓴정답

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posNum>=imgList.size()-1) {
//                    posNum = posNum-5; 내가쓴정답
                    posNum=0;  //처음 인덱스로
                }else {
                    posNum++;
                    //12.0이었는데 1이된다
                }  imageFname = imgList.get(posNum).toString();
                    Bitmap bm = BitmapFactory.decodeFile(imageFname);
                    ivSDImage.setImageBitmap(bm);
//                    tvRound.setText((posNum+1)+"/5");   내가쓴정답
                tvRound.setText((posNum+1)+"/"+imgList.size());

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        sdcardProcess();
    }
    void sdcardProcess() {
        sdPath="/storage/2A2B-DA07/Pic";
        //나는 절대 경로를 이용할 수 없으므로 직접적으로 경로를 가져와야 한다.
        imageFiles=new File(sdPath).listFiles();
        //2. 외부기억장치의 절대권한 생성

        for (File file:imageFiles) {
            imageFname=file.getName();
            extName=imageFname.substring(imageFname.length()-3);
            if(extName.equals("jpg")|| extName.equals("png")|| extName.equals("gif")) {
                imgList.add(file);
            }
        }

        imageFname = imgList.get(posNum).toString();
        //7. 동적 배열의 0번째 항목을 가져와 대입한다. //11. 0 대신 posNum 대입

        Bitmap bm= BitmapFactory.decodeFile(imageFname);
        //매우중요!!!!
        //8.Bitmap 이라는 안드로이드 클래스를 활용하여 만든다.
        ivSDImage.setImageBitmap(bm);
        //9. 이미지를 불러옴
//        tvRound.setText((posNum+1)+"/5");    내가쓴정답
          tvRound.setText("1/"+imgList.size());


    }
}


//        for (File file:imageFiles) {
//            //4.임시로 만든 파일 변수 file    ---> 향상된 for문
//            imageFname=file.getName();
//            extName=imageFname.substring(imageFname.length()-3);
//            //5. .substring 확장자만 얻어 온다.
//            if(extName.equals("jpg")|| extName.equals("png")|| extName.equals("gif")) {
//                imgList.add(imageFname);
//                //6 if(~~) 해당 파일 확장명이 아닌 것이면 빠져나올 것이고, 이미지파일이면, if구문안의 imgList.add(imageFname);  파일을 가져온다.
//            }
//
//        }


//    sdPath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pic";
//        imageFiles=new File(sdPath).listFiles();
//        //2. 외부기억장치의 절대권한 생성
//
//        for (File file:imageFiles) {
//            imageFname=file.getName();
//            extName=imageFname.substring(imageFname.length()-3);
//            if(extName.equals("jpg")|| extName.equals("png")|| extName.equals("gif")) {
//                imgList.add(imageFname);
//            }
//        }
//
//        imageFname = imgList.get(posNum).toString();
//        //7. 동적 배열의 0번째 항목을 가져와 대입한다. //11. 0 대신 posNum 대입
//
//        Bitmap bm= BitmapFactory.decodeFile(sdPath+"/"+imageFname);
//        //매우중요!!!!
//        //8.Bitmap 이라는 안드로이드 클래스를 활용하여 만든다.
//        ivSDImage.setImageBitmap(bm);
//        //9. 이미지를 불러옴



//하단의 구문은 매우 자주 사용됨

//        int permissionCheck=ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if(permissionCheck == PackageManager.PERMISSION_DENIED) {  // if(permissionCheck != PackageManager.PERMISSION_GRANTED)  같은의미
//            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
//        }else {
//            sdcardProcess();
//        }