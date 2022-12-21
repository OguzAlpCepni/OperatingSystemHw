package edu.sakarya.operatingsystemhw.interfaces;

import java.io.IOException;

public interface ITask {
    /*
    * Bu method task olusturuldugunda cagirilacaktir.
    */
    void onCreate() throws IOException;

    /*
    * Bu method taskin calistigi her saniye cagirilacaktir.
    */
    void onTick();

    /*
    * Bu method taskin durumu degistiginde calisacaktir.
    */
    void onStateChanged();
}
