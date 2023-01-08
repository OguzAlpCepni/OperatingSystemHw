package edu.sakarya.operatingsystemhw.interfaces;

import java.io.IOException;

public interface ITask {
    /*
    * Bu method task oluşturulduğunda çağırılacaktır.
    */
    void onCreate() throws IOException;

    /*
    * Bu method taskın çalıştığı her saniye çağırılacaktır.
    */
    void onTick();

    /*
    * Bu method taskın durumu değiştiğinde çalışacaktır.
    */
    void onStateChanged();
}
