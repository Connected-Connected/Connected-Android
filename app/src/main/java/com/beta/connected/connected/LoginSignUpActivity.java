package com.beta.connected.connected;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.beta.connected.connected.Ajax.Login.LoginAjax;
import com.beta.connected.connected.Module.File.FileManager;
import com.beta.connected.connected.Module.File.FileTransfer;
import com.beta.connected.connected.Module.Image.ImageAdapter;
import com.beta.connected.connected.Sqlite.ConnectedDbHelper;
import com.beta.connected.connected.Sqlite.Table.User;

import java.util.HashMap;

/**
 * Created by x-note on 2017-02-18.
 */

public class LoginSignUpActivity extends AppCompatActivity {

    private static final int TAKE_CAMERA = 0;
    private static final int TAKE_GALLERY = 1;
    private static final int AFTER_CROP = 2;

    /* 필요 모듈 선언*/
    private FileTransfer mFileTransfer;//파일전송매니저
    private FileManager mFileManager;//파일입출력매니저
    private ImageAdapter mImageAdapter;//이미지처리매니저

    private LoginAjax ajax;
    private String userToken;
    private String userImg = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcR6n0FO3ri6-HCE8hqEqGJQwxAfUf4fVxwiBb7q3vJofRRRbB8Dtg";
    private int userSex = 1; //1남자 2여자
    private int userAge = 0;
    private String userContry = "";
    private String userProfile = "";
    private int profileSize = 0;

    //선택한 사진들의 경로
    private HashMap<Integer, String> mProfilePath = new HashMap<>();

    //ImageView 아이디 배열
    private int[] mimageViews = {R.id.thumbNailImage};


    private int profileMaxSize = 100;

    Integer ages[];
    String contry[] = {"Korea","United States","Japan","China"};
    /*  */
    private Button userSexMan;
    private Button userSexWoman;

    private Button userAgeBtn;
    private Button userContryBtn;
    private ImageView userAgeCheck;
    private ImageView userContryCheck;
    private TextView profileSizeTxt;
    private EditText profileTxt;
    private ScrollView scrollView;
    private EditText userNickName;
    private ImageView userImgView;
    private Button signUpBtn;
    private TextView userNmTxt;
    private ImageView userNmCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Bundle bundle = getIntent().getExtras();
        userToken = bundle.getString("userToken");
        userImg = bundle.getString("userImg");

        ajax = new LoginAjax(this);
        mFileTransfer =  new FileTransfer(this);
        mFileManager = new FileManager(FileManager.FileType.image);
        mImageAdapter = new ImageAdapter(this);

        //썸네일 초기화
        ajax.id(R.id.thumbNailImage).image(userImg);

