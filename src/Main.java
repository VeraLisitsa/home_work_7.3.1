import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger threeLet = new AtomicInteger(0);
    public static AtomicInteger fourLet = new AtomicInteger(0);
    public static AtomicInteger fiveLet = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread thread1 = new Thread(new CountPalindrome(texts));
        Thread thread2 = new Thread(new CountOneLetter(texts));
        Thread thread3 = new Thread(new CountIsLetterIncr(texts));

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Красивых слов с длиной 3: " +threeLet + " шт");
        System.out.println("Красивых слов с длиной 4: " + fourLet + " шт");
        System.out.println("Красивых слов с длиной 5: " + fiveLet + " шт");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        String leftStr = text.substring(0, text.length() / 2);
        String rightStr = (text.length() % 2 == 0 ? text.substring(text.length() / 2, text.length()) : text.substring(text.length() / 2 + 1, text.length()));
        for (int i = 0; i < leftStr.length(); i++) {
            if (leftStr.charAt(i) != rightStr.charAt(rightStr.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOneLetter(String text) {
        char a = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) != a) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLetterIncr(String text) {
        char a = text.charAt(0);
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) < a) {
                return false;
            } else {
                a = text.charAt(i);
            }
        }
        return true;
    }
}

