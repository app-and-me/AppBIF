package kr.hs.e_mirim.cheong.anif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 수현 on 2018-05-27.
 */

public class MyListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ListItem> list_itemArrayList;

    TextView name_textView;
    TextView date_textView;
    TextView text_textView;

    public MyListAdapter(Context context, ArrayList<ListItem> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            name_textView = (TextView)convertView.findViewById(R.id.listTextView);
            text_textView = (TextView)convertView.findViewById(R.id.listTitleView);
            date_textView = (TextView)convertView.findViewById(R.id.listDateView);

            name_textView.setText(list_itemArrayList.get(position).getName());
            text_textView.setText(list_itemArrayList.get(position).getText().toString());
            date_textView.setText(list_itemArrayList.get(position).getDate().toString());
        }
        return convertView;
    }
}
