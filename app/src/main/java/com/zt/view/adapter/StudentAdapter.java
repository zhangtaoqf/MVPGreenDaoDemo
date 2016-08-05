package com.zt.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.db.Student;
import com.zt.R;

/**
 * Created by Administrator on 2016/8/5.
 */
public class StudentAdapter extends MBaseAdapter<Student> {
    public StudentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = getInflater().inflate(R.layout.item_student,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = ((ViewHolder) convertView.getTag());
        }

        Student item = getItem(position);

        viewHolder.textView.setText(item.toString());

        return convertView;
    }

    class ViewHolder
    {
        private TextView textView;
        public ViewHolder(View convertView)
        {
            textView = ((TextView) convertView.findViewById(R.id.item_textViewId));
        }
    }
}
