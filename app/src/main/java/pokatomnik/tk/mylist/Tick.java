package pokatomnik.tk.mylist;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Danilian on 28.01.2018.
 */

public final class Tick extends TimerTask {
    private Date startDate;
    private DateFormat dateFormat;
    private MainActivity activity;

    public Tick(Date startDate, DateFormat dateFormat, MainActivity activity) {
        this.startDate = startDate;
        this.dateFormat = dateFormat;
        this.activity = activity;
    }

    @Override
    public void run() {
        if (startDate == null || dateFormat == null) {
            return;
        }
        final long currentTimestamp = new Date().getTime();
        final long startTimestamp = startDate.getTime();

        activity.runOnUiThread(
                new UpdateUIRunnable(
                        activity,
                        dateFormat,
                        currentTimestamp,
                        startTimestamp
                )
        );
    }
}
