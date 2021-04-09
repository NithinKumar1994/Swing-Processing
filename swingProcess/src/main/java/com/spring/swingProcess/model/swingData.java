package com.spring.swingProcess.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;

@Getter
@Setter
public class swingData {
    private ArrayList<Double> ax;
    private ArrayList<Double> ay;
    private ArrayList<Double> az;
    private ArrayList<Double> wx;
    private ArrayList<Double> wy;
    private ArrayList<Double> wz;

    public swingData(){
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
     * @throws IOException
     */
    public void scanFile(File file) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(file));
        String s="";
        try {
            while ((s = br.readLine()) != null) {
                String[] line = s.split(",");
                ax.add(Double.parseDouble(line[1]));
                ay.add(Double.parseDouble(line[2]));
                az.add(Double.parseDouble(line[3]));
                wx.add(Double.parseDouble(line[4]));
                wy.add(Double.parseDouble(line[5]));
                wz.add(Double.parseDouble(line[6]));
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        finally {
            br.close();
        }

    }
}
