package com.example.a15puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                String buttonID = "button_"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (((Button)v).getText().toString().equals("X")){
            return;
        }
        switch (((Button)v).getId()){
            case R.id.button_00:
                checkForSwipe(0,0);
                break;
            case R.id.button_01:
                checkForSwipe(0,1);
                break;
            case R.id.button_02:
                checkForSwipe(0,2);
                break;
            case R.id.button_03:
                checkForSwipe(0,3);
                break;
            case R.id.button_10:
                checkForSwipe(1,0);
                break;
            case R.id.button_11:
                checkForSwipe(1,1);
                break;
            case R.id.button_12:
                checkForSwipe(1,2);
                break;
            case R.id.button_13:
                checkForSwipe(1,3);
                break;
            case R.id.button_20:
                checkForSwipe(2,0);
                break;
            case R.id.button_21:
                checkForSwipe(2,1);
                break;
            case R.id.button_22:
                checkForSwipe(2,2);
                break;
            case R.id.button_23:
                checkForSwipe(2,3);
                break;
            case R.id.button_30:
                checkForSwipe(3,0);
                break;
            case R.id.button_31:
                checkForSwipe(3,1);
                break;
            case R.id.button_32:
                checkForSwipe(3,2);
                break;
            case R.id.button_33:
                checkForSwipe(3,3);
                break;
        }

        if (checkForWin()){
            Toast.makeText(this,"You finally did it!",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkForWin() {
        int count = 1;
        String field[][] = new String[4][4];
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (i == 3 && j == 3){
                    continue;
                }
                if (field[i][j].equals("X")){
                    return false;
                }
                if (Integer.parseInt(field[i][j]) != count){
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    private void checkForSwipe(int x, int y) {
        if (checkForCoordinates(x,y-1) && buttons[x][y-1].getText().toString().equals("X")){
            makeSwipe(x,y,x,y-1);
        }
        else if (checkForCoordinates(x-1,y) && buttons[x-1][y].getText().toString().equals("X")){
            makeSwipe(x,y,x-1,y);
        }
        else if (checkForCoordinates(x,y+1) && buttons[x][y+1].getText().toString().equals("X")){
            makeSwipe(x,y,x,y+1);
        }
        else if (checkForCoordinates(x+1,y) && buttons[x+1][y].getText().toString().equals("X")){
            makeSwipe(x,y,x+1,y);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void makeSwipe(int fromX, int fromY, int toX, int toY) {
        String num = buttons[fromX][fromY].getText().toString();
        buttons[toX][toY].setText(num);
        buttons[toX][toY].setTextColor(0xFFFFFFFF);
        buttons[toX][toY].setBackground(getResources().getDrawable(R.drawable.button_background));
        buttons[fromX][fromY].setBackground(getResources().getDrawable(R.drawable.button_background_empty));
        buttons[fromX][fromY].setTextColor(0xFFbdc3c7);
        buttons[fromX][fromY].setText("X");
    }

    private boolean checkForCoordinates(int x, int y){
        return x >= 0 && x <= 3 && y >= 0 && y <= 3;
    }
}
