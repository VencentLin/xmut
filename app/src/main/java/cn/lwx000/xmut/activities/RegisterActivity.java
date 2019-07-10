package cn.lwx000.xmut.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.lwx000.xmut.R;
import cn.lwx000.xmut.beans.User;

//todo 复制布局文件2个
public class RegisterActivity extends HomeAsUpBaseActivity {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final TextView titleView = findViewById(R.id.tv_title);
        titleView.setText("注册");
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

    }

    public void register(final View view){
        final String userName = editText.getText().toString();
        String password = editText2.getText().toString();
        String email = editText3.getText().toString();
        if(TextUtils.isEmpty(userName)){
            editText.setError("账号不能为空");
            return;
        }
        if(TextUtils.isEmpty(password)){
            editText2.setError("密码不能为空");
            return;
        }
        if(TextUtils.isEmpty(email)){
            editText3.setError("email不能为空");
            return;
        }
        final User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Intent data = new Intent();
                    data.putExtra("username",userName);
                    setResult(RESULT_OK,data);
                    RegisterActivity.this.finish();
                } else {
                    Snackbar.make(view, "注册失败：" + e.getMessage(),
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

}
