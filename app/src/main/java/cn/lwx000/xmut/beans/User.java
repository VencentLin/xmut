package cn.lwx000.xmut.beans;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {
    private BmobFile headImage;
    private boolean sex;
    private String nickName;
    private String info;

    public BmobFile getHeadimage() {
        return headImage;
    }

    public User setHeadimage(BmobFile headimage) {
        this.headImage = headimage;
        return this;
    }

    public boolean isSex() {
        return sex;
    }

    public User setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public User setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public User setInfo(String info) {
        this.info = info;
        return this;
    }
}
