public class CountIsLetterIncr implements Runnable {
    public String[] texts;

    public CountIsLetterIncr(String[] texts) {
        this.texts = texts;
    }

    @Override
    public void run() {
        for (int i = 0; i < texts.length; i++) {
            String text = texts[i];
            if (Main.isLetterIncr(text) && !Main.isOneLetter(text)) {
                switch (text.length()) {
                    case 3:
                        Main.threeLet.getAndIncrement();
                        break;
                    case 4:
                        Main.fourLet.getAndIncrement();
                        break;
                    case 5:
                        Main.fiveLet.getAndIncrement();
                }
            }
        }
    }
}
