package com.ftninformatika.servisi22termin;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBoundService extends Service {

    private TextChanger myTextChanger = null;

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder{
        // Vraca instancu servisa u kom se bajnder nalazi
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public interface TextChanger {
        void changeText(String text);
    }

    public void setTextChanger(TextChanger changer) {
        this.myTextChanger = changer;
    }



    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void getVoce(int voce) {
        String vocka;

        switch (voce) {
            case 0:
                vocka = "Jabuka";
                break;
            case 1:
                vocka = "Kruska";
                break;
            case 2:
                vocka = "Banana";
                break;
            default:
                vocka = "Nepoznato";
        }
        if (myTextChanger != null) {
            myTextChanger.changeText(vocka);
        }
    }

}
