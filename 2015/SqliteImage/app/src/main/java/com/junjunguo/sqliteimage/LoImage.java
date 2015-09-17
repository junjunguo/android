package com.junjunguo.sqliteimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * This file is part of SqliteImage
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 9 Sep, 2015.
 */
public class LoImage extends MyImage {
    private Bitmap img;

    public LoImage(String title, String description, Bitmap img, long datetimeLong) {
        super(title, description, null, datetimeLong);
        this.img = img;
    }

    public LoImage() {
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    /**
     * convert and set byte [] img to Bitmap img
     *
     * @param img
     */
    public void setImg(byte[] img) {
        this.img = BitmapFactory.decodeByteArray(img, 0, img.length);
    }

    /**
     * convert the given bitmap to [] byte array
     */
    public byte[] getByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }

    /**
     * convert this.img bitmap to [] byte array
     */
    public byte[] getByteArray() {
        return getByteArray(getImg());
    }
}
