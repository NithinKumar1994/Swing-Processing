package com.spring.swingprocess.view;

import com.spring.swingprocess.model.SwingData;
import com.spring.swingprocess.service.SwingService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

@Component
public class Run {
    private SwingService ss;
    private SwingData sd;
    private File file;

    Run(){
        ss= new SwingService();
        sd= new SwingData();
    }



    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup(){
        System.out.println("Program running........");

        String config = "src/main/resources/application.properties";
        try(FileInputStream configFileStream = new FileInputStream(config)) {
            Properties prop = new Properties();
            prop.load(configFileStream);
            file = new File(prop.getProperty("spring.filename"));
        }
        catch (IOException e){
            System.out.println();
        }

        sd.scanFile(file);
        System.out.println(ss.searchContinuityAboveValue(sd.getAx(), 1, 120, 1, 10));
        System.out.println(ss.searchContinuityAboveValueTwoSignals(sd.getAx(), sd.getAy(), 1, 120, 0.5, 0.5, 10));
        System.out.println(ss.backSearchContinuityWithinRange(sd.getAx(), 1000, 1, 1, 2, 5));
        System.out.println(ss.searchMultiContinuityWithinRange(sd.getAx(), 1, 1000, 0.5, 1, 10));
    }

}
