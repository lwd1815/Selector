package com.example.lwd18.pictureselecotor.bigphotobrows;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.dialog.QuickOptionDialog;
import java.util.ArrayList;
import java.util.List;



public class ImageBrowseActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View
        .OnClickListener, ImageBrowseView,QuickOptionDialog.OnClick,ViewPageAdapter.ShowDialog {

    private ViewPager            vp;
    private TextView             hint;
    private TextView             save;
    private ViewPageAdapter      adapter;
    private ImageBrowsePresenter presenter;
    private QuickOptionDialog mDialog;
    private boolean isShow=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_image_browse);
        vp = (ViewPager) this.findViewById(R.id.viewPager);
        hint = (TextView) this.findViewById(R.id.hint);
        save = (TextView) this.findViewById(R.id.save);
        save.setOnClickListener(this);
        initPresenter();
        presenter.loadImage();

    }

    public void initPresenter() {
        presenter = new ImageBrowsePresenter(this);
    }

    @Override
    public Intent getDataIntent() {
        return getIntent();
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void setImageBrowse(List<String> images, int position) {
        if (adapter == null && images != null && images.size() != 0) {
            adapter = new ViewPageAdapter(ImageBrowseActivity.this,this, images);
            vp.setAdapter(adapter);
            vp.setCurrentItem(position);
            vp.addOnPageChangeListener(this);
            hint.setText(position + 1 + "/" + images.size());
            adapter.setShowDialog(this);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        presenter.setPosition(position);
        hint.setText(position + 1 + "/" + presenter.getImages().size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        new Thread(presenter).start();
        Toast.makeText(ImageBrowseActivity.this, "图片已经保存", Toast.LENGTH_SHORT).show();
    }


    public static void startActivity(Context context, ArrayList<String> images, int position) {
        Intent intent = new Intent(context, ImageBrowseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putStringArrayListExtra("images", images);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    public void saveclick() {
        if (isShow) {
            new Thread(presenter).start();
        }
    }

    @Override
    public void show() {
        showQuickOption();
        isShow=true;
        if (mDialog!=null) {
            mDialog.setOnClick(this);
        }
    }

    private void showQuickOption() {
        mDialog = new QuickOptionDialog(ImageBrowseActivity.this,this);
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }
}
