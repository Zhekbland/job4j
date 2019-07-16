package ru.job4j.io.write;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Analysis filters server's log and create log of time when server was no run.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 20.06.2019.
 */
public class Analysis {

    /**
     * Create log of time when server was no run.
     *
     * @param source - server's log of state
     * @param target - server's log of time when server was no run.
     */
    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter bw = new PrintWriter(new FileOutputStream(target))) {
            String str;
            String startNote = null;
            while ((str = br.readLine()) != null) {
                if ((str.contains("400") || str.contains("500")) && startNote == null) {
                    startNote = str.substring(4);
                } else if (!(str.contains("400") || str.contains("500")) && startNote != null && str.length() != 0) {
                    bw.write(startNote + ";" + str.substring(4) + "\n");
                    startNote = null;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Create list of result's log.
     *
     * @param resultFile - log of time when server was no run.
     * @return list of result's log.
     */
    public List<String> getListOfTarget(String resultFile) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resultFile))) {
            String str;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        return list;
    }
}
