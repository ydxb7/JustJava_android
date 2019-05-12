package ai.tomorrow.justjava;

import android.content.Intent;
import android.net.Uri;
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

        int price = 5;
        if(hasWhippedCream)
            price++;
        if(haschoco)
            price += 2;

        String priceMessage = CreateOrderSummary(price, hasWhippedCream, haschoco, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMessage);
    }

    private String CreateOrderSummary(int price, boolean hasWhippedCream, boolean haschoco, String name){
        String priceMessage = getString(R.string.order_summary_name, name); // name用来填补 %s的内容
        priceMessage += "\n " + getString(R.string.Add_whipped_cream) + hasWhippedCream;
        priceMessage += "\n " + getString(R.string.Add_chocolate) + haschoco;
        priceMessage += "\n " + getString(R.string.quantity) + ": " + quantity;
        priceMessage += "\n " + getString(R.string.Total) + ": $" + quantity * price;
        priceMessage += "\n" + getString(R.string.thank_you);
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
        if(quantity <= 99)
            quantity++;
        else
            return;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity > 1)
            quantity--;
        else
            return;
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
