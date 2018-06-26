package com.example.scott.beerdiary2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.Context.*;

/**
 * Created by Scott on 5/25/17.
 */

public class PersistenceUtil {

    private static final String TAG = PersistenceUtil.class.getName();
    private static final String FILENAME = "beer_file";

    static void saveBeers(ArrayList<Beer> beerlist, Context context) {
        Gson gson = new Gson();
        String json = gson.toJson(beerlist);
        Log.d(TAG, json);

        try (FileOutputStream fos = context.openFileOutput(FILENAME, MODE_PRIVATE)) {
            fos.write(json.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            makeToast(context);
        } catch (IOException io) {
            io.printStackTrace();
            makeToast(context);
        }
    }

    static ArrayList<Beer> loadBeers(Context context){
        Gson gson = new Gson();
        ArrayList<Beer> beerlist = new ArrayList<>();
        try{
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while((line = reader.readLine()) != null) {
                ArrayList<Beer> beers = gson.fromJson(line, new TypeToken<ArrayList<Beer>>(){}.getType());
                beerlist.addAll(beers);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            makeToast(context);
        } catch (IOException io) {
            io.printStackTrace();
            makeToast(context);
        }
        return beerlist;
    }

    static void deleteBeer(Context context, int position) {
        ArrayList<Beer> tempBeerList = loadBeers(context);
        tempBeerList.remove(position);
        saveBeers(tempBeerList, context);
    }

    static void makeToast(Context context) {
        Toast toast = Toast.makeText(context, "Sry, there was an error", Toast.LENGTH_SHORT);
    }
}
