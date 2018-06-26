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
    private TextView secondaryFermentationDateInput;
    private TextView bottlingOrKeggingDateInput;
    private TextView currentDateInput;
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
        secondaryFermentationDateInput = (TextView) findViewById(R.id.secondary_fermentation_date_input);
        bottlingOrKeggingDateInput = (TextView) findViewById(R.id.bottling_or_kegging_date_input);
        bitteringHopsList = (LinearLayout) findViewById(R.id.bittering_hops_list);
        addBitteringHopsButton = (Button) findViewById(R.id.add_bittering_hops_button);
        flavorHopsList = (LinearLayout) findViewById(R.id.flavor_hops_list);
        addFlavorHopsButton = (Button) findViewById(R.id.add_flavor_hops_button);
        aromaHopsList = (LinearLayout) findViewById(R.id.aroma_hops_list);
        addAromaHopsButton = (Button) findViewById(R.id.add_aroma_hops_button);
        knockOutHopsList = (LinearLayout) findViewById(R.id.knockout_hops_list);
        addKnockOutHopsButton = (Button) findViewById(R.id.add_knockout_hops_button);
        dryHopsList = (LinearLayout) findViewById(R.id.dry_hops_list);
        addDryHopsButton = (Button) findViewById(R.id.add_dry_hops_button);
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
            //showDate();
            inflateEmptyHopRow(bitteringHopsList, inflater);
            inflateEmptyHopRow(flavorHopsList, inflater);
            inflateEmptyHopRow(aromaHopsList, inflater);
            inflateEmptyHopRow(knockOutHopsList, inflater);
            inflateEmptyHopRow(dryHopsList, inflater);
        } else {
            ArrayList<Beer> tempBeerList = PersistenceUtil.loadBeers(this);
            Beer tempBeer = tempBeerList.get(position);
            beerNameInput.setText(tempBeer.getName());
            beerStyleDropdown.setSelection(tempBeer.getStyle());
            brewDateInput.setText(tempBeer.getBrewDate());
            secondaryFermentationDateInput.setText(tempBeer.getSecondaryFermentationDate());
            bottlingOrKeggingDateInput.setText(tempBeer.getBottlingOrKeggingDate());
            if (tempBeer.getBitteringHops().isEmpty()) {
                inflateEmptyHopRow(bitteringHopsList, inflater);
            } else {
                listHops(tempBeer.getBitteringHops(), bitteringHopsList, inflater);
            }
            if (tempBeer.getFlavorHops().isEmpty()) {
                inflateEmptyHopRow(flavorHopsList, inflater);
            } else {
                listHops(tempBeer.getFlavorHops(), flavorHopsList, inflater);
            }
            if (tempBeer.getAromaHops().isEmpty()) {
                inflateEmptyHopRow(aromaHopsList, inflater);
            } else {
                listHops(tempBeer.getAromaHops(), aromaHopsList, inflater);
            }
            if (tempBeer.getKnockOutHops().isEmpty()) {
                inflateEmptyHopRow(knockOutHopsList, inflater);
            } else {
                listHops(tempBeer.getKnockOutHops(), knockOutHopsList, inflater);
            }
            if (tempBeer.getDryHops().isEmpty()) {
                inflateEmptyHopRow(dryHopsList, inflater);
            } else {
                listHops(tempBeer.getDryHops(), dryHopsList, inflater);
            }
            preboilGravityInput.setText(tempBeer.getPreboilGravity());
            originalGravityInput.setText(tempBeer.getOriginalGravity());
            finalGravityInput.setText(tempBeer.getFinalGravity());
            aBV.setText(tempBeer.getABV());
            notesInput.setText(tempBeer.getNotes());
        }

        saveButton = (Button)findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tempBeer.setName(beerNameInput.getText().toString());
                tempBeer.setBrewDate(brewDateInput.getText().toString());
                tempBeer.setSecondaryFermentationDate(secondaryFermentationDateInput.getText().toString());
                tempBeer.setBottlingOrKeggingDate(bottlingOrKeggingDateInput.getText().toString());
                if (styleSelected != 0) {
                    tempBeer.setStyle(styleSelected);
                }
                getHopsOutOfLinearLayout(bitteringHopsList, Beer.HopsType.BITTERING);
                getHopsOutOfLinearLayout(aromaHopsList, Beer.HopsType.AROMA);
                getHopsOutOfLinearLayout(flavorHopsList, Beer.HopsType.FLAVOR);
                getHopsOutOfLinearLayout(knockOutHopsList, Beer.HopsType.KNOCKOUT);
                getHopsOutOfLinearLayout(dryHopsList, Beer.HopsType.DRY);
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

    public void onClickDateInput(View v) {
        currentDateInput = (TextView) v;
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
        currentDateInput.setText(date);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void inflateEmptyHopRow (LinearLayout hopsLayout, LayoutInflater inflater) {
        LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
        hopsLayout.addView(hop_row);
    }

    private void listHops (ArrayList<Hops> hopList, LinearLayout hopsLayout, LayoutInflater inflater) {
        for(int i = 0; i < hopList.size(); i++) {
            LinearLayout hop_row = (LinearLayout) inflater.inflate(R.layout.hop_row_layout, null);
            hopsLayout.addView(hop_row);
            EditText hopNameInput = (EditText) hop_row.getChildAt(0);
            EditText hopAmountInput = (EditText) hop_row.getChildAt(1);
            hopNameInput.setText(hopList.get(i).getName());
            hopAmountInput.setText(hopList.get(i).getAmount());
        }
    }

    private void getHopsOutOfLinearLayout(LinearLayout hopsLayout, Beer.HopsType hopsType){
        int countHops = hopsLayout.getChildCount();
        for(int i = 1; i < countHops; i++) {
            LinearLayout hopsChild = (LinearLayout) hopsLayout.getChildAt(i);
            EditText hopName = (EditText) hopsChild.getChildAt(0);
            EditText hopAmount = (EditText) hopsChild.getChildAt(1);
            Hops hops = new Hops();
            if(!isEmpty(hopName) && !isEmpty(hopAmount)) {
                hops.setName(hopName.getText().toString());
                hops.setAmount(hopAmount.getText().toString());
                tempBeer.setHops(hopsType, hops);
            }
        }
    }

    private boolean isEmpty(EditText editText) {
        return (editText.getText().toString().trim().length() == 0);
    }

    private boolean isEmpty(TextView textView) {
        return (textView.getText().toString().trim().length() == 0);
    }
}
