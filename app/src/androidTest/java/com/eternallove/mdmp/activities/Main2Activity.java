//package com.eternallove.mdmp.ui.activities;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Rect;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.eternallove.mdmp.R;
//
//import butterknife.ButterKnife;
//
//public class Main2Activity extends AppCompatActivity {
//
//
//    public static void actionStart(Context context) {
//        Intent intent = new Intent();
//        intent.setClass(context, Main2Activity.class);
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main2);
//        ButterKnife.bind(this);
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//
//        Rect outRect = new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
//        params.height = outRect.bottom - outRect.top;
//
//    }
//}
