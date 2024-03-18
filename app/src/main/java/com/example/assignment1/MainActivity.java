package com.example.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    TextView textView, textView2, textView3;
    Button button;
    Spinner source_spinner, destination_spinner;
    private Map<String, String> unitTypes;
    private Map<String, Double> conversionRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        editText1 = findViewById(R.id.editTextNumber1);
        editText2 = findViewById(R.id.editTextNumber2);

        button = findViewById(R.id.button);

        source_spinner = findViewById(R.id.source_spinner);
        destination_spinner = findViewById(R.id.destination_spinner);

        initializeUnitTypes();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        source_spinner.setAdapter(adapter);
        destination_spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sourceUnit = source_spinner.getSelectedItem().toString();
                    String destUnit = destination_spinner.getSelectedItem().toString();
                    double value = Double.parseDouble(editText1.getText().toString());
                    if (!unitTypes.get(sourceUnit).equals(unitTypes.get(destUnit))) {
                        throw new IllegalArgumentException("Units are not compatible for conversion.");
                    }
                    double result = convertUnits(sourceUnit, destUnit, value);
                    editText2.setText(String.format("%.2f", result));
                } catch (NumberFormatException nfe) {
                    Toast.makeText(MainActivity.this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
                } catch (IllegalArgumentException iae) {
                    Toast.makeText(MainActivity.this, iae.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeUnitTypes() {
        unitTypes = new HashMap<>();
        unitTypes.put("Inch", "Length");
        unitTypes.put("Centimeter", "Length");
        unitTypes.put("Foot", "Length");
        unitTypes.put("Yard", "Length");
        unitTypes.put("Mile", "Length");
        unitTypes.put("Kilometer", "Length");
        unitTypes.put("Pound", "Weight");
        unitTypes.put("Ounce", "Weight");
        unitTypes.put("Ton", "Weight");
        unitTypes.put("Kilogram", "Weight");
        unitTypes.put("Celsius", "Temperature");
        unitTypes.put("Kelvin", "Temperature");
        unitTypes.put("Fahrenheit", "Temperature");
    }

    private double convertUnits(String sourceUnit, String destUnit, double value) {
        if (sourceUnit.equals("Inch") && destUnit.equals("Centimeter")) {
            return value * 2.54;
        }
        if (sourceUnit.equals("Centimeter") && destUnit.equals("Inch")) {
            return value / 2.54;
        }
        if (sourceUnit.equals("Foot") && destUnit.equals("Centimeter")) {
            return value * 30.48;
        }
        if (sourceUnit.equals("Centimeter") && destUnit.equals("Foot")) {
            return value / 30.48;
        }
        if (sourceUnit.equals("Yard") && destUnit.equals("Centimeter")) {
            return value * 91.44;
        }
        if (sourceUnit.equals("Centimeter") && destUnit.equals("Yard")) {
            return value / 91.44;
        }
        if (sourceUnit.equals("Mile") && destUnit.equals("Kilometer")) {
            return value * 1.60934;
        }
        if (sourceUnit.equals("Kilometer") && destUnit.equals("Mile")) {
            return value / 1.60934;
        }
        if (sourceUnit.equals("Pound") && destUnit.equals("Kilogram")) {
            return value * 0.453592;
        }
        if (sourceUnit.equals("Kilogram") && destUnit.equals("Pound")) {
            return value / 0.453592;
        }
        if (sourceUnit.equals("Ounce") && destUnit.equals("Kilogram")) {
            return value * 28.3495;
        }
        if (sourceUnit.equals("Kilogram") && destUnit.equals("Ounce")) {
            return value / 28.3495;
        }
        if (sourceUnit.equals("Ton") && destUnit.equals("Kilogram")) {
            return value * 907.185;
        }
        if (sourceUnit.equals("Kilogram") && destUnit.equals("Ton")) {
            return value / 907.185;
        }
        if (sourceUnit.equals("Celsius") && destUnit.equals("Fahrenheit")) {
            return (value * 1.8) + 32;
        }
        if (sourceUnit.equals("Fahrenheit") && destUnit.equals("Celsius")) {
            return (value * -32) / 1.8;
        }
        if (sourceUnit.equals("Celsius") && destUnit.equals("Kelvin")) {
            return value + 273.15;
        }
        if (sourceUnit.equals("Kelvin") && destUnit.equals("Celsius")) {
            return value - 273.15;
        }
        return value;
    }
    ;
}