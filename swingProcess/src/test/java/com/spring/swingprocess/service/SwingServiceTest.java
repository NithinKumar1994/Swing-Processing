package com.spring.swingprocess.service;

import com.spring.swingprocess.model.SwingData;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class SwingServiceTest {
    SwingService ss;
    SwingData sd;

    SwingServiceTest(){
        ss=new SwingService();
        sd=new SwingData();
        sd.scanFile(new File("src/test/java/latestSwing.csv"));
    }

    @org.junit.jupiter.api.Test
    void searchContinuityAboveValue() {
        Optional<Integer> index=ss.searchContinuityAboveValue( sd.getAx(),1,120,1,10);
        assertTrue(index.isPresent());
        assertEquals(index.get(),37);
    }

    @org.junit.jupiter.api.Test
    void backSearchContinuityWithinRange() {
        Optional<Integer> index=ss.backSearchContinuityWithinRange( sd.getAx(),1000,1,1,2,5);
        assertTrue(index.isPresent());
        assertEquals(index.get(),646);
    }

    @org.junit.jupiter.api.Test
    void searchContinuityAboveValueTwoSignals() {
        Optional<Integer> index=ss.searchContinuityAboveValueTwoSignals(sd.getAx(), sd.getAy(), 1, 120, 0.5, 0.5, 10);
        assertTrue(index.isPresent());
        assertEquals(index.get(),26);
    }

    @org.junit.jupiter.api.Test
    void searchMultiContinuityWithinRange() {
        List<Integer> list= ss.searchMultiContinuityWithinRange(sd.getAx(), 1, 1000, 0.5, 1, 10);
        List<Integer> test= Arrays.asList(26, 37, 73, 287, 337, 476, 477, 622, 647, 712);

        assertEquals(list.size(),test.size());
        assertEquals(list.get(5),test.get(5));
        assertTrue(list.contains(712));
        assertFalse(list.contains(1));
    }

    @Test
    void helper() {
        Optional<Integer> index=ss.helper(sd.getAx(),new ArrayList<>(),1,120,1,10,10);
        assertTrue(index.isPresent());
        assertEquals(index.get(),37);
        index=ss.helper( sd.getAx(),sd.getAy(),1,120,0.5,0.5,10);
        assertTrue(index.isPresent());
        assertEquals(index.get(),26);
    }
}