package com.eternallove.mdmp.ui.fragments.MainFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eternallove.mdmp.R;
import com.eternallove.mdmp.ui.base.BaseFragment;
import com.eternallove.mdmp.ui.dialog.MessageDialog;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/28 11:55
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    //设置图片资源:url或本地资源
    Integer[] images = new Integer[]{
            R.drawable.ic_banner_1,
            R.drawable.ic_banner_2,
            R.drawable.ic_banner_3,
            R.drawable.ic_banner_4

    };

    //设置图片标题:自动对应
    String[] titles = new String[]{"数据安全","容灾备份","大数据","IT维保服务"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initBanner();
        return view;
    }

    private void initBanner() {
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        banner.setBannerStyle(Banner.NUM_INDICATOR_TITLE);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner.setIndicatorGravity(Banner.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        banner.setBannerTitle(titles);

        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //自定义图片加载框架
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(getActivity())
                        .load(url)
                        .into(view);
            }
        });
        //设置点击事件，下标是从1开始
        //设置点击事件
        banner.setOnBannerClickListener((view, position) -> {
            new MessageDialog(getActivity()).show();
            Toast.makeText(getActivity(), "你点击了：" + titles[position-1], Toast.LENGTH_SHORT).show();
        });
    }
    //如果你需要考虑更好的体验，可以这么操作

//    @Override
//    public void onStart() {
//        super.onStart();
//        banner.isAutoPlay(true);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        banner.isAutoPlay(false);
//    }
}
