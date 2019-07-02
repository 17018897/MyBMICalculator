package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight;
    EditText etHeight;
    TextView tvDate;
    TextView tvBMI;
    Button btnCal;
    Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        tvDate = findViewById(R.id.textViewDate);
        tvBMI = findViewById(R.id.textViewBMI);
        btnCal = findViewById(R.id.buttonCal);
        btnReset = findViewById(R.id.buttonReset);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double Weight = Double.parseDouble(etWeight.getText().toString());
                double Height = Double.parseDouble(etHeight.getText().toString());
                double bmi = Weight /(Height * Height);

                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

             tvDate.setText("Last Calculated Date: "+ datetime);
             tvBMI.setText("Last Calculated BMI: "+ bmi);
             etWeight.setText("");
             etHeight.setText("");

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWeight.setText("");
                etHeight.setText("");
                tvBMI.setText("Last Calculated BMI:");
                tvDate.setText("Last Calculated Date:");
            }
        });

    }
    protected void onPause() {
        super.onPause();

        String date = tvDate.getText().toString();
        String bmi = tvBMI.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("Date", date);
        prefEdit.putString("Bmi", bmi);

        prefEdit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String Date = prefs.getString("Date", "Last Calculated Date:");
        String Bmi = prefs.getString("Bmi", "Last Calculated BMI:");


        tvDate.setText(Date);
        tvBMI.setText(Bmi);

    }
}
