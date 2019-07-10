package cn.lwx000.xmut.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.lwx000.xmut.R;

//todo 复制布局文件2个
public class FindPasswordActivity extends HomeAsUpBaseActivity {
    private EditText editText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        final TextView titleView = findViewById(R.id.tv_title);
        titleView.setText("找回密码");
        editText = findViewById(R.id.editText);

    }

    public void findPassword(final View view){

        final String email = editText.getText().toString();
        if(TextUtils.isEmpty(email)){
            editText.setError("邮箱不能为空");
            return;
        }
        BmobUser.resetPasswordByEmail(email, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Snackbar.make(view, "重置密码请求成功，请到" + email
                            + "邮箱进行密码重置操作", Snackbar.LENGTH_LONG).show();
                } else {
                    Log.e("BMOB", e.toString());
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}
