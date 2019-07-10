package cn.lwx000.xmut.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.lwx000.xmut.R;
import cn.lwx000.xmut.beans.User;

//todo 复制布局文件2个
public class LoginActivity extends HomeAsUpBaseActivity {
    private EditText editText;
    private EditText editText2;
    private int REQUEST_CODE2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView titleView = findViewById(R.id.tv_title);
        titleView.setText("登陆");
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

    }

    public void login(final View view){
        final String userName = editText.getText().toString();
        String password = editText2.getText().toString();
        if(TextUtils.isEmpty(userName)){
            editText.setError("账号不能为空");
            return;
        }
        if(TextUtils.isEmpty(password)){
            editText.setError("密码不能为空");
            return;
        }

        User user = new User();
        //此处替换为你的用户名
        user.setUsername(userName);
        //此处替换为你的密码
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    User user = BmobUser.getCurrentUser(User.class);
                    Intent intent = new Intent();
                    intent.putExtra("userName",user.getUsername());
                    intent.putExtra("isLogin",true);
                    setResult(RESULT_OK,intent);
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    LoginActivity.this.finish();
                } else {
                    Snackbar.make(view, "登录失败：" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    public void register(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,REQUEST_CODE2);
    }
    public void forgetPassword(View view){
        Intent intent = new Intent(this,FindPasswordActivity.class);
        startActivity(intent);
    }


}
