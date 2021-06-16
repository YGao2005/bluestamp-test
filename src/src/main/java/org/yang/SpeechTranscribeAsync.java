package org.yang;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;

import java.io.IOException;

public class SpeechTranscribeAsync {
    public static void main(String[] args) throws IOException {
        try (SpeechClient speechClient = SpeechClient.create()) {
            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.FLAC;
            //int sampleRateHertz = 44100;
            String languageCode = "en-US";
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(encoding)
                    //.setSampleRateHertz(sampleRateHertz)
                    .setLanguageCode(languageCode)
                    .setAudioChannelCount(2)
                    .build();
            String uri = "gs://yang_bluestamp/test.flac";
            //String uri = "abc";
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setUri(uri)
                    .build();
            RecognizeResponse response = speechClient.recognize(config, audio);
            System.out.println(response.getResults(0).getAlternatives(0).getTranscript());
            System.out.println(response.getResults(0).getAlternatives(0).getConfidence());
        }

    }
}