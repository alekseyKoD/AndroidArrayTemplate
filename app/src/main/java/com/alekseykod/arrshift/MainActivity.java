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

    // маcсив для обозначения элемента массива, который нужно выделить другим цветом
int[][] markerArray=new int[ARRAY_ROWS][ARRAY_COLUMNS];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // заполним массив случ. цифрами
        for (int i = 0; i < ARRAY_ROWS; i++) {
            for (int j = 0; j < ARRAY_COLUMNS; j++) {
                sourceArray[i][j]= (int) (minRandValue+Math.random()*(maxRandValue-minRandValue+1));

                // зануляем массив markerArray
                markerArray[i][j]=0;
            }
        }
        findSeddlePoint();
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);

    }
    private void someFunc(String direction){

    }
    // функция для поиска в массиве седловых точек
    // Седловая точка – элемент массива, который одновременно является
    // минимумом в своей строке и максимумом в своем столбце
     private void findSeddlePoint() {
         int[] maxRowElemIndexArray=new int[ARRAY_ROWS];
         // Алгоритм поиска: сначала проходим по массиву и находим макс. элемент в каждой строке
         // индекс этого элемента заносим в новый одномерный массив.
         // затем делаем второй проход и ищем минимальный элемент в столбце и запоминаем его индекс
         // сравниваем этот индексами строк и если есть совпадение, значит мы нашли седловоцй элемент
         // помечаем это элемент в массиве markerArray( устанавл 1 в элемент с соотв индексами)

         int maxRowElem;
         int maxRowElemIndex;
         for (int i = 0; i < ARRAY_ROWS; i++) {
             maxRowElem=0;
             maxRowElemIndex=0;
             for (int j = 0; j < ARRAY_COLUMNS; j++) {
                if(sourceArray[i][j]>maxRowElem){
                    // нашли новый макс элемент в строке
                    maxRowElem=sourceArray[i][j];
                    maxRowElemIndex=j;
                }
             }
             maxRowElemIndexArray[i]=maxRowElemIndex;
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
                if(i==3 || j==3){
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