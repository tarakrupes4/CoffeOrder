package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox Whippedcream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean haswhippedcream = Whippedcream.isChecked();
        CheckBox choco = (CheckBox) findViewById(R.id.choco_cream);
        boolean haschoco = choco.isChecked();
        EditText name1 = (EditText) findViewById(R.id.text_name);
       String nam = name1.getText().toString();
       int price =  calPrice(quantity,haswhippedcream,haschoco);
       String priceMessage = orderSummary(price,haswhippedcream,nam,haschoco);
       
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "just java order for" +nam);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(priceMessage);

    }

    public String orderSummary(int price,boolean haswhippedcream,String name,boolean haschoco)
    {

        String name2 = name;
        int total = price;
        String disp = "Name: "+name2;
          disp += "\n is whipped cream added?"+haswhippedcream;
        disp += "\n is choco added?"+haschoco;
        disp += "\n quantity: "+quantity;
        disp +="\n total price: "+total;
        disp +="\n Thankyou!";
        return disp;

    }
    public int calPrice(int quantity,boolean addwhipped,boolean addchoco)
    {
        int price = quantity*5;
        if(addwhipped==true)
        {
            price +=quantity*1;
        }
        if(addchoco==true)
            price += quantity * 2;
        return price;
    }

    public void increment(View view)
    {
        if(quantity<=100)
        {
            quantity += 1;
        }

        display(quantity);

    }
    public void decrement(View view)
    {
        if(quantity==1)
        {
            return;
        }

        quantity -= 1;

        display(quantity);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}