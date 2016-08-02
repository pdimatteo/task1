package com.pdm.task1;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class CpuData {
    private static Sigar sigar;

    public CpuData(Sigar s) throws SigarException {
        sigar = s;
    }

  
    public static String printMetric() throws InterruptedException, SigarException {        
        String toRet = "";
    	String pid = "" + sigar.getPid();
        
        toRet += getMetric(pid);
        for(Double d:getMetric()){
        	toRet += "\t"+d;
        }     
        
        return toRet;
    }

    public static Double[] getMetric() throws SigarException {
        CpuPerc cpu = sigar.getCpuPerc();
        double system = cpu.getSys();
        double user = cpu.getUser();
        double idle = cpu.getIdle();
         
        return new Double[] {system, user, idle};
    }

    public static double getMetric(String pid) throws SigarException {
        ProcCpu cpu = sigar.getProcCpu(pid);

        return cpu.getPercent();
    }

}
