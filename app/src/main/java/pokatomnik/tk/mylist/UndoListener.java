/**
 * Created by Danilian on 28.01.2018.
 */

package pokatomnik.tk.mylist;

import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public final class UndoListener implements View.OnClickListener {
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    UndoListener(ArrayAdapter<String> adapter, ArrayList<String> list) {
        this.list = list;
        this.adapter = adapter;
    }

    @Override
    public void onClick(View view) {
        int lastIndex, size = list.size();

        if (size == 0) {
            return;
        }

        lastIndex = size - 1;

        list.remove(lastIndex);
        adapter.notifyDataSetChanged();
    }
}
