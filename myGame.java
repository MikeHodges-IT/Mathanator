package app.math.com.mathanador;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Mike on 11/11/2014.
 */
public class myGame {
     int[][]dataChoice;
     int intTable;
     int counter;
     int[] datNum = {0, 1,  2,  3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
     int[] table  = {1, 10, 11, 2, 5, 3, 9, 4, 6, 7, 8, 12};
    ArrayList<Integer> randAns = new ArrayList<Integer>();
    ArrayList<Integer> randNum4 = new ArrayList<Integer>();
    ArrayList<Integer> randNum13 = new ArrayList<Integer>();


    public myGame(int level) {

        intTable = table[level-1];
        for (int i = 0; i <= 3;  ++i) randNum4.add(i);
        for (int i = 0; i <= 12; ++i) randNum13.add(i);
        Collections.shuffle(randNum13);
        Collections.shuffle(randNum4 );
        counter = 0;



         switch (intTable) {
            case 1:

                int[][] dataChoice01 = {{0, 1, 2,  3,  4,  5,  6, 7,   8,  9, 10,  11, 12},
                                        {3,  2, 3,  4,  5,  6,  7, 8,   9, 10, 11,  12, 13},
                                        {1,  0, 8, 12,  9, 12, 24, 18, 21, 24, 27,  30, 33},
                                        {2,  9, 9, 15, 15, 18, 21, 24, 27, 30, 33,  36, 39},
                                        {1,  1, 7, 18,  6, 10, 12, 22, 18, 36, 300, 31, 24},
                                        {2,  2, 12, 3, 13, 20, 19, 19, 16, 18,  3,  34, 46}};
                dataChoice = dataChoice01;
               break;


            case 2:
                int[][] dataChoice02 = {{0,   2,  4,  6,  8, 10, 12, 14, 16, 18, 20,  22, 24},
                                        {2,   3,  4,  5,  6,  7,  8,  9, 10, 11, 12,  13, 14},
                                        {1,   4,  3,  7,  9, 12, 24, 12, 10, 16, 18,  21, 22},
                                        {2,   1,  5,  5,  7,  8, 10, 16, 14, 14, 22,  20, 26},
                                        {20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,  31, 32},
                                        {10,  6,  8,  8, 10, 20, 14, 18, 18, 20, 40,  24, 12}};

                dataChoice = dataChoice02;
                break;


            case 3:

                int[][] dataChoice03 = {{0,  3, 6,  9, 12, 15, 18, 21, 24, 27, 30,  33, 36},
                                        {3,  4, 5,  6,  7,  8,  9, 10, 11, 12, 13,  14, 15},
                                        {1,  0, 8, 12,  9, 12, 24, 18, 21, 24, 27,  30, 33},
                                        {2,  9, 9, 15, 15, 18, 21, 24, 27, 30, 33,  36, 39},
                                        {30, 1, 7, 18,  6, 10, 12, 22, 18, 36, 300, 31, 24},
                                        {6,  2, 12, 3, 13, 20, 19, 19, 16, 18,  3,  34, 46}};
                dataChoice = dataChoice03;
                break;

            case 4:

                int[][] dataChoice04 = {{0,  4,   8, 12, 16, 20, 24, 28, 32, 36, 40,  44, 48},
                                        {4,  5,   6,  7,  8,  9, 10, 11, 12, 13, 14,  15, 16},
                                        {1,  0,  10, 16, 12, 16, 22, 24, 28, 40, 44,  30, 33},
                                        {2,  9,   9,  8, 14, 18, 20, 32, 30, 32, 33,  36, 39},
                                        {40, 41, 42, 22, 20, 45, 46, 47, 48, 49, 34,  41, 42},
                                        {6,  2,  12,  3, 18, 24, 28, 24, 36, 34, 400, 34, 46}};
                dataChoice = dataChoice04;
                break;

            case 5:

                int[][] dataChoice05 = {{ 0,  5, 10, 15, 20, 25, 30, 35, 40, 45,  50, 55, 60},
                                        { 5,  6,  7,  8,  9, 10, 11, 12, 13, 14,  15, 16, 17},
                                        { 1, 10, 25, 10, 15, 20, 40, 40, 35, 40,  45, 60, 55},
                                        { 4,  5,  5, 20, 10, 30, 25, 45, 45, 50,  60, 50, 65},
                                        { 2,  1,  2, 10,  2,  5, 35, 30, 50, 55, 500, 65, 70},
                                        {50, 51, 52, 53, 54, 55, 56, 57, 58, 59,  50, 51, 52}};
                dataChoice = dataChoice05;
                break;

            case 6:

                int[][] dataChoice06 = {{0,   6, 12, 18, 24, 30, 36, 42, 48, 54, 60,  66, 72},
                                        {6,   7,  8,  9, 10, 11, 12, 13, 14, 15, 16,  17, 18},
                                        {1,   0, 10, 15, 21, 25, 30, 35, 40, 45, 600, 55, 60},
                                        {5,   9, 18, 24, 30, 36, 42, 48, 54, 60, 66,  72, 78},
                                        {60,  0, 6,  12, 18, 24, 30, 36, 42, 48, 600, 54, 66},
                                        {60, 11, 22, 23, 34, 35, 36, 47, 48, 59, 61,  61, 62}};
                dataChoice = dataChoice06;
                break;

            case 7:

                int[][] dataChoice07 = {{0,   7, 14, 21, 28, 35, 42, 49, 56, 63,  70, 77, 84},
                                        {7,   8,  9, 10, 11, 12, 13, 14, 15, 16,  17, 18, 19},
                                        {1,   1, 12, 28, 24, 28, 35, 56, 62, 70,  27, 84, 91},
                                        {2,  14, 16, 24, 32, 42, 49, 42, 49, 56,  63, 70, 77},
                                        {70,  1, 18, 14, 20, 40, 48, 43, 48, 36, 700, 66, 72},
                                        {70, 71, 12, 33, 34, 45, 46, 47, 58, 69,  71, 71, 72}};
                dataChoice = dataChoice07;
                break;

            case 8:

                int[][] dataChoice08 = {{0,  8, 16, 24, 32, 40, 48, 56, 64, 72,  80, 88,  96},
                                        {8,  9, 10, 11, 12, 13, 14, 15, 16, 17,  18, 19,  20},
                                        {1,  0,  8, 12, 28, 35, 42, 49, 56, 61, 800, 77,  84},
                                        {2,  9, 24, 32, 40, 48, 56, 64, 72, 80,  88, 96, 104},
                                        {80, 1, 8,  16, 24, 32, 40, 48, 56, 64,  72, 80,  88},
                                        {6,  2, 12, 23, 34, 45, 56, 57, 68, 71,  81, 91,  82}};
                dataChoice = dataChoice08;
                break;

            case 9:

                int[][] dataChoice09 = {{0,  9, 18, 27, 36, 45, 54, 63, 72, 81,  90,  99, 108},
                                        {9, 10, 11, 12, 13, 14, 15, 16, 17, 18,  19,  20,  21},
                                        {1,  0, 16, 24, 32, 40, 48, 56, 64, 72, 900,  88,  96},
                                        {2,  9, 27, 36, 45, 54, 63, 72, 81, 90,  99, 108, 117},
                                        {90, 1,  9, 18, 27, 36, 45, 54, 63, 72,  81,  90,  99},
                                        {6, 91, 92, 28, 37, 55, 56, 67, 78, 89,  91,  91,  92}};
                dataChoice = dataChoice09;
                break;

            case 10:

                int[][] dataChoice10 = {{0, 10,  20,  30,  40,  50,  60,  70,  80,   90,  100, 110, 120},
                                       {10, 11,  12,  13,  14,  15,  16,  17,  18,   19,   20,  21,  22},
                                       {1,   0,   8,  27,  36,  45,  54,  63,  72,   81,   90,  99, 108},
                                       {2,   9,  30,  40,  50,  60,  70,  80,  90,  100,  110, 120, 130},
                                       {100, 1,  10,  20,  30,  40,  50,  60,  70,   80,   90, 100, 110},
                                       {6,   2, 102, 103, 104, 105, 106, 107, 108,  109, 1100, 111, 112}};
                dataChoice = dataChoice10;
                break;

            case 11:

                int[][] dataChoice11 = {{0,   11,  22, 33, 44, 55, 66,  77,  88,   99, 110, 121, 132},
                                        {11,  12,  13, 14, 15, 16, 17,  18,  19,   20,  21,  22,  23},
                                        {1,   0,   20, 30, 40, 50, 60,  70,  80,   90, 100, 110, 120},
                                        {2,   9,   33, 44, 55, 66, 77,  88,  99,  110, 121, 132, 143},
                                        {110, 111, 11, 22, 33, 44, 55,  66,  77,   88,  99, 110, 121},
                                        {6,   2,   32, 43, 54, 65, 76,  87,  98,  109, 111, 122, 133}};
                dataChoice = dataChoice11;
                break;

            case 12:

                int[][] dataChoice12 = {{0,   12, 24, 36, 48, 60, 72, 84,  96, 108,  120, 132, 144},
                                        {12,  13, 14, 15, 16, 17, 18, 19,  20,  21,   22,  23,  24},
                                        {24,   0,  8, 33, 44, 55, 66, 77,  88,  99, 1200, 121, 132},
                                        {12,   6, 36, 48, 60, 72, 84, 96, 108, 120,  132, 144, 156},
                                        {120, 24, 12, 24, 36, 48, 60, 72,  84,  96,  108, 120, 132},
                                        {1,    2, 22, 43, 54, 65, 76, 87,  98, 109,  122, 134, 146}};
                dataChoice = dataChoice12;
                break;


        }
        randAns.clear();
        randAns.add(dataChoice[0][randNum13.get(counter)]);
        randAns.add(dataChoice[1][randNum13.get(counter)]);
        randAns.add(dataChoice[2 + randNum4.get(0)][randNum13.get(counter)]);
        randAns.add(dataChoice[2 + randNum4.get(1)][randNum13.get(counter)]);

        Collections.shuffle(randAns);
        Collections.shuffle(randNum4);


    }



    public String getButtonText01() {
     return randAns.get(0)+"";
    }
    public String getButtonText02() {
        return randAns.get(1)+"";
    }
    public String getButtonText03() {
        return randAns.get(2)+"";
    }
    public String getButtonText04() {
        return randAns.get(3)+"";
    }
    public int getCount() {

       return counter;
    }
    public void setCounterNext(){
        counter++;
        if(!isGameOver()) {

            randAns.clear();
            randAns.add(dataChoice[0][randNum13.get(counter)]);
            randAns.add(dataChoice[1][randNum13.get(counter)]);
            randAns.add(dataChoice[2 + randNum4.get(0)][randNum13.get(counter)]);
            randAns.add(dataChoice[2 + randNum4.get(1)][randNum13.get(counter)]);
            Collections.shuffle(randAns);
            Collections.shuffle(randNum4);

        }

    }
    public void setCounterReset(){
        counter = 0;
    }
    public String getCorrectAnswer() {
        return dataChoice[0][randNum13.get(counter)]+"";
    }
    public String getEquation(){
        String equationText = datNum[randNum13.get(counter)] + " X " + intTable + " = ";
         return equationText;
    }
    public boolean isGameOver  (){
        if (counter > 12){
            return true;
        }else{
            return false;
        }
    }

}
