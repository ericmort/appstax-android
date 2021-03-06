package com.appstax.appstagram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.appstax.AxFile;
import com.appstax.AxObject;
import com.appstax.android.Appstax;
import com.appstax.android.Callback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class FeedItem {

    private Appstax ax;
    private AxObject object;

    FeedItem(Appstax ax, AxObject object) {
        this.ax = ax;
        this.object = object;
    }

    public String getTitle() {
        return this.object.getObject("user").getString("name");
    }

    public String getSubtitle() {
        DateFormat df = new SimpleDateFormat("MMMM dd @ HH:mm");
        return df.format(object.getCreated());
    }

    public void getImage(final ImageView image) {
        AxFile file = object.getFile("image");

        if (file.getData() != null) {
            setData(image, file.getData());
            return;
        }

        ax.load(file, new Callback<AxFile>() {
            public void onSuccess(AxFile output) {
                setData(image, output.getData());
            }

            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setData(final ImageView image, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        image.setImageBitmap(bitmap);
    }

}
