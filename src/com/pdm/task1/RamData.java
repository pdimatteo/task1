package com.pdm.task1;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class RamData {

    private static Sigar sigar;
    
    public RamData(Sigar s) throws SigarException {
        sigar = s;
    }

        
    public static String printMetric() throws SigarException, InterruptedException {
       String toRet = "";
       
       Map<String, String> map = RamData.getMetric("" + sigar.getPid());
               
       Map<String, String> map2 = getMetric();
       for (Entry<String, String> e : map2.entrySet()) {
    	   String s;
    	   
           try {
        	   s = Sigar.formatSize(Long.valueOf(e.getValue()));
           } catch (NumberFormatException ex) {
        	   s = ((int) (double) Double.valueOf(e.getValue())) + "%";
           }
           
           toRet += "\t" + e.getKey() + ": " + s;
           
           //System.out.print("  " + e.getKey() + ": " + s);
        } 
       
       return toRet;
    }

    public static Map<String, String> getMetric() throws SigarException {
        Mem mem = sigar.getMem();
        return (Map<String, String>) mem.toMap();
    }

    public static Map<String, String> getMetric(String pid) throws SigarException {
        ProcMem state = sigar.getProcMem(pid);
        Map<String, String> map = new TreeMap<String, String>(state.toMap());
       
        return map;
    }
}
