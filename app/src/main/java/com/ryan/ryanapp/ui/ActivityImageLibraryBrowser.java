package com.ryan.ryanapp.ui;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.ryan.ryanapp.Constants;
import com.ryan.ryanapp.R;
import com.ryan.ryanapp.RyanApp;
import com.ryan.ryanapp.Utils.FileUtils;
import com.ryan.ryanapp.Utils.StringUtil;
import com.ryan.ryanapp.Utils.UniversualImageLoaderUtils;
import com.ryan.ryanapp.model.Size;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityImageLibraryBrowser extends ActivityBase {


    private GridView imageLibrary;
    public static final int ACTIVITY_REQUEST_CODE_IMAGE_LIBRAY_BROWSER = 1;
    public static final int ACTIVITY_REQUEST_CODE_IMAGE_CROP = 2;
    public static final String ACTIVITY_RESULT_EXTRA_KEY = "imageUri";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setToolbarMiddleTitle("选择照片");
        addContentView(R.layout.fragment_image_library_browser);
    }

    private void initView() {

        imageLibrary = (GridView) findViewById(R.id.imageLibrary);
        getAllImages();
        imageLibrary.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.putExtra(ACTIVITY_RESULT_EXTRA_KEY, ((Image) parent.getItemAtPosition(position)).path);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void getAllImages() {

        new Thread() {

            @Override
            public void run() {

                super.run();
                final List<Image> imagePathes = new ArrayList<Image>();
                ContentResolver cr = getContentResolver();
                String[] columns = new String[]{MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.DATE_TAKEN};
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                Image image = null;
                while (cur != null && cur.moveToNext()) {
                    String string = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                    long dateTaken = cur.getLong(cur.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN));
                    if (string.startsWith(getCacheDir().getAbsolutePath()) || StringUtil.isEmpty(string)) {
                        continue;
                    }
                    image = new Image();
                    image.dateTaken = dateTaken;
                    File f = new File(string);
                    BitmapFactory.decodeFile(string, options);
                    if (options.outWidth < 320 || options.outWidth < 320) {
                        continue;
                    }
                    if (f.exists()) {
                        Uri uri = Uri.fromFile(f);
                        image.path = uri.toString();
                        imagePathes.add(image);
                    }
                }
                Collections.sort(imagePathes);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        imageLibrary.setAdapter(new ImageAdapter(imagePathes));
                        ((ImageAdapter) imageLibrary.getAdapter()).notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    private class ImageAdapter extends BaseAdapter {

        List<Image> images = new ArrayList<Image>();

        public ImageAdapter(List<Image> images) {

            this.images = images;
        }

        @Override
        public int getCount() {

            return images.size();
        }

        @Override
        public Object getItem(int position) {

            return images.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(ActivityImageLibraryBrowser.this, R.layout.fragment_image_libray_browser_item, null);
                Size screentSize = RyanApp.instance.getScreentSize();
                convertView.setLayoutParams(new AbsListView.LayoutParams((screentSize.getWidth() - 32) / 3, (screentSize.getWidth() - 32) / 3));
                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ImageLoader.getInstance().displayImage(URLDecoder.decode(images.get(position).path), viewHolder.imageView, UniversualImageLoaderUtils.getDisplayImageOptions(), new SimpleImageLoadingListener() {

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    super.onLoadingFailed(imageUri, view, failReason);
                    ((ImageView) view).setImageResource(R.drawable.ic_launcher);
                }
            });
            return convertView;
        }
    }

    class Image implements Comparable<Image> {

        String path;
        long dateTaken;

        @Override
        public int compareTo(Image another) {

            if (another.dateTaken > dateTaken) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {

            return "Image [path=" + path + ", dateTaken=" + dateTaken + "]\n";
        }
    }

    class ViewHolder {

        ImageView imageView;
    }


    /**
     * 从图库选择照片
     */
    public static void chooseImageFromLibrary(FragmentBase baseFragment) {

        Intent intent = new Intent(baseFragment.getActivity(), ActivityImageLibraryBrowser.class);
        baseFragment.startActivityForResult(intent, ACTIVITY_REQUEST_CODE_IMAGE_LIBRAY_BROWSER);
    }

    /**
     * 裁切图片
     *
     * @param baseFragment 调用裁切页面的Fragment
     * @param sourceUri    要裁切的图片源文件的Uri路径
     * @return 裁切完成的图片路径，存放在临时图片文件夹中
     */
    public static String cropImage(FragmentBase baseFragment, String sourceUri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.parse(sourceUri), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        String cropedImagePath = FileUtils.generateTempFilePath(Constants.FileType.IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(cropedImagePath)));
        baseFragment.startActivityForResult(intent, ACTIVITY_REQUEST_CODE_IMAGE_CROP);
        return cropedImagePath;
    }
}
