package com.spring.swingprocess.service;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SwingService {

    /**
     * Function use to find the index of continuous value above a threshold
     *
     * @param data       input data
     * @param indexBegin starting index
     * @param indexEnd   ending index
     * @param threshold  value of the threshold the value should exceed
     * @param winLength  minimum number of continuous values that are above the required threshold
     * @return the starting index when the condition is satisfied or return null
     */
    public Optional<Integer> searchContinuityAboveValue(List<Double> data, int indexBegin, int indexEnd, double threshold, int winLength) {
        return helper(data, new ArrayList<>() , indexBegin, indexEnd, threshold, Integer.MAX_VALUE, winLength);
    }

    /**
     * Function use to find the index of continuous value above a threshold, value are iterated from the top.
     *
     * @param data        input data
     * @param indexBegin  the index starting from top
     * @param indexEnd    the lowest index
     * @param thresholdLo the lower end of threshold
     * @param thresholdHi the higher end of threshold
     * @param winLength   minimum number of continuous values that are between the threshold limit
     * @return the index when the conditions are satisfied or return null
     */
    public Optional<Integer> backSearchContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd, double thresholdLo, double thresholdHi, int winLength) {
        List<Double> subData = data.subList(indexEnd, indexBegin);
        Collections.reverse(subData);
        Optional<Integer> index=helper(subData, new ArrayList<>() , indexEnd, indexBegin, thresholdLo, thresholdHi, winLength);
        return Optional.of((subData.size() - (index.orElse(0))));
    }

    /**
     * Function used to find the index where both 2 data values satisfy the required conditions.
     *
     * @param data1      first input data
     * @param data2      second input data
     * @param indexBegin starting index of the data
     * @param indexEnd   last index of the data
     * @param thresholdLo input data value must be higher than threshold1
     * @param thresholdHi input data values must be lower than threshold2
     * @param winLength  minimum number of continuous values that are between the threshold
     * @return the index when the conditions are satisfied or return null
     */
    public Optional<Integer> searchContinuityAboveValueTwoSignals(List<Double> data1, List<Double> data2, int indexBegin, int indexEnd, double thresholdLo, double thresholdHi, int winLength) {
        return helper(data1,data2,indexBegin,indexEnd,thresholdLo,thresholdHi,winLength);

    }

    /**
     * @param data        input data
     * @param indexBegin  starting index of the data
     * @param indexEnd    last index of the data
     * @param thresholdLo input data value must be higher than threshold1
     * @param thresholdHi input data values must be lower than threshold2
     * @param winLength   minimum number of continuous values that are between the threshold
     * @return list is returned where first index contains the initial index and second index contains the last index where the required conditions satisfy.
     * 3rd and 4th index contains the next range of index where the conditions satisfy and so on
     */
    public List<Integer> searchMultiContinuityWithinRange(List<Double> data, int indexBegin, int indexEnd, double thresholdLo, double thresholdHi, int winLength) {
        int index = -1;
        int count = 0;
        int i;
        List<Integer> list = new ArrayList<>();
        for (i = indexBegin; i < indexEnd; i++) {
            if (data.get(i) > thresholdLo && data.get(i) < thresholdHi) {
                count++;
                if (index == -1) index = i;
            } else {
                if (count >= winLength) {
                    list.add(index);
                    list.add(i);
                }
                count = 0;
                index = -1;
            }

        }
        if (count >= winLength) {
            list.add(index);
            list.add(i);
        }

        return list;
    }

    /**
     * Helper function to find the index that meets the required conditions
     * @param data input data
     * @param data2 input data
     * @param indexBegin Starting Index of the data
     * @param indexEnd Last index of the data
     * @param thresholdLo Value of the data needs to be higher than thresholdLo
     * @param thresholdHi Value of the data needs to be lower than thresholdHi
     * @param winLength minimum number of continuous values that satisfies the conditions.
     * @return value of the Index
     */
    public Optional<Integer> helper(List<Double> data, List<Double> data2, int indexBegin, int indexEnd, double thresholdLo, double thresholdHi, int winLength) {
        Optional<Integer> index = Optional.empty();
        int count = 0;

        for (int i = indexBegin; i < indexEnd; i++) {
            if (data.get(i) > thresholdLo && ((data2.isEmpty())? data.get(i) < thresholdHi: data2.get(i)>thresholdHi)) {
                count++;
                if (index.isEmpty()) index = Optional.of(i);
            } else {
                index = Optional.empty();
                count = 0;

            }
            if (count == winLength) break;
        }

        return index;

    }
}
