package com.example.harryjiang.restfulwebserviceexample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getData = (Button) findViewById(R.id.getServiceData);
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String restURL = "www.androidexample.com/media/webservice/data/JsonReturn.php";
                new RESTOperation().execute(restURL);
            }
        });
    }

    private class RESTOperation extends AsyncTask<String, Void, Void> {

        String content, error, data = "";
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        TextView serverDataReceived = (TextView) findViewById(R.id.serverDataReceived), showParsedJSON = (TextView) findViewById(R.id.showParsedJSON);
        EditText userInput = (EditText) findViewById(R.id.userInput);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Please wait...");
            progressDialog.show();
            try {
                data += "&" + URLEncoder.encode("data", "UTF-8") + "=" + userInput.getText();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            BufferedReader br = null;
            URL url;
            try {
                url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWriter.write(data);
                outputStreamWriter.flush();
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                }
            } catch (MalformedURLException e) {
                error = e.getMessage();
                e.printStackTrace();
            } catch (IOException e) {
                error = e.getMessage();
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if (error != null)
                serverDataReceived.setText("Error " + error);
            else {
                serverDataReceived.setText(content);
                String output = "";
                JSONObject jsonResponse;
                try {
                    jsonResponse = new JSONObject(content);
                    JSONArray jsonArray = jsonResponse.optJSONArray("Android");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject child = jsonArray.getJSONObject(i);
                        String name = child.getString("name"), number = child.getString("number"), time = child.getString("date_added");
                        output = "Name = " + name + System.getProperty("line.separator") + number + System.getProperty("line.separator") + time;
                        output += System.getProperty("line.separator");
                    }
                    showParsedJSON.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
