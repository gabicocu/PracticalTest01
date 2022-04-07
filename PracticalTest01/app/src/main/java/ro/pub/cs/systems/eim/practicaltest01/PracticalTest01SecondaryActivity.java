package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private TextView operation;
    private Button correctButton, incorrectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        operation = (TextView) findViewById(R.id.number_of_clicks_text_view);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_CLICKS)) {
          String val = intent.getStringExtra(Constants.NUMBER_OF_CLICKS);
          operation.setText(String.valueOf(val));
        }

        correctButton = (Button)findViewById(R.id.ok_button);
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, null);
                finish();
            }
        });

        incorrectButton = (Button)findViewById(R.id.cancel_button);
        incorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });
    }
}