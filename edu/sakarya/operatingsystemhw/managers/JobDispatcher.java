package edu.sakarya.operatingsystemhw.managers;

import edu.sakarya.operatingsystemhw.interfaces.ITask;
import edu.sakarya.operatingsystemhw.models.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JobDispatcher {
    private int executeTime;
    private long cursor;
    private final RandomAccessFile file;

    public JobDispatcher(File file) throws FileNotFoundException {
        this.file = new RandomAccessFile(file, "r");
    }

    public JobDispatcher(String filePath) throws FileNotFoundException {
        this.file = new RandomAccessFile(filePath, "r");
    }

    public void run(){
        // İmleç başlangıç noktasını ayarla
        cursor = 0;
        executeTime = 0;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    for (ITask task : dispatch())
                        QueueManager.getInstance().addTheQueue((Task) task);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public List<ITask> dispatch() throws IOException {
        // Boş bir tasks listesi oluştur
        List<ITask> tasks = new ArrayList<>();

        // Dosya imlecini kaldığı yerden devam etmesi için ayarla
        this.file.seek(cursor);

        // Dosyayı satır satır oku ve executeTime ile başlayan satırları kullanarak
        // ITask nesneleri oluştur
        String line;
        boolean found = false;
        while ((line = this.file.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].startsWith(String.valueOf(executeTime))) {
                tasks.add(new Task(Integer.parseInt(parts[2].trim()), Integer.parseInt(parts[1].trim())));
            } else {
                // Eşleşme bulunamadığı için döngüyü sonlandır
                found = true;
                break;
            }
        }
        if(!found){
            // Dosya imlecini kapat
            file.close();
        }

        // İmleç konumunu güncelle
        cursor = file.getFilePointer();

        // executeTime'i güncelle
        executeTime++;

        // Oluşturulan tasks listesini geri döndür
        return tasks;
    }
}
