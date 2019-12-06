package com.example.sdcardimage2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class myImageView extends View {
    String imagePath = null;
    //5. String 변수  imagePath 선언 후, null값 적용  -> 경로를 가져오는 변수

    //2. extends View  뷰를 상속받는다
    //3.빨간줄 나오면 생성자 생성한다.
    public myImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }
    @Override
    //4. 코드  -> 오버라이드 메소드에서 onDraw 생성
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (imagePath!=null) {
            Bitmap bm = BitmapFactory.decodeFile(imagePath);


            //24. 이미지 위치 적용 부분
            int x = (this.getWidth()-bm.getWidth())/2;
            int y = (this.getHeight()-bm.getHeight())/2;
            canvas.drawBitmap(bm,x,y,null);







            //6. Bitmap 변수 bm선언 후 canvas 속성 적용
            //24. 이미지 크기 적용 부분  변수 선언 후 하단의 캔버스 비트맵에 변수 적용
            bm.recycle();
            //7. Bitmap 자원을 해제한다.   ->9 장에 나오는 개념

            //xml로이동

        }
    }


}
