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
int maxRandValue=5;
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
        findSeddlePoint(); // вариант 9
        findInvertSeddlePoint();  // вариант 10
        showArray(sourceArray,ARRAY_ROWS,ARRAY_COLUMNS);

    }
    private void someFunc(String direction){

    }
    // функция для поиска в массиве седловых точек
    // Седловая точка – элемент массива, который одновременно является
    // минимумом в своей строке и максимумом в своем столбце
     private void findSeddlePoint() {
         int[] minElemInRow=new int[ARRAY_ROWS];
         int[] maxElemInCol=new int[ARRAY_COLUMNS];

         // массив для тестов, седловые точки - элементы массива
         sourceArray= new int[][]{
                 {3,4,5,3,6,7,3},
                 {1,6,2,2,1,6,2},
                 {0,1,4,1,3,3,1},
                 {3,5,4,3,4,3,3},
                 {2,4,5,2,3,6,2},
                 {1,3,4,1,5,4,2},
                 {3,5,4,3,4,7,3}
                };


         // Алгоритм поиска: нам необходимо найти максимальный элемент в строке и минимальнй элемент в столбце
         // занести их в 2 одномернх массива: 1-ый массив  содержит макс элемент каждой строки
         // 2-ой массив содержит мин. элемент столбца.
         // После нахождения этих элементов делаем еще раз обход массива и каждый элемент массива
         // с сравниваем с элементвами соответствующими элементами в массивах с макс эл-ми в строке и мин в столбце
         // если они совпадают, значит мы нашли седловой элемент

         int tempMinElemInRow;
         int tempMaxElemInCol;

         for (int i = 0; i < ARRAY_ROWS; i++) {
             tempMinElemInRow=Integer.MAX_VALUE;
             tempMaxElemInCol=Integer.MIN_VALUE;
             for (int j = 0; j < ARRAY_COLUMNS; j++) {
                 // ищем макс элемент в строке
                 if(sourceArray[i][j]<tempMinElemInRow){
                     // нашли новый макс элемент в строке
                     tempMinElemInRow=sourceArray[i][j];
                 }
                 // ищем мин элемент в столбце
                 if(sourceArray[j][i]>tempMaxElemInCol){
                     // нашли новый мин элемент в столбце
                     tempMaxElemInCol=sourceArray[j][i];
                 }
             }
             minElemInRow[i]=tempMinElemInRow;
             maxElemInCol[i]=tempMaxElemInCol;
         }
         for (int i = 0; i < ARRAY_ROWS; i++) {
             for (int j = 0; j < ARRAY_ROWS; j++) {
                 if (sourceArray[i][j]==minElemInRow[i] &&
                         sourceArray[i][j]==maxElemInCol[j]){
                     markerArray[i][j]=1;
                 }

             }

         }
     }
    // функция для поиска в массиве обр. седловых точек
    // Обр. Седловая точка – элемент массива, который одновременно является
    // максимумом в своей строке и минимумом в своем столбце
    private void findInvertSeddlePoint() {
        int[] maxElemInRow=new int[ARRAY_ROWS];
        int[] minElemInCol=new int[ARRAY_COLUMNS];

        // массив для тестов, обр.седловые точки - элементы массива [1,2]=4,[1,6]=4,[4,2]=4
        sourceArray= new int[][]{
                {5,2,5,4,3,8,6},
                {2,3,4,0,3,4,2},
                {0,3,5,3,4,7,6},
                {7,4,5,4,6,6,7},
                {3,4,4,2,3,4,1},
                {3,4,5,6,5,5,5},
                {8,0,5,4,2,4,4}
        };

        // Алгоритм поиска: нам необходимо найти максимальный элемент в строке и минимальнй элемент в столбце
        // занести их в 2 одномернх массива: 1-ый массив  содержит макс элемент каждой строки
        // 2-ой массив содержит мин. элемент столбца.
        // После нахождения этих элементов делаем еще раз обход массива и каждый элемент массива
        // с сравниваем с элементвами соответствующими элементами в массивах с макс эл-ми в строке и мин в столбце
        // если они совпадают, значит мы нашли седловой элемент

        int tempMaxElemInRow;
        int tempMinElemInCol;

        for (int i = 0; i < ARRAY_ROWS; i++) {
            tempMaxElemInRow=Integer.MIN_VALUE;
            tempMinElemInCol=Integer.MAX_VALUE;
            for (int j = 0; j < ARRAY_COLUMNS; j++) {
                // ищем макс элемент в строке
                if(sourceArray[i][j]>tempMaxElemInRow){
                    // нашли новый макс элемент в строке
                    tempMaxElemInRow=sourceArray[i][j];
                }
                // ищем мин элемент в столбце
                if(sourceArray[j][i]<tempMinElemInCol){
                    // нашли новый мин элемент в столбце
                    tempMinElemInCol=sourceArray[j][i];
                }
            }
            maxElemInRow[i]=tempMaxElemInRow;
            minElemInCol[i]=tempMinElemInCol;
        }
        for (int i = 0; i < ARRAY_ROWS; i++) {
            for (int j = 0; j < ARRAY_ROWS; j++) {
                if (sourceArray[i][j]==maxElemInRow[i] &&
                        sourceArray[i][j]==minElemInCol[j]){
                    markerArray[i][j]=1;
                }

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

                //выделяем другим цветом элемент, который промаркирован
                // в массиве markerArray
                if(markerArray[i][j]==1){
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