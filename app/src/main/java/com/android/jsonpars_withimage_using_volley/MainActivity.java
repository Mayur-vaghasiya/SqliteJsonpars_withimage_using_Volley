package com.android.jsonpars_withimage_using_volley;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private static final String url = "https://raw.githubusercontent.com/mobilesiri/Android-Custom-Listview-Using-Volley/master/richman.json";
    ProgressDialog pDialog;
    private static final String TAG = MainActivity.class.getSimpleName();

    ArrayList<Forbsbillenior> forbsbilleniorslist = new ArrayList<Forbsbillenior>();

    ListView listView;
    Listadapter adapter;
    DatabaseHandler dbhandler;

    //private static final String TAG_NAME = "name";
    //  private static final String TAG_IMAGE = "image";
    //  private static final String TAG_WORTH = "worth";
    //  private static final String TAG_YEAR = "InYear";
    // private static final String TAG_SOURCES = "source";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhandler = new DatabaseHandler(this);
        listView = (ListView) findViewById(R.id.list);
        NetworkUtils utils = new NetworkUtils(MainActivity.this);
        //  RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Forbsbillenior forbsbillenior = forbsbilleniorslist.get(position);

                Intent in = new Intent(getApplicationContext(), singleActivity.class);
                in.putExtra("name", forbsbillenior.getName());
                in.putExtra("worth", forbsbillenior.getWorth());
                in.putExtra("source", forbsbillenior.getSources());
                in.putExtra("InYear", forbsbillenior.getYear());
                in.putExtra("image", forbsbillenior.getPhoto());

                startActivity(in);
            }
        });
        /*pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();*/

        if (dbhandler.getCount() == 0 && utils.isNetworkAvailable(this)) {

            // Creating volley request obj

             JsonArrayRequest billionaireReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);

                                    Forbsbillenior forbsbilleniors = new Forbsbillenior();
                                    forbsbilleniors.setName(obj.getString("name"));
                                    forbsbilleniors.setPhoto(obj.getString("image"));
                                    forbsbilleniors.setWorth(obj.getString("worth"));
                                    forbsbilleniors.setYear(obj.getInt("InYear"));
                                    forbsbilleniors.setSources(obj.getString("source"));

                                    // adding Billionaire to worldsBillionaires array
                                    dbhandler.Adduser(forbsbilleniors);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            getdata();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();
                }

            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(billionaireReq);
        } else {
            getdata();
        }

        /*queue.add(billionaireReq);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public void getdata() {
        forbsbilleniorslist = dbhandler.getbillionior();
        adapter = new Listadapter(MainActivity.this, forbsbilleniorslist);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}