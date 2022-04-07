package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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

    private int serviceStatus = Constants.SERVICE_STOPPED;

    private IntentFilter intentFilter = new IntentFilter();


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
            Toast t = Toast.makeText(getApplicationContext(),
                    "Toast message " + intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA),
                    Toast.LENGTH_LONG);
            t.show();
        }
    }

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
//                if(serviceStatus == Constants.SERVICE_STOPPED) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
                    intent.putExtra(Constants.FIRST_NUMBER, firstOp);
                    intent.putExtra(Constants.SECOND_NUMBER, secondOp);
                    getApplicationContext().startService(intent);
                    serviceStatus = Constants.SERVICE_STARTED;
//                }
//

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
//                if(serviceStatus == Constants.SERVICE_STOPPED) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
                    intent.putExtra(Constants.FIRST_NUMBER, firstOp);
                    intent.putExtra(Constants.SECOND_NUMBER, secondOp);
                    getApplicationContext().startService(intent);
                    serviceStatus = Constants.SERVICE_STARTED;
//                }

            }
        });
//
        navigateToSecondaryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                String op = operation.getText().toString();
                intent.putExtra(Constants.NUMBER_OF_CLICKS, op);
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
        });

        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.OP_1, firstEditText.getText().toString());
        savedInstanceState.putString(Constants.OP_2, secondEditText.getText().toString());
        savedInstanceState.putString(Constants.RESULT, operation.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.OP_1)) {
            firstEditText.setText(savedInstanceState.getString(Constants.OP_1));
        } else {
            firstEditText.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey(Constants.OP_2)) {
            secondEditText.setText(savedInstanceState.getString(Constants.OP_2));
        } else {
           secondEditText.setText(String.valueOf(0));
        }

        if (savedInstanceState.containsKey(Constants.RESULT)) {
           operation.setText(savedInstanceState.getString(Constants.RESULT));
        } else {
            operation.setText(String.valueOf(0));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        super.onDestroy();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }


}