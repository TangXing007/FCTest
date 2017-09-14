package hml.come.fucheng.activityMyselfChild;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.net_work.BaseResult;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by TX on 2017/7/31.
 */
@RuntimePermissions
public class UserFeedbackActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout back_button;
    private TextView commit_button, title_text;
    private EditText feedback_text;
    private ImageView feedback_image;
    private byte[] bytes;
    private Bitmap bitmap;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfeedback);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.bj));
        }
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        commit_button = (TextView)findViewById(R.id.feedback_commit_button);
        commit_button.setOnClickListener(this);
        feedback_text = (EditText)findViewById(R.id.feedback_text);
        feedback_image = (ImageView)findViewById(R.id.feedback_image);
        feedback_image.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("用户反馈");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.feedback_image:
                changeHeadIcon();
                break;
            case R.id.feedback_commit_button:
                if (feedback_text.getText().toString() == null || feedback_text.getText().toString()
                        .equals("") ||
                        bytes == null || bytes.equals("")){
                    Toast.makeText(UserFeedbackActivity.this, "反馈信息不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    commitSuggest();
                }
                break;
        }
    }

    private void changeHeadIcon(){
        final CharSequence[] items = {"相册", "拍照"};
        AlertDialog dialog = new AlertDialog.Builder(UserFeedbackActivity.this)
                .setTitle("选择图片")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            UserFeedbackActivityPermissionsDispatcher
                                    .photoAlbumWithCheck(UserFeedbackActivity.this);
                        }else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 3);
                        }
                    }
                }).create();
        dialog.show();
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void  photoAlbum(){
        Intent intent=new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void photoAlbumWrong(){
        Toast.makeText(UserFeedbackActivity.this,
                "获取权限失败，可能无法打开相册", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case  2:
                if (data != null){
                    Uri imageUri = data.getData();
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(imageUri, filePath, null, null, null);
                    cursor.moveToFirst();
                    int imageIndex = cursor.getColumnIndex(filePath[0]);
                    String picturePath = cursor.getString(imageIndex);
                    cursor.close();
                    FileInputStream inputStream;
                    try {
                        File file = new File(picturePath);
                        inputStream = new FileInputStream(file);
                        int length = inputStream.available();
                        bytes = new byte[length];
                        inputStream.read(bytes);
                        feedback_image.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, length));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    feedback_image.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                break;
            case 3:
                if (data != null && resultCode != 0 ){
                    bitmap = data.getParcelableExtra("data");
                    if (bitmap != null){
                        feedback_image.setImageBitmap(bitmap);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        bytes = byteArrayOutputStream.toByteArray();
                        feedback_image.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UserFeedbackActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void commitSuggest(){
        RequestParams params = new RequestParams();
        params.put("userid", CustomInfo.getInfo().getUser_id());
        params.put("con", feedback_text.getText().toString());
        params.put("head_picture", new ByteArrayInputStream(bytes));
        HttpClient.get_istance().post(NetUrl.COMMIT_SUGGEST, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                bitmap.recycle();
                BaseResult data = gson.fromJson(response.toString(), BaseResult.class);
                if (data.code.equals("1")){
                    feedback_text.setText("");
                    feedback_image.setImageResource(R.mipmap.feedback_image);
                    Toast.makeText(UserFeedbackActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UserFeedbackActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                String a = "aaa";
            }
        });
    }
}
