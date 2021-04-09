package com.spring.swingProcess.service;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class swingService {

    /**
     * Function use to find the index of continuous value above a threshold
     * @param data input data
     * @param indexBegin starting index
     * @param indexEnd ending index
     * @param threshold value of the threshold the value should exceed
     * @param winLength minimum number of continuous values that are above the required threshold
     * @return the starting index when the condition is satisfied or return -1
     */
    public int searchContinuityAboveValue(ArrayList<Double> data, int indexBegin, int indexEnd, double threshold, int winLength){
        int index=-1;
        int count=0;

        for(int i=indexBegin;i<indexEnd;i++){
            if(data.get(i)>threshold){
                count++;
                if(index==-1) index=i;
            }
            else{
                index=-1;
                count=0;

            }
            if(count==winLength) return index;
        }

        return -1;

    }

    /**
     * Function use to find the index of continuous value above a threshold, value are iterated from the top.
     * @param data input data
     * @param indexBegin the index starting from top
     * @param indexEnd the lowest index
     * @param thresholdLo the lower end of threshold
     * @param thresholdHi the higher end of threshold
     * @param winLength minimum number of continuous values that are between the threshold limit
     * @return the index when the conditions are satisfied or return -1
     */
    public int backSearchContinuityWithinRange(ArrayList<Double> data, int indexBegin, int indexEnd,double thresholdLo,double thresholdHi,int winLength){
        int index=-1;
        int count=0;
        for(int i=indexBegin;i>indexEnd;i--){
            if(data.get(i)>thresholdLo && data.get(i)<thresholdHi){
                count++;
                if(index==-1) index=i;
            }
            else{
                count=0;
            }
            if(count==winLength) return index;
        }

        return -1;

    }

    /**
     * Function used to find the index where both 2 data values satisfy the required conditions.
     * @param data1 first input data
     * @param data2 second input data
     * @param indexBegin starting index of the data
     * @param indexEnd  last index of the data
     * @param threshold1 input data value must be higher than threshold1
     * @param threshold2 input data values must be lower than threshold2
     * @param winLength minimum number of continuous values that are between the threshold
     * @return the index when the conditions are satisfied or return -1
     */
    public int searchContinuityAboveValueTwoSignals(ArrayList<Double> data1,ArrayList<Double> data2,int indexBegin,int indexEnd,double threshold1,double threshold2,int winLength){
        int index=-1;
        int count=0;

        for(int i=indexBegin;i<indexEnd;i++){
            if(data1.get(i)>threshold1 && data2.get(i)>threshold2){
                count++;
                if(index==-1) index=i;
            }
            else{
                count=0;
                index=-1;
            }
            if(count==winLength) return index;
        }
        return -1;

    }

    /**
     *
     * @param data input data
     * @param indexBegin starting index of the data
     * @param indexEnd last index of the data
     * @param thresholdLo input data value must be higher than threshold1
     * @param thresholdHi input data values must be lower than threshold2
     * @param winLength minimum number of continuous values that are between the threshold
     * @return list is returned where first index contains the intial index and second index contains the last index where the required conditions satisfy.
     *         3rd and 4th index contains the next range of index where the conditions satisfy and so on
     */
    public ArrayList searchMultiContinuityWithinRange(ArrayList<Double> data,int indexBegin,int indexEnd,double thresholdLo,double thresholdHi,int winLength) {
        int index=-1,count=0,i=0;
        ArrayList<Integer> range=new ArrayList<>();
        for(i=indexBegin;i<indexEnd;i++){
            if(data.get(i)>thresholdLo && data.get(i)<thresholdHi){
                count++;
                if(index==-1) index=i;
            }
            else{
                if(count>=winLength) {
                    range.add(index);
                    range.add(i);
                }
                count=0;
                index=-1;
            }

        }
        if(count>=winLength) {
            range.add(index);
            range.add(i);
        }

        return range;
    }
}