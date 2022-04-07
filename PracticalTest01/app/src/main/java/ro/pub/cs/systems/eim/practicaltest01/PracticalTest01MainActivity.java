package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private EditText firstEditText;
    private EditText secondEditText;
    private TextView operation;
    private Button addButton, minusButton;
    private Button navigateToSecondaryActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        firstEditText = (EditText) findViewById(R.id.operand_1);
        secondEditText = (EditText) findViewById(R.id.operand_2);

        addButton = (Button) findViewById(R.id.add_button);
        minusButton = (Button) findViewById(R.id.minus_button);

        operation = (TextView) findViewById(R.id.operation) ;

        navigateToSecondaryActivityButton = (Button) findViewById(R.id.navigate_to_secondary_activity_button);

        firstEditText.setText(String.valueOf(0));
       secondEditText.setText(String.valueOf(0));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int firstOp = Integer.valueOf(firstEditText.getText().toString());
                int secondOp = Integer.valueOf(secondEditText.getText().toString());
                int result = firstOp + secondOp;
                operation.setText(String.valueOf(firstOp) + " + " + String.valueOf(secondOp) + " = " + String.valueOf(result));

                // service start activation
//                if (leftNumberOfClicks + rightNumberOfClicks > Constants.NUMBER_OF_CLICKS_THRESHOLD
//                        && serviceStatus == Constants.SERVICE_STOPPED) {
//                    Intent intent = new Intent(getApplicationContext(), Test1ModelService.class);
//                    intent.putExtra(Constants.FIRST_NUMBER, leftNumberOfClicks);
//                    intent.putExtra(Constants.SECOND_NUMBER, rightNumberOfClicks);
//                    getApplicationContext().startService(intent);
//                    serviceStatus = Constants.SERVICE_STARTED;
//                }

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int firstOp = Integer.valueOf(firstEditText.getText().toString());
                int secondOp = Integer.valueOf(secondEditText.getText().toString());
                int result = firstOp - secondOp;
                operation.setText(String.valueOf(firstOp) + " - " + String.valueOf(secondOp) + " = " + String.valueOf(result));

                // service start activation
//                if (leftNumberOfClicks + rightNumberOfClicks > Constants.NUMBER_OF_CLICKS_THRESHOLD
//                        && serviceStatus == Constants.SERVICE_STOPPED) {
//                    Intent intent = new Intent(getApplicationContext(), Test1ModelService.class);
//                    intent.putExtra(Constants.FIRST_NUMBER, leftNumberOfClicks);
//                    intent.putExtra(Constants.SECOND_NUMBER, rightNumberOfClicks);
//                    getApplicationContext().startService(intent);
//                    serviceStatus = Constants.SERVICE_STARTED;
//                }

            }
        });
//
//        navigateToSecondaryActivityButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
//                int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) +
//                        Integer.parseInt(rightEditText.getText().toString());
//                intent.putExtra(Constants.NUMBER_OF_CLICKS, numberOfClicks);
//                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
//            }
//        });

    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putString(Constants.LEFT_COUNT, leftEditText.getText().toString());
//        savedInstanceState.putString(Constants.RIGHT_COUNT, rightEditText.getText().toString());
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
//            leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
//        } else {
//            leftEditText.setText(String.valueOf(0));
//        }
//        if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
//            rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
//        } else {
//            rightEditText.setText(String.valueOf(0));
//        }
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
//            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
//        }
//    }


}