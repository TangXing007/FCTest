package hml.come.fucheng.activity;

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
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.activityMyselfChild.PersonDataActivity;
import hml.come.fucheng.adapter.LocationAdapter;
import hml.come.fucheng.moudle.CarListData;
import hml.come.fucheng.moudle.LocationData;
import hml.come.fucheng.net_work.BaseResult;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by TX on 2017/7/19.
 */
@RuntimePermissions
public class ApplyForJoin extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    private TextView textView, location, commit_button;
    private ImageView cameraImage;
    private EditText company_name, fuzeren, phone, password;
    private byte[] bytes;
    private boolean isPhone;
    private LinearLayout person_choose, apply_dealer, apply_resource;
    private String mark = null;
    private RadioButton one, two;
    private PopupWindow popupWindow;
    private View pop, headView;
    private ListView car_listView;
    private RadioButton head_button;
    private ProgressBar progressBar;
    private LocationData listData;
    private LocationAdapter adapter;
    private OptionsPickerView pvOptions;
    private ArrayList<String> optionItem1, optionItem2, optionItem3;
    private int id;
    private String Sid;
    private ImageView eyes;
    private Bitmap bitmap;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_for_join);
        immersion();
        back();
        Intent intent = getIntent();
        mark = intent.getStringExtra("mark");
        textView = (TextView)findViewById(R.id.title_text);
        textView.setText("申请加入");
        person_choose = (LinearLayout)findViewById(R.id.person_choose);
        apply_dealer = (LinearLayout)findViewById(R.id.apply_for_dealer_choose);
        apply_dealer.setOnClickListener(this);
        apply_resource = (LinearLayout)findViewById(R.id.apply_for_resource_choose);
        apply_resource.setOnClickListener(this);
        one = (RadioButton)findViewById(R.id.choose_one);
        two = (RadioButton)findViewById(R.id.choose_two);
        company_name = (EditText)findViewById(R.id.qiyemingcheng);
        location = (TextView) findViewById(R.id.location);
        location.setOnClickListener(this);
        fuzeren = (EditText)findViewById(R.id.fuzeren);
        phone = (EditText)findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.denglu_password);
        cameraImage = (ImageView)findViewById(R.id.camera_iamge);
        cameraImage.setOnClickListener(this);
        commit_button = (TextView)findViewById(R.id.submit_button);
        commit_button.setOnClickListener(this);
        eyes = (ImageView)findViewById(R.id.apply_eye);
        eyes.setOnTouchListener(this);
        if (mark == null || mark.equals("")){
            person_choose.setVisibility(View.VISIBLE);
        }else {
            person_choose.setVisibility(View.GONE);
        }
        if (CustomInfo.getInfo().getType().equals("1")){
            two.setChecked(true);
            one.setChecked(false);
            apply_dealer.setClickable(false);
            apply_resource.setClickable(true);
        }else if (CustomInfo.getInfo().getType().equals("2")){
            one.setChecked(true);
            two.setChecked(false);
            apply_resource.setClickable(false);
            apply_dealer.setClickable(true);
        }
        chooseLocation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camera_iamge:
                changeHeadIcon();
                break;
            case R.id.location:
                pop();
                break;
            case R.id.submit_button:
                String number = phone.getText().toString();
                isPhone = isPhone(number);
                if (company_name == null || company_name.equals("") || location == null ||
                        location.equals("") || fuzeren == null || fuzeren.equals("") ||
                        phone == null || phone.equals("") || password == null || password.equals("")
                        || bytes == null || bytes.equals("") || mark == null || mark.equals("")){
                    Toast.makeText(ApplyForJoin.this, "信息不能为空", Toast.LENGTH_SHORT).show();
                }else if (isPhone == false){
                    Toast.makeText(ApplyForJoin.this, "请输入正确手机号码", Toast.LENGTH_SHORT).show();
                }
                else {
                    imageCommit();
                }
                break;
            case R.id.apply_for_dealer_choose:
                mark = "10";
                one.setChecked(true);
                two.setChecked(false);
                break;
            case R.id.apply_for_resource_choose:
                mark = "9";
                one.setChecked(false);
                two.setChecked(true);
                break;
        }
    }


    private void changeHeadIcon(){
        final CharSequence[] items = {"相册", "拍照"};
        AlertDialog dialog = new AlertDialog.Builder(ApplyForJoin.this)
                .setTitle("选择图片")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            ApplyForJoinPermissionsDispatcher.photoAlbumWithCheck(ApplyForJoin.this);
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
        Toast.makeText(ApplyForJoin.this,
                "获取权限失败，可能无法打开相册", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                        cameraImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, length));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cameraImage.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                break;
            case 3:
                if (data != null ){
                    bitmap = data.getParcelableExtra("data");
                    if (bitmap != null){
                        cameraImage.setImageBitmap(bitmap);
                        cameraImage.setScaleType(ImageView.ScaleType.FIT_XY);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        bytes = byteArrayOutputStream.toByteArray();
                    }
                }
                break;
        }
    }

    private void imageCommit(){
        RequestParams imageparams = new RequestParams();
        String a = CustomInfo.getInfo().getUser_id();
        imageparams.put("userId", CustomInfo.getInfo().getUser_id());
        imageparams.put("COMPANY_NAME", company_name.getText());
        imageparams.put("QUYU", Sid);
        imageparams.put("USERNAME", fuzeren.getText());
        imageparams.put("PHONE", phone.getText());
        imageparams.put("PASSWORD", password.getText());
        imageparams.put("HEAD_IMG", new ByteArrayInputStream(bytes));
        imageparams.put("ROLE_ID", mark);
        HttpClient.get_istance().post(NetUrl.APPLYJOIN_TEST, imageparams,
                new JsonHttpResponseHandler(){
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        Gson gson = new Gson();
                        BaseResult data = gson.fromJson(response.toString(), BaseResult.class);
                        if (data.code.equals("1")){
                            Toast.makeText(ApplyForJoin.this,"提交成功,请等待后台审核",
                                    Toast.LENGTH_SHORT).show();
                            company_name.setText("");
                            location.setText("");
                            fuzeren.setText("");
                            phone.setText("");
                            password.setText("");
                            cameraImage.setImageResource(R.mipmap.tit19);
                            bitmap.recycle();
                            finish();
                        }else if (data.code.equals("3")){
                            Toast.makeText(ApplyForJoin.this,"此手机号码已被注册",
                                    Toast.LENGTH_SHORT).show();
                        }else if (data.code.equals("2")){
                            Toast.makeText(ApplyForJoin.this,"注册失败,请重试",
                            Toast.LENGTH_SHORT).show();
                        }

                    }
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, JSONObject errorResponse) {
                       Toast.makeText(ApplyForJoin.this,"提交失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ApplyForJoinPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private static boolean isPhone(String number){
        String num = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(number)){
            return false;
        }else {
            return number.matches(num);
        }
    }

    private void chooseLocation(){
        pop = LayoutInflater.from(this).inflate(R.layout.car_list_pop, null);
        popupWindow = new PopupWindow(pop, 900, 1700);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));
        popupWindow.setFocusable(true);
        popupWindow.update();
        car_listView = (ListView)pop.findViewById(R.id.car_listView);
        headView = LayoutInflater.from(this).inflate(R.layout.car_list_head_view, null);
        head_button = (RadioButton)headView.findViewById(R.id.car_head_button);
        progressBar = (ProgressBar)pop.findViewById(R.id.progressBar);
        HttpClient.get_istance().post(NetUrl.LOCATION_LIST, null, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                Gson gson = new Gson();
                listData = gson.fromJson(response.toString(), LocationData.class);
                if(listData.data != null && listData.data.size() !=  0){
                    adapter = new LocationAdapter(listData.data, ApplyForJoin.this);
                    progressBar.setVisibility(View.GONE);
                    car_listView.setVisibility(View.VISIBLE);
                }
                if(adapter != null){
                    car_listView.addHeaderView(headView);
                    car_listView.setAdapter(adapter);
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                String a = "eroor";
            }
        });
    }

    private void pop(){
        if (listData != null && listData.data != null && listData.data.size() != 0){
            progressBar.setVisibility(View.GONE);
            car_listView.setVisibility(View.VISIBLE);
            for (LocationData.Data item : listData.data){
                if (item.check != false){
                    head_button.setChecked(false);
                }
            }
        }else {
            car_listView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        popupWindow.showAtLocation(location, Gravity.CENTER, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        car_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    popupWindow.dismiss();
                }else {
                    for (LocationData.Data check : listData.data){
                        check.check = false;
                    }
                    location.setText(listData.data.get(position - 1).cityname);
                    location.setTextColor(getResources().getColor(R.color.huiXXX));
                    location.setTextSize(16);
                    Sid = listData.data.get(position - 1).id + "";
                    listData.data.get(0).check = false;
                    head_button.setChecked(false);
                    listData.data.get(position - 1).check = true;
                    popupWindow.dismiss();
                }
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.apply_eye:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        password.setSelection(password.getText().length());
                        break;
                    case MotionEvent.ACTION_UP:
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        password.setSelection(password.getText().length());
                        break;
                }
                break;
        }
        return true;
    }
}
