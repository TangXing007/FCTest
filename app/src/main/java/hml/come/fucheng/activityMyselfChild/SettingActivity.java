package hml.come.fucheng.activityMyselfChild;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import de.greenrobot.event.EventBus;
import hml.come.fucheng.R;
import hml.come.fucheng.activity.BaseActivity;
import hml.come.fucheng.activity.CircleImageView;
import hml.come.fucheng.eventbus.FirstEventBus;
import hml.come.fucheng.moudle.PersonData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by TX on 2017/7/26.
 */
@RuntimePermissions
public class SettingActivity extends BaseActivity implements View.OnClickListener{
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private LinearLayout peson_data, intention_car, alter_password, service_clause, versions;
    private TextView outLanding_button, title_text;
    private LinearLayout back_button;
    private LinearLayout pet_name, head_portrait;
    private TextView pet_name_text, person_number;
    private CircleImageView person_data_image;
    private byte[] bytes;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        back();
        immersion();
        outLanding_button = (TextView) findViewById(R.id.outlanding_button);
        outLanding_button.setOnClickListener(this);
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("设置");
        peson_data = (LinearLayout)findViewById(R.id.peson_data);
        peson_data.setOnClickListener(this);
        intention_car = (LinearLayout)findViewById(R.id.intention_car);
        intention_car.setOnClickListener(this);
        alter_password = (LinearLayout)findViewById(R.id.alter_password);
        alter_password.setOnClickListener(this);
        personHttp();
        pet_name = (LinearLayout)findViewById(R.id.pet_name);
        pet_name.setOnClickListener(this);
        pet_name_text = (TextView)findViewById(R.id.pet_name_text);
        person_number = (TextView)findViewById(R.id.person_number);
        head_portrait = (LinearLayout)findViewById(R.id.head_portrait);
        head_portrait.setOnClickListener(this);
        person_data_image = (CircleImageView) findViewById(R.id.peson_data_image);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.outlanding_button:
                preferences = getSharedPreferences("data", MODE_PRIVATE);
                editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();
                EventBus.getDefault().post(new FirstEventBus("msg"));
                break;
            case R.id.peson_data:
                Intent personIntent = new Intent(SettingActivity.this, PersonDataActivity.class);
                startActivity(personIntent);
                break;
            case R.id.intention_car:
                Intent carIntent = new Intent(SettingActivity.this, IntentionCarActivity.class);
                startActivity(carIntent);
                break;
            case R.id.alter_password:
                Intent alterIntent = new Intent(SettingActivity.this, AlterPasswordActivity.class);
                startActivity(alterIntent);
                break;
            case R.id.pet_name:
                Intent amendIntent = new Intent(SettingActivity.this, AmendActivity.class);
                startActivityForResult(amendIntent, 1);
                break;
            case R.id.head_portrait:
                changeHeadIcon();
                break;
        }
    }

    private void changeHeadIcon(){
        final CharSequence[] items = {"相册", "拍照"};
        AlertDialog dialog = new AlertDialog.Builder(SettingActivity.this)
                .setTitle("选择图片")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            SettingActivityPermissionsDispatcher.photoAlbumWithCheck
                                    (SettingActivity.this);
                        }else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 3);
                        }
                    }
                }).create();
        dialog.show();
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
                    Picasso.with(SettingActivity.this).load(NetUrl.TEST_LF_HEAD+
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
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void  photoAlbum(){
        Intent intent=new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void photoAlbumWrong(){
        Toast.makeText(SettingActivity.this,
                "获取权限失败，可能无法打开相册", Toast.LENGTH_SHORT).show();
    }

    private void imageCommit(){
        RequestParams imageparams = new RequestParams();
        imageparams.put("id", CustomInfo.getInfo().getUser_id());
        imageparams.put("head", new ByteArrayInputStream(bytes));
        HttpClient.get_istance().post(NetUrl.PERSON, imageparams,
                new JsonHttpResponseHandler(){
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        Toast.makeText(SettingActivity.this, "更新头像成功", Toast.LENGTH_SHORT).show();
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
        SettingActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
