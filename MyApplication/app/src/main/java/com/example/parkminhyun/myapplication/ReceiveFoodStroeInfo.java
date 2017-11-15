package com.example.parkminhyun.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ReceiveFoodStroeInfo extends Service {

    private final Context context;
    private String htmlPageUrl;
    private String htmlContentInStringFormat;

    public ReceiveFoodStroeInfo(Context context, String searchText){
        this.context = context;
        this.htmlPageUrl = searchText;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements links = doc.select("a.name");

                for(Element e: links){
                    htmlContentInStringFormat += ("download: " + e.text()) + '\n';
                }

//


//                for (org.jsoup.nodes.Element link : links) {
//                    htmlContentInStringFormat += (link.attr("abs:href")
//                            + "("+link.text().trim() + ")\n");
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }



}
