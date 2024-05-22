package com.alekseykod.arrshift;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

int ARRAY_ROWS = 7;
int ARRAY_COLUMNS = 7;
int minRandValue=1;
int maxRandValue=20;
int[][] sourceArray=new int[ARRAY_ROWS][ARRAY_COLUMNS];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // заполним массив случ. цифрами
        for (int i = 0; i < ARRAY_ROWS; i++) {
            for (int j = 0; j < ARRAY_COLUMNS; j++) {
                sourceArray[i][j]= (int) (minRandValue+Math.random()*(maxRandValue-minRandValue+1));
            }
        }
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);
        //swapElemRelativeCenterCol(); //вариант 5
        swapElemRelativeCenterRow(); // вариант 6

    }
    private void swapElemRelativeCenterCol() {
        // вариант 5
        int tempVal = 0;
        for (int i = 0; i < ARRAY_ROWS; i++) {
            //обходим столбцы от начала до середины
            // зеркально обходим столбцы с конца массива до середины
            // (средний столбец не включаем)
            for (int j = 0; j < ARRAY_COLUMNS / 2; j++) {

                // меняем значения в столюцов через временную переменную
                tempVal = sourceArray[i][j];
                sourceArray[i][j] = sourceArray[i][ARRAY_COLUMNS - j - 1];
                sourceArray[i][ARRAY_COLUMNS - j - 1] = tempVal;
            }
        }
    }
    private void swapElemRelativeCenterRow() {
        // вариант 6
        int tempVal = 0;
        for (int i = 0; i < ARRAY_ROWS/2; i++) {
            //обходим строки от начала до середины
            // зеркально обходим строки с конца массива до середины
            // (среднюю строку не включаем)
            for (int j = 0; j < ARRAY_COLUMNS; j++) {

                // меняем значения в столюцов через временную переменную
                tempVal = sourceArray[i][j];
                sourceArray[i][j] = sourceArray[ARRAY_ROWS-i-1][j];
                sourceArray[ARRAY_ROWS-i-1][j] = tempVal;
            }
        }
    }

    private void showArray(int[][]array,int rowArray,int colArray){
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        // очищаем tablelayout от предыдущих значений
        tableLayout.removeAllViewsInLayout();

        for (int i = 0; i < rowArray; i++) {
            // создаем новую строку в tableLayout
            TableRow tableRow = new TableRow(this);
            // применяем параметры выравнивания и заполнения tableRow относительно экрана (по центру)
            TableLayout.LayoutParams params =
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT,
                            Gravity.CENTER_HORIZONTAL);
            tableRow.setLayoutParams(params);

            // применяем параметры выравнивания и заполнения textView
            TableRow.LayoutParams textViewParams =
                    new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT,
                            Gravity.CENTER_HORIZONTAL);


            for (int j = 0; j < colArray; j++) {
                // создаем textView, он будет выступать в роли ячейки в  строке tableRow
                TextView textView = new TextView(this);
                textView.setLayoutParams(textViewParams);
                textView.setText(String.valueOf(array[i][j]));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);

                //выделяем другим цветом заданный элемент
               // if(j==colArray/2){ // для варианта 5
                if(i==rowArray/2){ // для варианта 6
                    textView.setBackgroundColor(0xFF00FF00);
                    textView.setTextColor(0xFFFF0000);
                }





                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);
        }
    }
    public void upButtonClick(View view) {
     //   someFunc("UP");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);
    }
    public void downButtonClick(View view) {
     //   someFunc("DOWN");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);
    }
    public void leftButtonClick(View view) {
     //   someFunc("LEFT");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);
    }

    public void rightButtonClick(View view) {
     //   someFunc("RIGHT");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);

    }
}