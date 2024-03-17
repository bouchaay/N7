package ReadderWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;

public class TxFileReaderWriter implements FileReaderWriter {
    private String fname;
    private String mode;

    private BufferedReader br;
    private BufferedWriter bw;
    private int index ;     
    public TxFileReaderWriter(String fname) {
        this.fname = fname;
        this.index = 0;
    }
    public void write(KV record) {
        try{
            // print the key and value
            //System.out.println("Writing key: " + record.getKey() + " value: " + record.getValue());

            bw.write( record.getValue() );
            bw.newLine();
            index ++;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public KV read() {
       try{
           String line = br.readLine();
           
           if(line == null){
               return null;
           }
            
           index ++;
           return new KV( Integer.toString(index), line);
         }catch(Exception e){
             e.printStackTrace();
             return null;
         }
    }

    @Override
    public void open(String mode) {
        try{
            this.mode = mode;
            if(mode.equals("w")){
                //System.out.println("Opening file " + fname + " for writing...");
                // print the actual path
                //System.out.println("Actual path : " + new File(".").getAbsolutePath());
                this.bw = new BufferedWriter(new FileWriter(new File(fname)));

            }else if(mode.equals("r")){
                //System.out.println("Opening file " + fname + " for reading...");
                //System.out.println("Actual path : " + new File(".").getAbsolutePath());
                this.br = new BufferedReader(new FileReader(new File(fname)));
            }else{
                //System.out.println("Error: mode not recognized");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        try{
            if(mode.equals("w")){
                System.out.println("Closing file " + fname + " for writing...");
                this.bw.close();

            }else if(mode.equals("r")){
                System.out.println("Closing file " + fname + " for reading...");
                this.br.close();
            }else{
                System.out.println("Error: mode not recognized");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public long getIndex() {
        return index;
    }

    @Override
    public String getFname() {
        
        return fname;        
    }

    @Override
    public void setFname(String fname) {
        this.fname = fname;
    }
    @Override
    public int getLineCount() {
        int count = 0;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(fname)));
            while(reader.readLine() != null){
                count++;
            }
            reader.close();
    }catch(Exception e){
        e.printStackTrace();
    }
        return count;
    }

    
    
}
