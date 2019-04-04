package gorkemkara.com.tr.hesapmakinesi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Observer;
import java.util.Observable;

/**
 * Created by GÖRKEM KARA on 05-04-2019.
 */

public class MainActivity extends Activity implements Observer, OnClickListener {


    private Calculator calculator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        calculator = new Calculator();
        calculator.addObserver(this);
        setOnClickButtonListener(R.id.button0);
        setOnClickButtonListener(R.id.button1);
        setOnClickButtonListener(R.id.button2);
        setOnClickButtonListener(R.id.button3);
        setOnClickButtonListener(R.id.button4);
        setOnClickButtonListener(R.id.button5);
        setOnClickButtonListener(R.id.button6);
        setOnClickButtonListener(R.id.button7);
        setOnClickButtonListener(R.id.button8);
        setOnClickButtonListener(R.id.button9);
        setOnClickButtonListener(R.id.plus_button);
        setOnClickButtonListener(R.id.minus_button);
        setOnClickButtonListener(R.id.multiply_button);
        setOnClickButtonListener(R.id.divide_button);
        setOnClickButtonListener(R.id.decimal_button);
        setOnClickButtonListener(R.id.equals_button);
        setOnClickButtonListener(R.id.clear_button);
        setOnClickButtonListener(R.id.open_bracket);
        setOnClickButtonListener(R.id.close_bracket);


    }

    //güncelleme mevzusu
    @Override
    public void update(Observable observable, Object data) {
        TextView display = (TextView) this.findViewById(R.id.display);
        Calculator calc = (Calculator) observable;

        display.setText(calc.getDisplay());

        if(calc.getMessage() != null) {
            Toast toast = Toast.makeText(getApplicationContext(), calc.getMessage(), Toast.LENGTH_SHORT);
            toast.show();

            calc.setMessage(null);
        }
    }


    private void setOnClickButtonListener(int id) {
        Button button = (Button) this.findViewById(id);
        button.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        Button button = (Button) v;

        switch (button.getId()) {
            case R.id.clear_button:
                calculator.clear();
                break;
            case R.id.plus_button:
                calculator.addition();
                break;
            case R.id.minus_button:
                calculator.subtraction();
                break;
            case R.id.multiply_button:
                calculator.multiplication();
                break;
            case R.id.divide_button:
                calculator.division();
                break;
            case R.id.decimal_button:
                calculator.appendDecimal();
                break;
            case R.id.equals_button:
                calculator.calculate();
                break;
            case R.id.open_bracket:
                calculator.appendOpenBracket();
                break;
            case R.id.close_bracket:
                calculator.appendCloseBracket();
                break;
            default:
                calculator.appendDisplay(button.getText().toString());
                break;
        }
    }

}
