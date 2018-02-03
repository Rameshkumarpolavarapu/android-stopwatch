package pokatomnik.tk.mylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Danilian on 02.02.2018.
 */

public class RemovableArrayAdapter extends ArrayAdapter<String> {
    private ArrayList<String> baseItems;

    RemovableArrayAdapter(Context context, ArrayList<String> list) {
        super(context, 0, list);
        baseItems = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.remove_item_text, parent, false);
        }

        TextView tw = convertView.findViewById(R.id.tw);
        tw.setText(item);

        Button removeButton = convertView.findViewById(R.id.remove_item_button);
        final RemovableArrayAdapter self = this;

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.baseItems.remove(position);
                self.notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
