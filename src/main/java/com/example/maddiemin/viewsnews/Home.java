package com.example.maddiemin.viewsnews;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Home extends AppCompatActivity {

    String query = "";
    String resp;
    boolean isAbc_clicked = false;
    boolean isBreitbart_clicked = false;
    boolean isBI_clicked = false;
    boolean isNyt_clicked = false;
    boolean isWsj_clicked = false;
    boolean isAp_clicked = false;
    boolean isWapo_clicked = false;
    private RequestQueue mRequestQueue;

    RequestQueue queue;
    String apiKey = "23212ddf68d2408f8c8946f25b6cb1fc";
    TextView mTestText;

    List<Article> myData = new ArrayList<Article>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button mPolButton = (Button)findViewById(R.id.politics_button);
        Button mGloButton = (Button)findViewById(R.id.global_button);
        Button mSpoButton = (Button)findViewById(R.id.sports_button);
        mTestText = (TextView)findViewById(R.id.trend_text);

        final Button mAbcButton = (Button)findViewById(R.id.abc_button);
        final Button mBreitbartButton = (Button)findViewById(R.id.breitbart_button);
        final Button mBIButton = (Button)findViewById(R.id.businessInsider_button);
        final Button mNytButton = (Button)findViewById(R.id.nyt_button);
        final Button mWsjButton = (Button)findViewById(R.id.wsj_button);
        final Button mApButton = (Button)findViewById(R.id.ap_button);
        final Button mWapoButton = (Button)findViewById(R.id.wapo_button);
        queue = Volley.newRequestQueue(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(myData);
        mRecyclerView.setAdapter(mAdapter);

        mPolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mGloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mSpoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mAbcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAbc_clicked) { //news source is deselected
                    isAbc_clicked = false;
                    mAbcButton.setPressed(false);
                    lookUnpressed(mAbcButton);
                    removeArticles("abc-news-au");
                }
                else {                      //news source is selected
                    isAbc_clicked = true;
                    mAbcButton.setPressed(true);
                    lookPressed(mAbcButton);
                    loadNews("abc-news-au");
                }
            }
        });
        mBreitbartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBreitbart_clicked) {
                    isBreitbart_clicked = false;
                    mBreitbartButton.setPressed(false);
                    lookUnpressed(mBreitbartButton);
                    removeArticles("breitbart-news");
                }
                else {
                    isBreitbart_clicked = true;
                    mBreitbartButton.setPressed(true);
                    lookPressed(mBreitbartButton);
                    loadNews("breitbart-news");

                }
            }
        });
        mBIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBI_clicked) {
                    mBIButton.setPressed(false);
                    isBI_clicked = false;
                    lookUnpressed(mBIButton);
                    removeArticles("business-insider");
                }
                else {
                    isBI_clicked = true;
                    mBIButton.setPressed(true);
                    lookPressed(mBIButton);
                    loadNews("business-insider");
                }
            }
        });
        mNytButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNyt_clicked) {
                    mNytButton.setPressed(false);
                    isNyt_clicked = false;
                    lookUnpressed(mNytButton);
                    removeArticles("the-new-york-times");
                }
                else {
                    isNyt_clicked = true;
                    mNytButton.setPressed(true);
                    lookPressed(mNytButton);
                    loadNews("the-new-york-times");
                }
            }
        });
        mWsjButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWsj_clicked) {
                    mWsjButton.setPressed(false);
                    isWsj_clicked = false;
                    lookUnpressed(mWsjButton);
                    removeArticles("the-wall-street-journal");
                }
                else {
                    isWsj_clicked = true;
                    mWsjButton.setPressed(true);
                    lookPressed(mWsjButton);
                    loadNews("the-wall-street-journal");
                }
            }
        });
        mApButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAp_clicked) {
                    mApButton.setPressed(false);
                    isAp_clicked = false;
                    lookUnpressed(mApButton);
                    removeArticles("associated-press");
                }
                else {
                    isAp_clicked = true;
                    mApButton.setPressed(true);
                    lookPressed(mApButton);
                    loadNews("associated-press");
                }
            }
        });
        mWapoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWapo_clicked) {
                    mWapoButton.setPressed(false);
                    isWapo_clicked = false;
                    lookUnpressed(mWapoButton);
                    removeArticles("the-washington-post");
                }
                else {
                    isWapo_clicked = true;
                    mWapoButton.setPressed(true);
                    lookPressed(mWapoButton);
                    loadNews("the-washington-post");
                }
            }
        });
    }
    public void lookPressed (Button btn){
        btn.setHeight(btn.getHeight() + 14);
        btn.getBackground().setColorFilter(new LightingColorFilter(0xFF34344A, 0x00000000));
    }
    public void lookUnpressed (Button btn){
        btn.setHeight(btn.getHeight() - 14);
        btn.setPadding(btn.getPaddingLeft(), btn.getPaddingTop() - 2, btn.getPaddingRight(), btn.getPaddingBottom() - 2);
        btn.getBackground().setColorFilter(null);
    }
    public void loadNews (String news_src) {
        resp = "beginning loadNews()";
        String url = "https://newsapi.org/v1/articles?source=" + news_src + "&apiKey=" + apiKey;

        final String src = news_src;
        final String[] headlines = {"", "", "", "", ""};
        final String[] descriptions = {"", "", "", "", ""};
        final String[] urls = {"", "", "", "", ""};
        final String[] imageUrls = {"", "", "", "", ""};

        JsonObjectRequest jsObjReq = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        resp = "Request pending.";
                        try {
                             JSONArray articleArr = response.getJSONArray("articles");
                             for(int i=0; i<5; i++) {
                                 JSONObject jsonObject = (JSONObject) articleArr.get(i);
                                 headlines[i] = jsonObject.getString("title");
                                 descriptions[i] = jsonObject.getString("description");
                                 urls[i] = jsonObject.getString("url");
                                 imageUrls[i] = jsonObject.getString("urlToImage");
                                 Article newArticle = new Article(src, headlines[i], descriptions[i], urls[i], imageUrls[i]);
                                 addArticle(newArticle);
                             }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resp = "GET Request Error";
                    }
                });
        queue.add(jsObjReq);

    }
    public void addArticle(Article article){
        myData.add(article);
        alphabetizeArticles();
        mAdapter.notifyDataSetChanged();
    }
    public void removeArticles (String news_src){
        for(int i=0; i<myData.size(); i++){
            if(myData.get(i).getSrc().equals(news_src)){
                myData.remove(i);
                mAdapter.notifyDataSetChanged();
                i--;
            }
        }
    }
    public void alphabetizeArticles(){
        Collections.sort(myData, new articleComparator());
        /*
        //Quicksort
        if (low < high) {
                int p = parts(arr, low, high);
                arr = alphabetizeArticles(arr, low, p - 1);
                arr = alphabetizeArticles(arr, p + 1, high);
            return null;
        }
        else{
            return arr;
        }*/
    }
    /*public int parts (List<Article> arr, int low, int high)
    {
        Article piv = arr.get(high);
        int smaller = (low - 1);
        for (int i = low; i <= high - 1; i++)
        {
            if (arr.get(i).getHead().compareTo(piv.getHead()) <= 0 )
            {
                smaller++;
                switchArt(arr, i, smaller);
            }
        }
        switchArt(arr, smaller + 1, high);
        return (smaller + 1);
    }
    public void switchArt(List<Article> arr, int a, int b){
        Article temp = myData.get(a);
        myData.set(a, myData.get(b));
        myData.set(b, temp);
    }*/
}