        initializeEventHandler();
    }


    private void initializeEventHandler(){


        /* scrollview */
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        /* 프로필 사진 */
        userImgView = (ImageView) findViewById(R.id.thumbNailImage);
        userImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //카메라, 앨범, 취소 다이얼로그 띄우기
                showImageDialog();
            }
        });

        /* 닉네임 ime 이벤트 */
        userNmTxt = (TextView) findViewById(R.id.userNmTxt);
        userNmCheck = (ImageView) findViewById(R.id.checkNickName);
        userNickName = (EditText) findViewById(R.id.userNickName);
        userNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String userNm = charSequence.toString();
                if(userNm.length() > 3)
                {
                    ajax.checkOverlapUserName(userNm, new AjaxCallback<String>(){
                        @Override
                        public void callback(String url, String result, AjaxStatus status) {

                            if(result != null) {
                                if(result.trim().equals("OK")){
                                    userNmTxt.setText("");
                                    userNmTxt.setTextColor(Color.parseColor("#03A9F4"));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        userNmCheck.setImageDrawable(getDrawable(R.drawable.circle_correct));
                                    }
                                }
                                else {
                                    userNmTxt.setText("이미 존재하는 닉네임 입니다.");
                                    userNmTxt.setTextColor(Color.parseColor("#FF0000"));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        userNmCheck.setImageDrawable(getDrawable(R.drawable.circle_incorrect));
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    userNmTxt.setText("3글자 이상 입력하세요.");
                    userNmTxt.setTextColor(Color.parseColor("#FF0000"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        userNmCheck.setBackground(getDrawable(R.drawable.circle_incorrect));
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        userNickName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_NEXT:
                        userAgeBtn.callOnClick();
                        break;
                }
                return true;
            }
        });

        /*성별 클릭 이벤트*/
        userSexMan = (Button) findViewById(R.id.sexMan);
        userSexWoman = (Button) findViewById(R.id.sexWoman);

        userSexMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여자면 change
                if(userSex == 2)
                {
                    userSex = 1;
                    userSexMan.setBackgroundColor(Color.parseColor("#03A9F4"));
                    userSexMan.setTextColor(Color.parseColor("#ffffff"));

                    userSexWoman.setBackgroundColor(Color.parseColor("#ffffff"));
                    userSexWoman.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
        userSexWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //남자면 change
                if(userSex == 1)
                {
                    userSex = 2;
                    userSexWoman.setBackgroundColor(Color.parseColor("#F8CACC"));
                    userSexWoman.setTextColor(Color.parseColor("#ffffff"));

                    userSexMan.setBackgroundColor(Color.parseColor("#ffffff"));
                    userSexMan.setTextColor(Color.parseColor("#000000"));
                }
            }
        });


        /* 나이 선택 spinner */
        userAgeCheck = (ImageView) findViewById(R.id.checkAge);
        userAgeBtn = (Button) findViewById(R.id.ageSelector);
        userAgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final NumberPicker agePicker = new NumberPicker(getBaseContext());

                agePicker.setMaxValue(64);
                agePicker.setMinValue(14);
                agePicker.setWrapSelectorWheel(true);
                if(userAge == 0)
                {
                    agePicker.setValue(20);
                }
                else {
                    agePicker.setValue(userAge);
                }

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(LoginSignUpActivity.this);
                builderSingle.setTitle("본인의 나이를 선택하세요.");
                builderSingle.setView(agePicker);
                builderSingle.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userAge = agePicker.getValue();
                        userAgeBtn.setText(userAge + "");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            userAgeCheck.setImageDrawable(getDrawable(R.drawable.circle_correct));
                        }
                        userContryBtn.callOnClick();
                    }
                });
                builderSingle.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
