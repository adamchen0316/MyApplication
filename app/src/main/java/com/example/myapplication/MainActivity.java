package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    private void upload() {
        Map<String, String> map = null;
        XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.default_remote_config);
        try {
            String TAG_KEY = "key";
            String TAG_VALUE = "value";
            String currentTag = "";
            String currentKey = "";
            int root = xmlResourceParser.getEventType();

            while (root != XmlResourceParser.END_DOCUMENT) {
                switch (root) {
                    case XmlResourceParser.START_TAG:
                        currentTag = xmlResourceParser.getName();
                        break;
                    case XmlResourceParser.TEXT:
                        if (currentTag.equals(TAG_KEY)) {
                            currentKey = xmlResourceParser.getText();
                        } else if (currentTag.equals(TAG_VALUE)) {
                            if (map == null) {
                                map = new HashMap<>();
                            }
                            map.put(currentKey, xmlResourceParser.getText());
                        }
                        break;
                }
                root = xmlResourceParser.next();
            }
            if (map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String mapKey = entry.getKey();
                    String mapValue = entry.getValue();
                    Log.i("dasda", mapKey + ":" + mapValue);
                }
            }


        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

    }

    public void onclick(View v) {
        upload();
    }
}
