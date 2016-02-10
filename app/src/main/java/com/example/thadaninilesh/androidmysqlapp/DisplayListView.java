package com.example.thadaninilesh.androidmysqlapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String jsonString;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ContactAdapter contactAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view_layout);
        listView = (ListView)findViewById(R.id.listView);

        contactAdapter = new ContactAdapter(this, R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        jsonString = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String name,email,mobile;

            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                email = JO.getString("email");
                mobile = JO.getString("mobile");
                ContactsList contactsList = new ContactsList(name,email,mobile);
                contactAdapter.add(contactsList);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
