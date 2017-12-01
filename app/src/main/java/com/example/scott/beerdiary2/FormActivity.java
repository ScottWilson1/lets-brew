package com.example.scott.beerdiary2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{

    public static class CustomEditText extends android.support.v7.widget.AppCompatEditText {

        Context mContext;

        public CustomEditText(Context context) {
            super(context);
            mContext = context;
        }

        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            mContext = context;
        }

        public CustomEditText(Context context, AttributeSet attributeSet, int defStyleAttr) {
            super(context, attributeSet, defStyleAttr);
            mContext = context;
        }

        @Override
        public boolean onKeyPreIme(int keyCode, KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            }
            return super.dispatchKeyEvent(event);
        }
    }

    private EditText beerNameInput;
    private TextView brewDateInput;
    private Spinner beerStyleDropdown;
    private LinearLayout bitteringHopsList;
    private Button addBitteringHopsButton;
    private LinearLayout flavorHopsList;
    private Button addFlavorHopsButton;
    private LinearLayout aromaHopsList;
    private Button addAromaHopsButton;
    private LinearLayout knockOutHopsList;
    private Button addKnockOutHopsButton;
    private LinearLayout dryHopsList;
    private Button addDryHopsButton;
    private EditText bitteringHopsNameInput;
    private EditText bitteringHopsAmountInput;
    private EditText flavorHopsNameInput;
    private EditText flavorHopsAmountInput;
    private EditText aromaHopsNameInput;
    private EditText aromaHopsAmountInput;
    private EditText knockOutsHopNameInput;
    private EditText knockOutsHopAmountInput;
    private EditText dryHopsNameInput;
    private EditText dryHopsAmountInput;
    private EditText preboilGravityInput;
    private CustomEditText originalGravityInput;
    private EditText finalGravityInput;
    private TextView aBV;
    private EditText notesInput;
    private Button saveButton;
    private Calendar c = Calendar.getInstance();
    private int year = c.get(Calendar.YEAR);
    private int month = c.get(Calendar.MONTH);
    private int day = c.get(Calendar.DAY_OF_MONTH);
    private int styleSelected = 0;
    private int position;
    private Beer tempBeer = new Beer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        beerNameInput = (EditText) findViewById(R.id.beer_name_input);
        beerStyleDropdown = (Spinner) findViewById(R.id.beer_style_dropdown);
        brewDateInput = (TextView) findViewById(R.id.brew_date_input);
        bitteringHopsList = (LinearLayout) findViewById(R.id.bittering_hops_list);
        addBitteringHopsButton = (Button) findViewById(R.id.add_bittering_hops_button);
        bitteringHopsNameInput = (EditText) findViewById(R.id.hop_name);
        bitteringHopsAmountInput = (EditText) findViewById(R.id.hop_amount);
        flavorHopsList = (LinearLayout) findViewById(R.id.flavor_hops_list);
        addFlavorHopsButton = (Button) findViewById(R.id.add_flavor_hops_button);
        flavorHopsNameInput = (EditText) findViewById(R.id.flavor_hops_name);
        flavorHopsAmountInput = (EditText) findViewById(R.id.flavor_hops_amount);
        aromaHopsList = (LinearLayout) findViewById(R.id.aroma_hops_list);
        addAromaHopsButton = (Button) findViewById(R.id.add_aroma_hops_button);
        aromaHopsNameInput = (EditText) findViewById(R.id.aroma_hops_name);
        aromaHopsAmountInput = (EditText) findViewById(R.id.aroma_hops_amount);
        knockOutHopsList = (LinearLayout) findViewById(R.id.knockout_hops_list);
        addKnockOutHopsButton = (Button) findViewById(R.id.add_knockout_hops_button);
        knockOutsHopNameInput = (EditText) findViewById(R.id.knockout_hops_name);
        knockOutsHopAmountInput = (EditText) findViewById(R.id.knockout_hops_amount);
        dryHopsList = (LinearLayout) findViewById(R.id.dry_hops_list);
        addDryHopsButton = (Button) findViewById(R.id.add_dry_hops_button);
        dryHopsNameInput = (EditText) findViewById(R.id.dry_hops_name);
        dryHopsAmountInput = (EditText) findViewById(R.id.dry_hops_amount);
        preboilGravityInput = (EditText) findViewById(R.id.preboil_gravity);
        originalGravityInput = (CustomEditText) findViewById(R.id.original_gravity);
        finalGravityInput = (EditText) findViewById(R.id.final_gravity);
        aBV = (TextView) findViewById(R.id.abv);
        if(!"".equals(tempBeer.getABV())) {
            tempBeer.calculateABV();
            aBV.setText(tempBeer.getABV());
        }
        notesInput = (EditText) findViewById(R.id.notes_input);
        String[] beerTypes = getResources().getStringArray(R.array.beer_styles);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.
                simple_spinner_dropdown_item, beerTypes);
        beerStyleDropdown.setAdapter(adapter);
        beerStyleDropdown.setOnItemSelectedListener(this);

        final LayoutInflater inflater = LayoutInflater.from(this);
        addBitteringHopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
                bitteringHopsList.addView(hop_row);
            }
        });

        addFlavorHopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
                flavorHopsList.addView(hop_row);
            }
        });

        addAromaHopsButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
               aromaHopsList.addView(hop_row);
           }
        });

        addKnockOutHopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
                knockOutHopsList.addView(hop_row);
            }
        });

        addDryHopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
                dryHopsList.addView(hop_row);
            }
        });


        position = getIntent().getIntExtra("POSITION", -1);
        if (position == -1) {
            showDate();
        } else {
            ArrayList<Beer> tempBeerList = PersistenceUtil.loadBeers(this);
            Beer tempBeer = tempBeerList.get(position);
            beerNameInput.setText(tempBeer.getName());
            beerStyleDropdown.setSelection(tempBeer.getStyle());
            brewDateInput.setText(tempBeer.getDate());
            listHops(tempBeer.getBitteringHops(), bitteringHopsList, inflater);
            listHops(tempBeer.getFlavorHops(), flavorHopsList, inflater);
            listHops(tempBeer.getAromaHops(), aromaHopsList, inflater);
            listHops(tempBeer.getKnockOutHops(), knockOutHopsList, inflater);
            listHops(tempBeer.getDryHops(), dryHopsList, inflater);
            preboilGravityInput.setText(tempBeer.getPreboilGravity());
            originalGravityInput.setText(tempBeer.getOriginalGravity());
            finalGravityInput.setText(tempBeer.getFinalGravity());
            aBV.setText(tempBeer.getABV() + "%");
            notesInput.setText(tempBeer.getNotes());
        }

        saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tempBeer.setName(beerNameInput.getText().toString());
                tempBeer.setDate(brewDateInput.getText().toString());
                if (styleSelected != 0) {
                    tempBeer.setStyle(styleSelected);
                }
                int count = bitteringHopsList.getChildCount();
                for(int i = 0; i < count; i++) {
                    LinearLayout hopsRow = (LinearLayout) bitteringHopsList.getChildAt(i);
                }
