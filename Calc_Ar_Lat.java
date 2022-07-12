package academy.devonline.java.basic.C_A_L_C;
/**
 * Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами:
 * a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)! Решения, в которых каждое число и
 * арифмитеческая операция передаются с новой строки считаются неверными.
 *
 * Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
 *
 * Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по
 * величине и могут быть любыми.
 *
 * Калькулятор умеет работать только с целыми числами.
 *
 * Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки
 * вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
 *
 * При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ
 * ожидается арабскими.
 *
 * При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
 *
 * При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение
 * выбрасывает исключение и завершает свою работу.
 *
 * Результатом операции деления является целое число, остаток отбрасывается.
 *
 * Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
 *
 * Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы
 * меньше единицы, выбрасывается исключение.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calc_Ar_Lat {
    public static void main(String[] args) {

        System.out.println();
        System.out.println(" Калькулятор для вычисления арифметических выражений вида\n" +
                "     ( 5+8 + Enter ) от 0 до 10 для арабских цифр \n" +
                "     ( X-V + Enter ) от I до X для латинских цифр ");

//Создаем String-переменную userInput, считываем в нее введенные данные в верхнем врегистре.
        String userInput = new Scanner(System.in).nextLine().toUpperCase();

// Объявление основных переменных
        int number1, number2;
        char operand = ' ';
        int result;

// Создаём пустой символьный массив  arrowСharUserInput длиной 9 символов
        char[] arrowСharUserInput = new char[9];

// Выявляем символ арифметической операции посредством цикла for
        for (int i = 0; i < userInput.length(); i++) {
// Заполняем массив, приводим в символы посредством метода .charAt
            arrowСharUserInput[i] = userInput.charAt(i);

            if (arrowСharUserInput[i] == '+') {
                operand = '+';
            }
            if (arrowСharUserInput[i] == '-') {
                operand = '-';
            }
            if (arrowСharUserInput[i] == '*') {
                operand = '*';
            }
            if (arrowСharUserInput[i] == '/') {
                operand = '/';
            }
        }
// Создаем String-переменную arrowStringUserInput, переводим в него массив arrowСharUserInput посредством метода .valueOf
        String arrowStringUserInput = String.valueOf(arrowСharUserInput);

// Разбиваем массив на части по шаблону(по операндам). Жаль/, что [operand.length - 1] не катит.((
        String[] part = arrowStringUserInput.split("[-+/*]");

// Определяем вид цифр - (рим\араб), при необходимости присваиваем значение для римских и конвертируем их в арабские значения
// Определяем первое (до знака) число (при необходимости берем значения в "библиотеке" romanToNumber)
        String firstNum = part[0];
// Учитываем первое число и определяем второе (после знака) число (при необходимости обращаемся к  "библиотеке" romanToNumber)
        String secondNum = part[1];
        String onlySecondNum = secondNum.trim();     // Получаем "чистое" второе число  - удаляем пробелы в начале и конце строки.
        number1 = romanToNumber(firstNum);           // Присваиваем значение первой цифры для римского написания
        number2 = romanToNumber(onlySecondNum);      // Присваиваем значение второй цифры для римского написания

        if (number1 < 0 && number2 < 0) {            // проверка цифр на "меньше нуля"
            result = 0;
        } else {
            result = calculated(number1, number2, operand);  // для вычисления результата вызываем функкцию calculated

            //возвращенный вычисленный результат конвертируем в латинские цифры функцией convertNumToRoman
            String resultRoman = convertNumToRoman(result);

            System.out.println();
            System.out.println("         Результат для римских цифр: ");
            System.out.println("                     " + firstNum + " " + operand + " " + onlySecondNum + " = " + resultRoman);
        }
        number1 = Integer.parseInt(firstNum);      // Преобразуем строку firstNum в объект-число методом .parseInt
        number2 = Integer.parseInt(onlySecondNum); // Преобразуем строку onlySecondNum в объект-число методом .parseInt

        result = calculated(number1, number2, operand);     // Вычисляем арабский вариант
        System.out.println();
        System.out.println("               Результат для арабских цифр: ");
        System.out.println("                         " + number1 + " " + operand + " " + number2 + " = " + result);
    }

    // Функция присвоения арабского значения римскому результату
    public static String convertNumToRoman(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
//
        String ArabForRoman = roman[numArabian];
        return ArabForRoman;
    }

    public static int romanToNumber(String forRoman) {
        try {
            switch (forRoman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("        Неверный формат данных");
        }
        return -1;
    }

    public static int calculated(int num1, int num2, char operandSymbol) {
        int result = 0;
        switch (operandSymbol) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("\n             Exception : " + e);
                    System.out.println("    Присутствует недопустимый нулевой параметр! Результат некорректный!");
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("\n      Не корректный знак арефметической операции!");
        }
        return result;
    }
}





