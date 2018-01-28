package pokatomnik.tk.mylist;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Danilian on 28.01.2018.
 */

public final class UpdateUIRunnable implements Runnable {

    private MainActivity activity;
    private DateFormat dateFormat;
    private long currentTimestamp;
    private long startTimestamp;

    public UpdateUIRunnable(MainActivity activity,
                            DateFormat dateFormat,
                            long currentTimestamp,
                            long startTimestamp) {
        this.activity = activity;
        this.dateFormat = dateFormat;
        this.currentTimestamp = currentTimestamp;
        this.startTimestamp = startTimestamp;
    }

    @Override
    public void run() {
        activity.updateTimerLabel(dateFormat.format(
                new Date(currentTimestamp - startTimestamp)
        ));
    }
}