//                builderSingle.setAdapter(ageArray, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        userAge = ageArray.getItem(i);
//
//                    }
//                });

                builderSingle.show();
            }
        });


        /* 국적 선택 */
        final ArrayAdapter<String> contryArray = new ArrayAdapter<>(this,   android.R.layout.simple_list_item_1, contry);
        contryArray.setDropDownViewResource(android.R.layout.simple_list_item_1); // The drop down view
        userContryCheck = (ImageView) findViewById(R.id.checkContry);
        userContryBtn = (Button) findViewById(R.id.contrySelector);
        userContryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(LoginSignUpActivity.this);
                builderSingle.setTitle("본인의 국적을 선택하세요.");
                builderSingle.setAdapter(contryArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userContry = contryArray.getItem(i);
                        userContryBtn.setText(userContry + "");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            userContryCheck.setImageDrawable(getDrawable(R.drawable.circle_correct));
                        }
                        /*프로필작성에 포커스주고 키보드 띄우기*/
                        profileTxt.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

                    }
                });

                builderSingle.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builderSingle.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builderSingle.show();
            }
        });

        //글자수제한막기 > 현재 xml로 되어있음
        /*
        * final EditText edit = new EditText(this);
          edit.setInputType(InputType.TYPE_CLASS_NUMBER); //숫자만 입력
          edit.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) }); //글자수 제한
        * */
        /* 프로필 작성 */
        profileSizeTxt = (TextView) findViewById(R.id.profileSize);
        profileSizeTxt.setText("("+profileSize+"/"+profileMaxSize+")");
        profileTxt = (EditText) findViewById(R.id.profileTxt);
        profileTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //scrollView.arrowScroll()
                    //scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    profileSize = profileTxt.getText().length();
                    profileSizeTxt.setText("("+profileSize+"/"+profileMaxSize+")");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /* 가입하기 버튼 */
        signUpBtn = (Button) findViewById(R.id.signUp);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(profileTxt.getWindowToken(), 0);

                //flag를 하나씩 두기
                if(userNmCheck.getBackground() == getDrawable(R.drawable.circle_incorrect))
                {
                    Toast.makeText(getBaseContext(), "닉네임을 확인하세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(userAgeCheck.getBackground() == getDrawable(R.drawable.circle_incorrect))
                {
                    Toast.makeText(getBaseContext(), "나이를 입력하세요.", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(userContryCheck.getBackground() == getDrawable(R.drawable.circle_incorrect))
                {
                    Toast.makeText(getBaseContext(), "국적을 선택하세요.", Toast.LENGTH_LONG).show();
                    return;
                }


                //내부 DB에 저장 > 지워도 됨
//                ConnectedDbHelper conn = new ConnectedDbHelper(getBaseContext());
//                SQLiteDatabase db = conn.getWritableDatabase();
//                User user = new User();
//                user.setToken(userToken);
//                user.setName(userNickName.getText().toString());
//                user.setImg("");
//                user.setSex(userSex);
//                user.setAge(userAge);
//                user.setContry(userContry);
//                user.setProfile(userProfile);
//                user.insert(db);
//
//                //메인화면으로 이동
//                Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);

                fileUpload();
            }
        });
    }


    // 카메라, 앨범, 취소 다이얼로그 SHOW
    private void showImageDialog(){

        mImageAdapter.showDialogWithGalleryNCamera(
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //권한획득 필요
                        //boolean checkPermission = mPermManager.checkPermission(requestPermission, id);
                        //if(checkPermission){
                            mImageAdapter.getPicture(id);
                        //}

                        dialog.dismiss();

                    }
                }
        );

    }


    //이미지 RESULT 함수
    protected void onActivityResult( int requestCode, int resultCode, Intent data )   {
        if( resultCode == RESULT_OK )
        {
            Uri mImageCaptureUri;
            switch (requestCode){
                // 앨범에서 가져올 때
                case TAKE_GALLERY:
                    // 카메라 촬영 시
                case TAKE_CAMERA:
                    //Crop한 이미지를 저장할 Temp파일 생성
                    mImageCaptureUri = mFileManager.createTempFile(mImageAdapter.getRealPathFromURI(data.getData(), this));
                    mImageAdapter.cropImageFromUri(AFTER_CROP, mImageCaptureUri);
                    break;
                // 잘라내기 후
                case AFTER_CROP:
                    //잘려진 이미지 경로 받아오기
                    mImageCaptureUri = data.getData();

                    ImageView clickImageView = null;
                    //선택했던 이미지뷰에 이미지 설정
                    clickImageView = (ImageView) findViewById(R.id.thumbNailImage);
                    mImageAdapter.setAlbumImage( mImageCaptureUri.getPath(), clickImageView );

                    //이미지 경로 저장
                    mProfilePath.put(R.id.thumbNailImage, mImageCaptureUri.getPath());
            }
        }
    }

    //파일 업로드 함수
    private void fileUpload(){

        //파일 추가
        String file_path;
        for(int i=0; i<mimageViews.length; i++){
            file_path = mProfilePath.get(mimageViews[i]);
            if(mProfilePath.get(mimageViews[i]) != null)
            {
                mFileTransfer.AddFile(file_path, userToken+".jpg");
            }
        }

        //파일 전송
        mFileTransfer.fileTransferWithPath(new Handler() {
            //파일 전송 결과 Handler
            public void handleMessage(Message msg) {

                fileUploadComplete();
                //오류처리 및 경로 받아오는 로직으로 바꿔야함
//                switch(msg.what) {
//                    //파일 전송 중 에러 발생시
//                    case -1:
//                        //에러메세지 출력
//                        ((Exception)msg.obj).printStackTrace();
//                        break;
//                    //정상 처리시
//                    case 0:
//                        fileUploadComplete();
//                        break;
//                }
            }
        });
    }


    //파일이 모두 업로드 됐으면 메인으로 이동
    private void fileUploadComplete(){

        //Temp 파일 모두 삭제
        mFileManager.deleteTempDir();

        userProfile = profileTxt.getText().toString();

        ajax.saveUserInfo(
                userToken,
                userNickName.getText().toString(),
                "",//잠깐
                userSex,
                userAge,
                userContry,
                userProfile,
                new AjaxCallback<String>(){
                    @Override
                    public void callback(String url, String result, AjaxStatus status) {
                        if(result != null) {
                            if(result.trim().equals("OK")){

                                new Thread(){
                                    @Override
                                    public void run() {
                                        super.run();
                                        //내부 DB에 저장
                                        ConnectedDbHelper conn = new ConnectedDbHelper(getBaseContext());
                                        SQLiteDatabase db = conn.getWritableDatabase();
                                        User user = new User();
                                        user.setToken(userToken);
                                        user.setName(userNickName.getText().toString());
                                        user.setImg("");
                                        user.setSex(userSex);
                                        user.setAge(userAge);
                                        user.setContry(userContry);
                                        user.setProfile(userProfile);
                                        user.insert(db);

                                        //메인화면으로 이동
                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }.start();
                            }
                        }
                    }
        });


    }
    //endregion
}
