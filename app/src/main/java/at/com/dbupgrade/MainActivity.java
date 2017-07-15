package at.com.dbupgrade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * THIS IS SAMPLE CODE WHICH DEMONSTRATE HOW UPGRADE THE EXISTING DATA BASE
 * IN THIS SAMPLE WE HAVE ADDED NEW FILED IN TO THE EXISTING DATA BASE
 * TO VIEW THIS CHANGE INCREMENT DATA BASE VERSION FROM DataBaseHelper CLASS
 */
public class MainActivity extends AppCompatActivity {

    private DataBaseHelper baseHelper;
    private TextView textViewColumn;
    private ListView listView;
    private ListAdapter studentArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewColumn = (TextView) findViewById(R.id.column);
        listView = (ListView) findViewById(R.id.listView_data);

        baseHelper = new DataBaseHelper(this);
        displayColumnName();

        Student[] students = baseHelper.getStudentName();
        studentArrayAdapter = new ListAdapter(MainActivity.this, R.layout.layout_list_row, new ArrayList<Student>(Arrays.asList(students)));
        listView.setAdapter(studentArrayAdapter);

        findViewById(R.id.button_insert_sample_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseHelper.insert("student 1");
                baseHelper.insert("student 2");
                baseHelper.insert("student 3");
                baseHelper.insert("student 4");
                baseHelper.insert("student 5");
                baseHelper.insert("student 6");

                Student[] students = baseHelper.getStudentName();
                if (studentArrayAdapter != null)
                    studentArrayAdapter.clear();
                studentArrayAdapter = new ListAdapter(MainActivity.this, R.layout.layout_list_row, new ArrayList<Student>(Arrays.asList(students)));
                listView.setAdapter(studentArrayAdapter);
            }
        });
    }

    private void displayColumnName() {
        String[] columnName = baseHelper.getColumnName();
        String rowName = "Column names: ";
        for (String name :
                columnName) {
            rowName = rowName + "  " + name;
        }
        textViewColumn.setText(rowName);
    }
}
