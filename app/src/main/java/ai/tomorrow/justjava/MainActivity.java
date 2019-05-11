package ai.tomorrow.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippered_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean haschoco = chocolateCheckBox.isChecked();
        EditText name_EditText = (EditText) findViewById(R.id.name_EditText);
        String name = name_EditText.getText().toString();

        Log.v("MainActivity", "has whipped cream: " + hasWhippedCream);
        Log.v("MainActivity", "has chocolate: " + haschoco);

        String priceMessage = CreateOrderSummary(hasWhippedCream, haschoco, name);
        displayMessage(priceMessage);
    }

    private String CreateOrderSummary(boolean hasWhippedCream, boolean haschoco, String name){
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + haschoco;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + quantity * 5;
        priceMessage += "\n Thank you!";
        return priceMessage;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        quantity--;
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView oerderSummayTextView = (TextView) findViewById(R.id.order_summary_text_view);
        oerderSummayTextView.setText(message);
    }

}
