package kumc.app.phi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import kumc.app.entity.MedRecord;
import kumc.app.fragment.PlaceholderFragment;
import kumc.app.view.FragmentWrapper;


public class MainActivity extends PhiActivity {

    private FragmentWrapper fragmentWrapper;
    private List<MedRecord> medRecords ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        needData()
        if (savedInstanceState == null) {
            fragmentWrapper = FragmentWrapper.getFragmentWrapper();

            fragmentWrapper.addFragment(1, new PlaceholderFragment())
                           .addFragment(2, new PlaceholderFragment());

            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    protected handleData(){
        
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



}
