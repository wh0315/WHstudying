package com.caifulif.whstudying.edittext;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.caifulif.whstudying.R;

import java.io.FileNotFoundException;

public class EditTextActivity extends AppCompatActivity {
    private static final int PHOTO_SUCCESS = 1;
    private static final int CAMERA_SUCCESS = 2;
    /**
     * Called when the activity is first created.
     */
    Button b;
    MyEditText e;
    int width;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        WindowManager wm = (WindowManager) this
                .getSystemService(WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        b = (Button) findViewById(R.id.myButton);
        e = (MyEditText) findViewById(R.id.myEdit);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final CharSequence[] items = {"手机相册", "相机拍摄"};
                AlertDialog dlg = new AlertDialog.Builder(EditTextActivity.this).setTitle("选择图片")
                        .setItems(items,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {
                                        //这里item是根据选择的方式,
                                        //在items数组里面定义了两种方式, 拍照的下标为1所以就调用拍照方法
                                        if (item == 1) {
                                            Intent getImageByCamera = new Intent("android.media" +
                                                    ".action" +
                                                    ".IMAGE_CAPTURE");
                                            startActivityForResult(getImageByCamera,
                                                    CAMERA_SUCCESS);
                                        } else {
                                            Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                                            getImage.addCategory(Intent.CATEGORY_OPENABLE);
                                            getImage.setType("image/*");
                                            startActivityForResult(getImage, PHOTO_SUCCESS);
                                        }
                                    }
                                }).create();
                dlg.show();
//                e.insertDrawable(R.drawable.easy);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        ContentResolver resolver = getContentResolver();
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_SUCCESS:
                    //获得图片的uri
                    Uri originalUri = intent.getData();
                    Bitmap bitmap = null;
                    try {
                        Bitmap originalBitmap = BitmapFactory.decodeStream(resolver
                                .openInputStream(originalUri));
                        bitmap = resizeImage(originalBitmap, width, width);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (bitmap != null) {
                        //根据Bitmap对象创建ImageSpan对象
                        ImageSpan imageSpan = new ImageSpan(EditTextActivity.this, bitmap);
                        //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
                        SpannableString spannableString = new SpannableString("[local]" + 1 +
                                "[/local]");
                        //  用ImageSpan对象替换face
                        spannableString.setSpan(imageSpan, 0, "[local]1[local]".length() + 1,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        //将选择的图片追加到EditText中光标所在位置
                        int index = e.getSelectionStart(); //获取光标所在位置
                        Editable edit_text = e.getEditableText();
                        if (index < 0 || index >= edit_text.length()) {
                            edit_text.append(spannableString);
                        } else {
                            edit_text.insert(index, spannableString);
                        }
                    } else {
                        Toast.makeText(EditTextActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CAMERA_SUCCESS:
                    Bundle extras = intent.getExtras();
                    Bitmap originalBitmap1 = (Bitmap) extras.get("data");
                    if (originalBitmap1 != null) {
                        WindowManager wm = (WindowManager) this
                                .getSystemService(WINDOW_SERVICE);
                        int width = wm.getDefaultDisplay().getWidth();
                        int height = wm.getDefaultDisplay().getHeight();
                        bitmap = resizeImage(originalBitmap1, width, width);
                        //根据Bitmap对象创建ImageSpan对象
                        ImageSpan imageSpan = new ImageSpan(EditTextActivity.this, bitmap);
                        //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
                        SpannableString spannableString = new SpannableString("[local]" + 1 +
                                "[/local]");
                        //  用ImageSpan对象替换face
                        spannableString.setSpan(imageSpan, 0, "[local]1[local]".length() + 1,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        //将选择的图片追加到EditText中光标所在位置
                        int index = e.getSelectionStart(); //获取光标所在位置
                        Editable edit_text = e.getEditableText();
                        if (index < 0 || index >= edit_text.length()) {
                            edit_text.append(spannableString);
                        } else {
                            edit_text.insert(index, spannableString);
                        }
                    } else {
                        Toast.makeText(EditTextActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 图片缩放
     *
     * @param originalBitmap 原始的Bitmap
     * @param newWidth       自定义宽度
     * @return 缩放后的Bitmap
     */
    private Bitmap resizeImage(Bitmap originalBitmap, int newWidth, int newHeight) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        //定义欲转换成的宽、高
//            int newWidth = 200;
//            int newHeight = 200;
        //计算宽、高缩放率
        float scanleWidth = (float) newWidth / width;
        float scanleHeight = (float) newHeight / height;
        //创建操作图片用的matrix对象 Matrix
        Matrix matrix = new Matrix();
        // 缩放图片动作
        // matrix.postScale(scanleWidth,scanleHeight);
        //旋转图片 动作
        //matrix.postRotate(45);
        // 创建新的图片Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, newWidth - 16, newWidth
                - 16, matrix, true);
        return resizedBitmap;
    }

}
