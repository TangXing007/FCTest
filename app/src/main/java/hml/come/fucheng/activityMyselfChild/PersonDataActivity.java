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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.Base64;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.activity.CircleImageView;
import hml.come.fucheng.moudle.PersonData;
import hml.come.fucheng.moudle.SalesManagementListData;
import hml.come.fucheng.net_work.FuChenHttpHandler;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by TX on 2017/7/27.
 */
@RuntimePermissions
public class PersonDataActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout pet_name, head_portrait;
    private TextView pet_name_text, person_number, title_text;
    private CircleImageView person_data_image;
    private LinearLayout back_button;
    private byte[] bytes;
    private String imageBASE64;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_person_data);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.bj));
        }
        personHttp();
        pet_name = (LinearLayout)findViewById(R.id.pet_name);
        pet_name.setOnClickListener(this);
        pet_name_text = (TextView)findViewById(R.id.pet_name_text);
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("个人资料");
        person_number = (TextView)findViewById(R.id.person_number);
        head_portrait = (LinearLayout)findViewById(R.id.head_portrait);
        head_portrait.setOnClickListener(this);
        person_data_image = (CircleImageView) findViewById(R.id.peson_data_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pet_name:
                Intent amendIntent = new Intent(PersonDataActivity.this, AmendActivity.class);
                startActivityForResult(amendIntent, 1);
                break;
            case R.id.back_button:
                finish();
                break;
            case R.id.head_portrait:
                changeHeadIcon();
                break;
        }
    }

    private void changeHeadIcon(){
        final CharSequence[] items = {"相册", "拍照"};
        AlertDialog dialog = new AlertDialog.Builder(PersonDataActivity.this)
                .setTitle("选择图片")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            PersonDataActivityPermissionsDispatcher.photoAlbumWithCheck
                                    (PersonDataActivity.this);
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
        Toast.makeText(PersonDataActivity.this,
                "获取权限失败，可能无法打开相册", Toast.LENGTH_SHORT).show();
    }

    public void personHttp(){
        RequestParams params = new RequestParams();
        params.put("id", CustomInfo.getInfo().getUser_id());
        HttpClient.get_istance().post(NetUrl.PERSON_DATA, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Type listType = new TypeToken<LinkedList<PersonData>>(){}.getType();
                Gson gson = new Gson();
                LinkedList<PersonData> list = gson.fromJson(response.toString(), listType);
                if (list.size() != 0 && list.get(0).head != null && !list.get(0).head.equals("")){
                    pet_name_text.setText(list.get(0).name);
                    person_number.setText(list.get(0).phone);
                    String a = list.get(0).head;
                    Picasso.with(PersonDataActivity.this).load(NetUrl.TEST_LF_HEAD+
                            list.get(0).head).into(person_data_image);
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (requestCode != 0 && data != null){
                    String name = data.getStringExtra("pet_name");
                    pet_name_text.setText(name);
                }
                break;
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
                        person_data_image.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, length));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //person_data_image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    person_data_image.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageCommit();
                }
                break;
            case 3:
                if (data != null ){
                    Bitmap bitmap = data.getParcelableExtra("data");
                    if (bitmap != null){
                        person_data_image.setImageBitmap(bitmap);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        bytes = byteArrayOutputStream.toByteArray();
                        imageCommit();
                    }
                }
                break;
        }
    }

    private void imageCommit(){
        RequestParams imageparams = new RequestParams();
        imageparams.put("id", CustomInfo.getInfo().getUser_id());
        imageparams.put("head", new ByteArrayInputStream(bytes));
        HttpClient.get_istance().post(NetUrl.PERSON, imageparams,
                new JsonHttpResponseHandler(){
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        Toast.makeText(PersonDataActivity.this, "更新头像成功", Toast.LENGTH_SHORT).show();
                    }
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                        String b = "false";
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PersonDataActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

}
