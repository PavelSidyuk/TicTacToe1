import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final int SIZE = 3;

    static final char DOT_EMPTY = '•';
    static final char DOT_HUMAN = 'X';
    static final char DOT_AI = 'O';

    static final char HEADER_FIRST_SYMBOL = '♥';
    static final String EMPTY = " ";

    static final char[][] map = new char[SIZE][SIZE];
    static final Scanner in = new Scanner(System.in);
    static final Random random = new Random();
    private int rowNumber;

    public static void main (String[] args) {

        turnGame();
    }

    private static void turnGame () {
        initMap();

        printMap();
        playGame();
    }

    private static void initMap () {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;

            }

        }
    }

    private static void printMap () {
        System.out.println();
        printHeaderMap();

        printBodyMap();
    }

    private static void printHeaderMap () {
        System.out.print(HEADER_FIRST_SYMBOL + EMPTY);

        for (int i = 0; i < SIZE; i++) {
            printMapNumbers(i);

        }
        System.out.println();
    }

    private static void printBodyMap () {
        for (int i = 0; i < SIZE; i++) {
            printMapNumbers(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY);
            }
            System.out.println();

        }
    }

    private static void printMapNumbers (int i) {
        System.out.print(i + 1 + EMPTY);
    }

    private static void playGame () {
        while (true) {
            humanTurn();//ход человека
            printMap();//печать поля
            checkEnd(DOT_HUMAN);//проверка на окончание игры после человека

            aiTurn();//ход ИИ
            printMap();//печать поля
            checkEnd(DOT_AI);//проверка на окончание игры после ИИ
        }
    }

    private static void humanTurn () {
        int rowNumber;
        int columnNumber;

        System.out.println("\nХод человека! Введите нора строки и столбца");
        do {
            rowNumber = 0;
            columnNumber = 0;
            System.out.println("Строка = ");
            if (in.hasNextInt()) {
                rowNumber = in.nextInt() - 1;
            } else {
                in.next();
                System.out.println("Введите число!");
                continue;
            }
            System.out.println("Столбик = ");
            if (in.hasNextInt()) {
                columnNumber = in.nextInt() - 1;
            } else {
                in.next();
                continue;

            }
        } while (!isHumanValidTurn(rowNumber, columnNumber));


        map[rowNumber][columnNumber] = DOT_HUMAN;

    }

    private static boolean isHumanValidTurn (int rowNumber, int columnNumber) {
        //валидность чисел


        //проверка на занятость ячейки

        return isNumbersValid(rowNumber, columnNumber) && isCellOccupancy(rowNumber, columnNumber, false);

    }

    private static boolean isNumbersValid (int rowNumber, int columnNumber) {
        if (rowNumber > SIZE || rowNumber < 0 || columnNumber > SIZE || columnNumber < 0) {
            System.out.println("\nПроверьте значение строки и столбца");
            return false;
        }
        return true;
    }

    private static boolean isCellOccupancy (int rowNumber, int columnNumber, boolean isAi) {
        if (map[rowNumber][columnNumber] != DOT_EMPTY) {
            if (!isAi) {
                System.out.println("\nВы выбрали занятую ячейку!");
            }
            return false;
        }
        return true;
    }

    private static void checkEnd (char symbol) {
        boolean isEnd = false;
        if (checkWin(symbol)) {
            isEnd = true;
            String winMessage;

            if (symbol == DOT_HUMAN) {
                winMessage = "Ура! Вы победили!";
            } else {
                winMessage = "Комплюхтер WIN!";
            }
            System.out.println(winMessage);

        }//победа (ПЕРЕДЕЛАТЬ)


        if (!isEnd && isMapFull()) {
            System.out.println("Ничья!");
            isEnd = true;
        }//ничья


        if (isEnd) {
            System.exit(0);
        }
    }

    private static boolean checkWin (char symbol) {
        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;
        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;
        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
        if (map[2][0] == symbol && map[1][1] == symbol && map[0][2] == symbol) return true;
        return false;

    }

    private static boolean isMapFull () {
        for (char[] chars : map) {
            for (char symbol : chars) {
                if (symbol == DOT_EMPTY) {
                    return false;
                }

            }

        }
        return true;
    }

    private static void aiTurn () {
        int rowNumber;
        int columnNumber;
        System.out.println("\nХод комплюхтера");
        do {
            rowNumber = random.nextInt(SIZE);
            columnNumber = random.nextInt(SIZE);
        } while (!isCellOccupancy(rowNumber, columnNumber, true));

        map[rowNumber][columnNumber] = DOT_AI;
    }
}
