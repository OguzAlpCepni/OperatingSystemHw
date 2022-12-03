package edu.sakarya.operatingsystemhw.interfaces;

public interface ITask {
    /*
    * Bu method task olusturuldugunda cagirilacaktir.
    */
    void onCreate();

    /*
    * Bu method taskin calistigi her saniye cagirilacaktir.
    */
    void onTick();

    /*
    * Bu method taskin durumu degistiginde calisacaktir.
    */
    void onStateChanged();
}
