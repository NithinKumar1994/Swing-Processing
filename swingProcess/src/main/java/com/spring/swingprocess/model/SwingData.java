package com.spring.swingprocess.model;

import lombok.Getter;
import lombok.Setter;
import java.io.*;
import java.util.ArrayList;

@Getter
@Setter
public class SwingData {
    private ArrayList<Integer> timeStamp;
    private ArrayList<Double> ax;
    private ArrayList<Double> ay;
    private ArrayList<Double> az;
    private ArrayList<Double> wx;
    private ArrayList<Double> wy;
    private ArrayList<Double> wz;

    public SwingData(){
        timeStamp=new ArrayList<>();
        ax=new ArrayList<>();
        ay=new ArrayList<>();
        az=new ArrayList<>();
        wx=new ArrayList<>();
        wy=new ArrayList<>();
        wz=new ArrayList<>();
    }

    /**
     * Function used to read input file and store the swing data
     * @param file input file
     */
    public void scanFile(File file){
        String s;
        try(BufferedReader br=new BufferedReader(new FileReader(file))) {
            while ((s = br.readLine()) != null) {
                String[] line = s.split(",");
                timeStamp.add(Integer.parseInt(line[0]));
                ax.add(Double.parseDouble(line[1]));
                ay.add(Double.parseDouble(line[2]));
                az.add(Double.parseDouble(line[3]));
                wx.add(Double.parseDouble(line[4]));
                wy.add(Double.parseDouble(line[5]));
                wz.add(Double.parseDouble(line[6]));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
