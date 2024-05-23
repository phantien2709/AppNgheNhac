package com.example.nhaconline.Model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Baihat implements Parcelable {

@SerializedName("Idbaihat")
@Expose
public String idbaihat;
@SerializedName("Tenbaihat")
@Expose
public String tenbaihat;
@SerializedName("Hinhbaihat")
@Expose
public String hinhbaihat;
@SerializedName("Casi")
@Expose
public String casi;
@SerializedName("Linkbaihat")
@Expose
public String linkbaihat;
@SerializedName("Luotthich")
@Expose
private String luotthich;

private String favorite;
    public Baihat(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linkbaihat = in.readString();
        luotthich = in.readString();
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
        }
    };

    public Baihat(String idbaihat, String tenbaihat, String casi, String hinhbaihat) {
        this.idbaihat = idbaihat;
        this.tenbaihat = tenbaihat;
        this.hinhbaihat = hinhbaihat;
        this.casi = casi;
    }

    public Baihat(int id, String tenBaiHat, String casi, String hinh) {

    }

    public Baihat(String idbaihat, String tenbaihat, String casi, String hinhbaihat, String linkbaihat) {
        this.idbaihat = idbaihat;
        this.tenbaihat = tenbaihat;
        this.hinhbaihat = hinhbaihat;
        this.casi = casi;
        this.linkbaihat = linkbaihat;
    }

    public String getIdbaihat() {
return idbaihat;
}

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getTenbaihat() {
return tenbaihat;
}

public void setTenbaihat(String tenbaihat) {
this.tenbaihat = tenbaihat;
}

public String getHinhbaihat() {
return hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.hinhbaihat = hinhbaihat;
}

public String getCasi() {
return casi;
}

public void setCasi(String casi) {
this.casi = casi;
}

public String getLinkbaihat() {
return linkbaihat;
}

public void setLinkbaihat(String linkbaihat) {
this.linkbaihat = linkbaihat;
}

public String getLuotthich() {
return luotthich;
}

public void setLuotthich(String luotthich) {
this.luotthich = luotthich;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(idbaihat);
        parcel.writeString(tenbaihat);
        parcel.writeString(hinhbaihat);
        parcel.writeString(casi);
        parcel.writeString(linkbaihat);
        parcel.writeString(luotthich);
    }
}