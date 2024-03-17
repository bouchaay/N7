package ReadderWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;

public class KvFileReaderWriter implements FileReaderWriter {
    private String fname;

    private int index;
    private BufferedReader br;
    private BufferedWriter bw;

    public KvFileReaderWriter(String fname) {
        this.fname = fname;
        this.index = 0;
    }

    @Override
    public KV read() {
        try {
            String line = br.readLine();

            if (line == null) {
                return null;
            }
            String[] parts = line.split(KV.SEPARATOR);

            index++;
            return new KV(parts[0], parts[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void write(KV record) {
        try {
            String line = record.getKey() + KV.SEPARATOR + record.getValue();
            bw.write(line);
            bw.newLine();
            index++;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void open(String mode) {
        try {
            if (mode.equals("w")) {
                //System.out.println("Opening file " + fname + " for writing...");
                this.bw = new BufferedWriter(new FileWriter(new java.io.File(fname)));
            } else if (mode.equals("r")) {
                //System.out.println("Opening file " + fname + " for reading...");
                this.br = new BufferedReader(new FileReader(new File(fname)));
            } else {
                //System.out.println("Error: mode not recognized");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (bw != null) {
                bw.close();
            }
            if (br != null) {
                br.close();
            }
        }catch (Exception e){
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
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fname)));
            while (br.readLine() != null) {
                count++;
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
}