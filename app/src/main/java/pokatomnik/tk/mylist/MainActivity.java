package pokatomnik.tk.mylist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Boolean isStarted = false;
    Date startDate = null;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.ENGLISH);
    TextView timeLabel;
    Timer timer = null;
    Tick tick = null;
    UndoListener undoListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timeLabel = findViewById(R.id.textView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        ((ListView)findViewById(R.id.listView)).setAdapter(adapter);

        undoListener = new UndoListener(adapter, list);
        updateTimerUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetDate() {
        startDate = new Date();
    }

    private void updateTimerUI() {
        FloatingActionButton startStopButton = findViewById(R.id.startStop);
        FloatingActionButton pickTimeButton = findViewById(R.id.add);

        if (isStarted) {
            startStopButton.setImageResource(R.drawable.ic_stop_white_24dp);
        } else {
            startStopButton.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        }
        pickTimeButton.setEnabled(isStarted);
    }

    private void startTimer() {
        timer = new Timer();
        tick = new Tick(startDate, dateFormat, this);
        timer.schedule(tick, 0, 50L);
    }

    public void updateTimerLabel(String text) {
        timeLabel.setText(text);
    }

    private void stopTimer() {
        tick.cancel();
        timer.cancel();
        tick = null;
        timer = null;
    }

    public void toggleStart(View view) {
        isStarted = !isStarted;
        if (isStarted) {
            clear(null);
            resetDate();
            startTimer();
        } else {
            startDate = null;
            stopTimer();
        }
        updateTimerUI();
    }

    public void onFABOnClick(View view) {
        long currentTimestamp = new Date().getTime();
        long startTimeStamp = startDate.getTime();
        String dateStr = dateFormat.format(new Date(currentTimestamp - startTimeStamp));

        adapter.add(dateStr);
        Snackbar
                .make(view, "Added time: " + dateStr, Snackbar.LENGTH_LONG)
                .setAction("UNDO", undoListener)
                .show();
    }

    public void clear(MenuItem item) {
        adapter.clear();
    }
}
