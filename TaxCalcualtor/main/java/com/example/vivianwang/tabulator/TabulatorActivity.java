package com.example.vivianwang.tabulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TabulatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabulator);
    }

    public void buttonClicked(View v){

        View FromView = findViewById(R.id.FromBox);
        EditText FromEdit = (EditText) FromView;
        double income1 = Double.parseDouble(FromEdit.getText().toString());

        View ToView = findViewById(R.id.ToBox);
        EditText ToEdit = (EditText) ToView;
        double income2 = Double.parseDouble(ToEdit.getText().toString());

        View IncView = findViewById(R.id.IncBox);
        EditText IncEdit = (EditText) IncView;
        double inc = Double.parseDouble(IncEdit.getText().toString());


        TaxModel model = new TaxModel();

        View outputView = findViewById(R.id.tableLayout);
        TableLayout output = (TableLayout) outputView;
        output.removeAllViews();

        TableRow header = new TableRow(this);

        TextView taxLabel = new TextView(this);
        taxLabel.setText("INCOME");
        header.addView(taxLabel);

        taxLabel = new TextView(this);
        taxLabel.setText("TAX");
        header.addView(taxLabel);

        taxLabel = new TextView(this);
        taxLabel.setText("AVG");
        header.addView(taxLabel);

        taxLabel = new TextView(this);
        taxLabel.setText("MGN");
        header.addView(taxLabel);

        taxLabel = new TextView(this);
        taxLabel.setText("CPP");
        header.addView(taxLabel);

        taxLabel = new TextView(this);
        taxLabel.setText("EI");
        header.addView(taxLabel);

        output.addView(header);


        for (double i = income1; i <= income2; i = i + inc) {

            model.setIncome(i);

            TableRow cal = new TableRow(this);

            TextView data = new TextView(this);
            data.setText(Double.toString(i));
            cal.addView(data);

            data = new TextView(this);
            data.setText(model.getTax());
            cal.addView(data);

            data = new TextView(this);
            data.setText(model.getAverageRate());
            cal.addView(data);

            data = new TextView(this);
            data.setText(model.getMarginalRate());
            cal.addView(data);

            data = new TextView(this);
            data.setText(model.getCPP());
            cal.addView(data);

            data = new TextView(this);
            data.setText(model.getEI());
            cal.addView(data);

            output.addView(cal);
        }
    }
}
