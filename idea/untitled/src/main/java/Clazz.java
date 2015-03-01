import com.darkprograms.speech.microphone.MicrophoneAnalyzer;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Recognizer;
import javaFlacEncoder.FLACFileWriter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * Created by bebe on 12/20/14.
 */
public class Clazz {
    public static void ambientListeningLoop() {
        MicrophoneAnalyzer mic = new MicrophoneAnalyzer(FLACFileWriter.FLAC);
        mic.setAudioFile(new File("AudioTestNow.flac"));
        while (true) {
            mic.open();
            final int THRESHOLD = 66;
            int volume = mic.getAudioVolume();
            boolean isSpeaking = (volume > THRESHOLD);
            if (isSpeaking) {
                try {
                    System.out.println("RECORDING...");
                    mic.captureAudioToFile(mic.getAudioFile());//Saves audio to file.
                    do {
                        Thread.sleep(5000);//Updates every second
                    }
                    while (mic.getAudioVolume() > THRESHOLD);
                    System.out.println("Recording Complete!");
                    System.out.println("Recognizing...");
                    Recognizer rec = new Recognizer(Recognizer.Languages.AUTO_DETECT);
                    GoogleResponse response = rec.getRecognizedDataForFlac(mic.getAudioFile(), 3);
                    displayResponse(response);//Displays output in Console
                    System.out.println("Looping back");//Restarts loops
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("Error Occured");
                } finally {
                    mic.close();//Makes sure microphone closes on exit.
                }
            }
        }
    }

    private static void displayResponse(GoogleResponse gr) {
        if (gr.getResponse() == null) {
            System.out.println((String) null);
            return;
        }
        System.out.println("Google Response: " + gr.getResponse());
        if (gr.getConfidence() != null) {
            System.out.println("Google is " + gr.getConfidence() * 100 + "% confident in"
                    + " the reply");
        }
        System.out.println("Other Possible responses are: ");
        for (String s : gr.getOtherPossibleResponses()) {
            System.out.println("\t" + s);
        }
    }
}
