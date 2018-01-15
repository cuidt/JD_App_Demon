package jd.com.jd_app_demon.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jd.com.jd_app_demon.R;
import jd.com.jd_app_demon.activity.LoginActivity;
import jd.com.jd_app_demon.activity.SetActivity;
import jd.com.jd_app_demon.bean.GainBean;
import jd.com.jd_app_demon.bean.HandPortraitBean;
import jd.com.jd_app_demon.present.UserPresenter;
import jd.com.jd_app_demon.util.MySharedPreferences;
import jd.com.jd_app_demon.view.IUserView;

public class MyFragment extends Fragment implements IUserView {


    Unbinder unbinder;
    @BindView(R.id.user_tx)
    SimpleDraweeView userTx;
    @BindView(R.id.LoginorLeg)
    TextView LoginorLeg;

    private int uid;
    private String imagePath;
    private UserPresenter userPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        unbinder = ButterKnife.bind(this, view);

        userPresenter = new UserPresenter(this);
        userPresenter.getUser();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.user_tx, R.id.my_login, R.id.sz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_tx:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
                break;
            case R.id.my_login:
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                getContext().startActivity(intent2);
                break;
            case R.id.sz:
                Intent intent3 = new Intent(getActivity(), SetActivity.class);
                getContext().startActivity(intent3);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 0:
                startPhotoZoom(data.getData());
                break;
            //点击进行相册图片的选择，进行缩放
            case 1:
                if (data == null) {
                    return;
                }
                imagePath = setImageToView(data);
                userPresenter.loadHandPortrait();
                //获取本地的图片地址，进行设置完成的时候图片的展示
                Uri uri = Uri.parse("file://" + imagePath);
                userTx.setImageURI(uri);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", true);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }

    public String setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        String path = null;
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            path = savePhoto(photo, Environment
                    .getExternalStorageDirectory().getAbsolutePath(), String
                    .valueOf(System.currentTimeMillis()));
        }
        return path;
    }


    public String savePhoto(Bitmap photoBitmap, String path, String photoName) {
        String localPath = null;
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File photoFile = new File(path, photoName + ".png");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100,
                            fileOutputStream)) { // 转换完成
                        localPath = photoFile.getPath();
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                localPath = null;
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        fileOutputStream = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return localPath;
    }

    @Override
    public int getUid() {
        return MySharedPreferences.getInt("uid", 0);
    }

    @Override
    public void isAddShop(GainBean gainBean) {
        //uid = gainBean.getData().getUid();
        // uid = MySharedPreferences.getInt("uid", 0);
        String username = gainBean.getData().getUsername();
        userTx.setImageURI(gainBean.getData().getIcon());
        LoginorLeg.setText(username);
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void setLoadHandPortrait(HandPortraitBean bean) {
        Toast.makeText(getActivity(), bean.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
