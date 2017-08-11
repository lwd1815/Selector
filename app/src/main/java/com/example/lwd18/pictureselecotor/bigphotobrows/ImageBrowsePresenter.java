package com.example.lwd18.pictureselecotor.bigphotobrows;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/5/24 9:45
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class ImageBrowsePresenter implements Runnable{

    private ImageBrowseView view;
    private List<String> images;
    private int position;
    private String[] imageTypes = new String[] { ".jpg",".png", ".jpeg","webp"};
    public ImageBrowsePresenter(ImageBrowseView view) {
        this.view = view;
    }

    public void loadImage(){
        Intent intent = view.getDataIntent();
        images = intent.getStringArrayListExtra("images");
        position = intent.getIntExtra("position",0);
        view.setImageBrowse(images,position);
    }

    /**
     * 利用glide加载图片时,一定要放在线程中,不然会报错
     */
    @Override
    public void run() {
        final String imageUrl = getPositionImage();
        Bitmap bitmap = null;
        try {
             bitmap = Glide.with(view.getMyContext()).asBitmap().load(imageUrl).into(Target.SIZE_ORIGINAL, Target
                    .SIZE_ORIGINAL).get();
            if (bitmap!=null){
                saveBitmap(bitmap,imageUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void saveBitmap(Bitmap bitmap,String url) {
        // 创建目录
        //获取内部存储状态  
        String state =Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写  
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            return;
        }
        //String sdCardDir=Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/";
        // TODO: 2017/8/8 暂时改动 
        String sdCardDir=Environment.getExternalStorageDirectory().getAbsolutePath();
        File appDir = new File(sdCardDir, "DAYU");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String imageType = getImageType(url); //获取图片类型
        String fileName = "DAYU"+System.currentTimeMillis() + "." + imageType;
        File file = new File(appDir, fileName);
        //保存图片
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if(TextUtils.equals(imageType,"jpg")) imageType = "jpeg";
            imageType = imageType.toUpperCase();
            bitmap.compress(Bitmap.CompressFormat.valueOf(imageType), 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(view.getMyContext(),"保存成功",Toast.LENGTH_SHORT).show(); //Toast
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(view.getMyContext().getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        view.getMyContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
    }

    public String getPositionImage(){
        return images.get(position);
    }

    public List<String> getImages() {
        return images;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getImageType(String imageUrl){
        String imageType = "";
        if(imageUrl.endsWith(imageTypes[0])){
            imageType = "jpg";
        }else if(imageUrl.endsWith(imageTypes[1])){
            imageType = "png";
        }else{
            imageType = "jpeg";
        }
        return imageType;
    }
}