//                tempBeer.setHopName(hopNameInput.getText().toString());
//                tempBeer.setHopAmount(hopAmountInput.getText().toString());
                tempBeer.setPreboilGravity(preboilGravityInput.getText().toString());
                tempBeer.setOriginalGravity(originalGravityInput.getText().toString());
                tempBeer.setFinalGravity(finalGravityInput.getText().toString());
                tempBeer.calculateABV();
                tempBeer.setNotes(notesInput.getText().toString());
                ArrayList<Beer> tempbeerlist = PersistenceUtil.loadBeers(getApplicationContext());
                    if (position == -1) {
                    tempbeerlist.add(tempBeer);
                    } else {
                    tempbeerlist.set(position, tempBeer);
                    }
                PersistenceUtil.saveBeers(tempbeerlist, getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        originalGravityInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasfocus) {
                if(!hasfocus) {
                    String oG = originalGravityInput.getText().toString();
                    String fG = finalGravityInput.getText().toString();
                    if(!oG.equals("") && !fG.equals("")) {
                        tempBeer.calculateABV(oG, fG);
                        aBV.setText(tempBeer.getABV());
                    }
                }
            }
        });

        finalGravityInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasfocus) {
                if(!hasfocus) {
                    String oG = originalGravityInput.getText().toString();
                    String fG = finalGravityInput.getText().toString();
                    if(!oG.equals("") && !fG.equals("")) {
                        tempBeer.calculateABV(oG, fG);
                        aBV.setText(tempBeer.getABV());
                    }
                }
            }
        });

    }

    public void onClickBrewDateInput(View v) {
        DatePickerDialog dialog = new DatePickerDialog(this, this, year, month, day);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
        showDate();
    }

    public void showDate() {
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String yearText = df.format(c.getTime());
        String date = (month + 1 ) + "/" + day + "/" + yearText;
        brewDateInput.setText(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void listHops (ArrayList<Hops> hopList, LinearLayout hopsLayout, LayoutInflater inflater) {
        for(int i = 0; i < hopList.size(); i++) {
            LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
            hopsLayout.addView(hop_row);
            EditText hopNameInput = (EditText) findViewById(R.id.hop_name);
            EditText hopAmountInput = (EditText) findViewById(R.id.hop_amount);
            hopNameInput.setText(hopList.get(i).getName());
            hopAmountInput.setText(hopList.get(i).getAmount());
        }
    }
}
