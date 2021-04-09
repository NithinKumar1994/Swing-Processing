package com.spring.swingProcess.view;

import com.spring.swingProcess.model.swingData;
import com.spring.swingProcess.service.swingService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.*;

@Component
public class run {

        @EventListener(ApplicationReadyEvent.class)
        public void runAfterStartup() throws IOException {
            out.println("Program running........");
            swingService ss=new swingService();
            File file=new File("C:\\Users\\pnith\\Downloads\\swingProcess\\src\\test\\java\\com\\spring\\swingProcess\\latestSwing.csv");
            swingData sd=new swingData();
            sd.scanFile(file);
            System.out.println(ss.searchContinuityAboveValue(sd.getAx(),1,120,1,10));
            System.out.println(ss.searchContinuityAboveValueTwoSignals(sd.getAx(),sd.getAy(),1,120,0.5,0.5,10));
            System.out.println(ss.backSearchContinuityWithinRange(sd.getAx(),1000,1,1,2,5));
            System.out.println(ss.searchMultiContinuityWithinRange(sd.getAx(),1,1000,0.5,1,10));
        }

}
