package com.unipi.talepis.schedulesound4;

import java.io.IOException;
import android.media.MediaRecorder;

import com.google.firebase.crash.FirebaseCrash;

public class SoundMeter {
        // This class is used to record voice and calculate Amplitude
        static final private double EMA_FILTER = 0.6;
 
        private MediaRecorder mRecorder = null;
        private double mEMA = 0.0;
 
        public void start() {
                
            if (mRecorder == null) {
                     
                        mRecorder = new MediaRecorder();
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mRecorder.setOutputFile("/dev/null"); 
                         
                        try {
                            mRecorder.prepare();
                            mRecorder.start();
                        } catch (IllegalStateException e) {
                            stop();
                            // try to clean up if something went wrong...
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                       mEMA = 0.0;
                }
        }
         
        public void stop() {
                if (mRecorder != null) {
                    try {
                        mRecorder.stop();
                        mRecorder.release();
                        mRecorder = null;
                    } catch (Exception e){
                        FirebaseCrash.report(e);
                    }
                }
        }
         
        public double getAmplitude() {
                if (mRecorder != null)
                        return  (mRecorder.getMaxAmplitude()/2700.0);
                else
                        return 0;
 
        }
 
        public double getAmplitudeEMA() {
                double amp = getAmplitude();
                mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
                return mEMA;
        }
}

