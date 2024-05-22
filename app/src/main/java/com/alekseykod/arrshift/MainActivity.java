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
int minRandValue=0;
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

    }
    private void someFunc(String direction){

    }

    private void showArray(int[][]array,int rowArray,int colArray){



        // переменная, в которой храним макс сумму по строкам, начальное значение равно 0, т.к
        // это минимально возвожное значение
        int maxSumRow=0;

        // переменная, в которой храним мин сумму по столбцам, начальное значение устанавливаем макс
        // возм.значению (макс значение случ. величины в каждом элементе в столбцах)
        int minSumCol=maxRandValue*colArray;

        int maxSumRowIndex=0; // номер строки, в которой сумма эл-том максимальна
        int minSumColIndex=0;  // номер столбца, в которой сумма эл-том минимальна

        for (int i = 0; i < rowArray; i++) {
            int tempSumRow=0;
            int tempSumCol=0;

            for (int j = 0; j < rowArray; j++) {
                tempSumRow+=array[i][j];  // суммируем элементы строк
                tempSumCol+=array[j][i]; // суммируем элементы столбцов
            }
            if(tempSumRow>maxSumRow){
                // если сумма элементов строки больше предыдущей, то
                // устанавливаем новую макс сумм и обновляем индекс строки
                maxSumRow=tempSumRow;
                maxSumRowIndex=i;
            }
            if(tempSumCol<minSumCol){
                // если сумма элементов строки меньше предыдущей, то
                // устанавливаем новую мин. сумм и обновляем индекс столбца
                minSumCol=tempSumCol;
                minSumColIndex=i;
            }
        }
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
                //заполняем ячейку значением из ячейки массива
                textView.setText(String.valueOf(array[i][j]));

                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);

                //выделяем другим цветом заданный элементы, которые удовлетворяют условиям
                if(i==maxSumRowIndex || j==minSumColIndex){
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
        someFunc("UP");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);
    }
    public void downButtonClick(View view) {
        someFunc("DOWN");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);
    }
    public void leftButtonClick(View view) {
        someFunc("LEFT");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);
    }

    public void rightButtonClick(View view) {
        someFunc("RIGHT");
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);

    }
}