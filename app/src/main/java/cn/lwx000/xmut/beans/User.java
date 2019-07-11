package cn.lwx000.xmut.beans;

import androidx.annotation.NonNull;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {

    private BmobFile headImage;
    private boolean sex;
    private String nickName;
    private String info;

    @NonNull
    @Override
    public String toString() {
        return "User{"+
                "headImage="+ headImage +
                ",sex=" + sex +
                ",nickName'" + nickName + '\'' +
                ",info='" + info + '\'' +
                '}';
    }

    public BmobFile getHeadImage() {
        return headImage;
    }

    public void setHeadImage(BmobFile headImage) {
        this.headImage = headImage;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
