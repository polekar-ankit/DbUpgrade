package at.com.dbupgrade;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Creted by User on 08-Feb-17
 */

public class ListAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> complaintArrayList;
    private Context context;
    private int resource;

    public ListAdapter(Context context, int resource, ArrayList<Student> complaintArrayList) {
        super(context, resource, complaintArrayList);
        this.complaintArrayList = complaintArrayList;
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;
        if (row == null) {
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            row = layoutInflater.inflate(resource, null);
            holder = new Holder();
            holder.textViewId = (TextView) row.findViewById(R.id.textView_id);
            holder.textViewName = (TextView) row.findViewById(R.id.textView_name);
            holder.textViewRollNo = (TextView) row.findViewById(R.id.textView_roll_no);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        Student student = complaintArrayList.get(position);
        holder.textViewId.setText("" + student.getnId());
        holder.textViewName.setText(student.getsName());
        if (student.getnRollNo() != -1) {
            holder.textViewRollNo.setText("" + student.getnRollNo());
        }


        return row;
    }

    private static class Holder {
        TextView textViewId;
        TextView textViewName;
        TextView textViewRollNo;

    }
}
