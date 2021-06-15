package com.example.sneakershop01;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sneakershop01.adapters.ChartAdapter;
import com.example.sneakershop01.adapters.ProductsAdapter;
import com.example.sneakershop01.entities.Chart;
import com.example.sneakershop01.entities.OrderDetails;
import com.example.sneakershop01.entities.Orders;
import com.example.sneakershop01.entities.Products;
import com.example.sneakershop01.managers.ChartManager;
import com.example.sneakershop01.managers.OrderDetailsManager;
import com.example.sneakershop01.managers.OrdersManager;
import com.example.sneakershop01.managers.ProductsManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
public class ChartActivity extends AppCompatActivity {
    TextView totalchart;
    TextView quantitychart;
    ListView listView;
    Context ctx;
    Button button_buy;
    String userid;
    Double amountChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ctx = this;
        Intent infIntent = getIntent();
        userid = infIntent.getStringExtra("userid");
        //
        totalchart = (TextView) findViewById(R.id.totalchart);
        quantitychart = (TextView) findViewById(R.id.quantitychart);
        button_buy = (Button) findViewById(R.id.button_buy);
        //
        listView = (ListView) findViewById(R.id.ListSneakers);
        ChartAdapter chartAdapter = new ChartAdapter(this, R.layout.list_chart, ChartManager.getAll(ctx));
        listView.setAdapter(chartAdapter);
        button_buy.setEnabled(listView.getCount() > 0);
        //
        amountChart = ChartManager.gettotalchart(this);
        int quantityChart = ChartManager.getquantitychart(this);
        //
        NumberFormat Price = NumberFormat.getNumberInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("CAD$ ");
        ((DecimalFormat) Price).setDecimalFormatSymbols(dfs);
        String Price_Str = "\n Total Amount CAD$ " + Price.format(amountChart);
        totalchart.setText(Price_Str);
        //
        quantitychart.setText("Number of Products : " + String.valueOf(quantityChart));
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Record inf in DB}
                if (userid == null) userid = "";
                if (userid.length() == 0) userid = "0";
                //
                Orders order = new Orders();
                order.setUserid(Integer.parseInt(userid));
                order.setAmount(amountChart);
                order.setTax(0);
                order.setDate_order("20210223");
                //
                OrdersManager.add(ctx, order);
                //
                int orderid = OrdersManager.getlastid(ctx);
                //
                ArrayList<Chart> listchart = ChartManager.getAll(ctx);
                //
                for (Chart item : listchart) {
                    //
                    OrderDetails orderproduct = new OrderDetails(orderid, item.getProductid(), item.getQuantity());
                    //
                    OrderDetailsManager.add(ctx, orderproduct);
                    //
                }
                Toast.makeText(getApplicationContext(), "Order No. " + String.valueOf(orderid) + " was created", Toast.LENGTH_SHORT).show();
                ChartManager.deleteAll(ctx);
                chartAdapter.notifyDataSetChanged();
                refreshViewChart();
            }
        });
        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chart chart = (Chart) parent.getItemAtPosition(position);
                int pos = position;
                //
                // ------------------------------------------------
                LayoutInflater inflater = getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.chart_detail_layout, null);
                //
                //
                Button deletefromchart_button = (Button) dialoglayout.findViewById(R.id.deletefromchart_button);
                deletefromchart_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        ChartManager.delete(ctx, chart.getId());
                        //listchart_1.remove(pos);
                        //
                        chartAdapter.notifyDataSetChanged();
                        refreshViewChart();
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(ChartActivity.this);
                builder.setView(dialoglayout);
                builder.show();
                // ------------------------------------------------
            }
        });
    }
    private Boolean refreshViewChart() {
        //listView = (ListView) findViewById(R.id.ListSneakers);
        ChartAdapter chartAdapter = new ChartAdapter(ctx, R.layout.list_chart, ChartManager.getAll(ctx));
        listView.setAdapter(chartAdapter);
        //
        amountChart = ChartManager.gettotalchart(ctx);
        int quantityChart = ChartManager.getquantitychart(ctx);
        //
        NumberFormat Price = NumberFormat.getNumberInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("CAD$ ");
        ((DecimalFormat) Price).setDecimalFormatSymbols(dfs);
        String Price_Str = "\n Total Amount CAD$ " + Price.format(amountChart);
        totalchart.setText(Price_Str);
        //
        quantitychart.setText("Number of Products : " + String.valueOf(quantityChart));
        //
        return true;
    }
}