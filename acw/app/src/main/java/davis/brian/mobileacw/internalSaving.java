package davis.brian.mobileacw;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static davis.brian.mobileacw.PuzzleRepository.mApplicationContext;

public class internalSaving {

    public void saveIndexLocally(JSONObject pIndexObject, String pFilename) {
        ContextWrapper contextWrapper = new ContextWrapper(mApplicationContext);
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(
                    contextWrapper.openFileOutput(pFilename, Context.MODE_PRIVATE));
            outputStreamWriter.write(pIndexObject.toString());
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private LiveData<ArrayList<Puzzle>> loadIndexLocally(String pFilename) {
        JSONObject indexObject = null;
        MutableLiveData<ArrayList<Puzzle>> mutableItems = new MutableLiveData<>();
        try {
            InputStream inputStream = mApplicationContext.openFileInput(pFilename);

            if ( inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                String builtString = stringBuilder.toString();
                indexObject = new JSONObject(builtString);
            }

        } catch (FileNotFoundException e) {
            Log.e("JSONLoading", "File Not Found: " + e.toString());
        } catch (IOException e) {
            Log.e("JSONLoading", "Can't Read File: " + e.toString());
        } catch (JSONException e) {
            Log.e("JSONLoading", "JSON Error: " + e.toString());
        }
        //if (indexObject != null) {
        //        ArrayList<Puzzle> puzzles = parseJSONResponse{indexObject);
        //            mutableItems.setValue(puzzles);
        //        }
        //    }
        return mutableItems;
    }

    public void saveImageLocally(Bitmap pBitmap, String pFilename) {
        ContextWrapper contextWrapper = new ContextWrapper(mApplicationContext);
        File directory = contextWrapper.getDir("puzzleImages", Context.MODE_PRIVATE);
        File file = new File(directory, pFilename);
        if (!file.exists()) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
                pBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (java.io.IOException e){
                e.printStackTrace();
            }
        }
    }

    public boolean loadImageLocally(String pFilename, MutableLiveData<Puzzle> pPuzzleData) {
        boolean loaded = false;
        ContextWrapper contextWrapper = new ContextWrapper(mApplicationContext);
        File directory = contextWrapper.getDir("puzzleImages", Context.MODE_PRIVATE);
        File file = new File(directory, pFilename);
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                Puzzle puzzle = pPuzzleData.getValue();
                puzzle.setImage(bitmap);
                pPuzzleData.setValue(puzzle);

                fileInputStream.close();
                loaded = true;
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        } return loaded;
    }
}

