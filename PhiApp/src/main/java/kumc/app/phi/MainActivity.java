package kumc.app.phi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import kumc.app.async.AsyncWork;
import kumc.app.async.HttpTask;
import kumc.app.async.TaskHandler;
import kumc.app.constant.URL;
import kumc.app.container.MedRecordContainer;
import kumc.app.fragment.BaseFragment;
import kumc.app.fragment.MedicalPartFragment;
import kumc.app.sample.CardFlipActivity;
import kumc.app.sample.CrossfadeActivity;
import kumc.app.view.FragmentWrapper;

import static kumc.app.container.MedRecordContainer.MedRecord;

public class MainActivity extends BaseActivity {

    private FragmentWrapper fragmentWrapper;
    private List<MedRecord> medRecords ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        if (savedInstanceState == null) {
            new HttpTask().call(URL.kumc.user_med_info, medRecordContainerTask ) ;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.amain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    TaskHandler medRecordContainerTask = new TaskHandler<MedRecordContainer>() {
        @Override
        public void handle(MedRecordContainer container) {
            Log.d("TAG","TEST");

        }
    };

    AsyncWork WorkMedRecords = new AsyncWork<MedRecord>() {
        protected List<MedRecord> doInBackground(Object params) {
            // The connection URL
            String url = "https://ajax.googleapis.com/ajax/" +
                    "services/search/web?v=1.0&q={query}";

        // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            // Add the String message converter
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());


        // Make the HTTP GET request, marshaling the response to a String
            ResponseEntity<MedRecord[]> list = restTemplate.getForEntity(url, MedRecord[].class);
            MedRecord[] medRecords = list.getBody();

            return Arrays.asList(medRecords);
        }

        @Override
        protected void onPostExecute(List<MedRecord> medRecords) {
            fragmentWrapper = FragmentWrapper.getFragmentWrapper();
            for ( MedRecord mr : medRecords ) {
                BaseFragment fm = new MedicalPartFragment();
                fm.setBaseData(medRecords);
                fragmentWrapper.addFragment(fm ) ;
            }
        }

    };

    private class TaskMedRecords extends AsyncTask<Void, Void, MedRecordContainer> {
        @Override
        protected MedRecordContainer doInBackground(Void... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                MedRecordContainer medRecords = restTemplate.getForObject(URL.kumc.user_med_info, MedRecordContainer.class);
                return medRecords;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(MedRecordContainer medRecords) {
//            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
//            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
//            greetingIdText.setText(medRecords.getId());
//            greetingContentText.setText(medRecords.getContent());
        }

    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.bt_cardflip:
                Intent intent1 = new Intent(this, CardFlipActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_crossfade:
                Intent intent2 = new Intent(this, CrossfadeActivity.class);
                startActivity(intent2);
                break;
        }

    }

}
