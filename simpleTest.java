import java.util.Random;

public class Main {

    //emotions menu
    private static String[] emotions = {"anger", "joy",
            "neutral", "fear", "disgust", "sadness", "surprise"};

    public static void fetchServer() throws Exception {
        EmotionServerHandler esh = EmotionServerHandler.getInstance();

        Random randomInt = new Random();
        //iterating through the emotions menu 10 times and getting random emotions
        for(int j = 0; j < 20; j++) {
            int index = randomInt.nextInt(7);
            esh.sendEmotionToServer(emotions[index], 1);
        }
    }

    public static void main(String[] args) throws Exception {
        fetchServer();
    }
}
