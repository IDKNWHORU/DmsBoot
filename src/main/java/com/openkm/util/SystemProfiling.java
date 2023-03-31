package com.openkm.util;

import com.openkm.core.Config;
import com.openkm.dao.ProfilingDAO;
import com.openkm.dao.bean.Profiling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class SystemProfiling {
    private static Logger log = LoggerFactory.getLogger(SystemProfiling.class);

    public static void log(String param, long time) {
        if (Config.SYSTEM_PROFILING) {
            try {
                StackTraceElement[] trace = (new Throwable()).getStackTrace();

                if (trace.length >= 1) {
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement caller = trace[1];

                    if (trace.length >= 2) {
                        for (int i = 2; i < trace.length; i++) {
                            if (trace[i].getClassName().startsWith("com.openkm")) {
                                sb.append(trace[i]);
                                sb.append("\n");
                            }
                        }
                    }

                    Profiling vo = new Profiling();
                    vo.setDate(Calendar.getInstance());
                    vo.setUser(PrincipalUtils.getUser());
                    vo.setClazz(caller.getClassName());
                    vo.setMethod(caller.getMethodName());
                    vo.setParams(param);
                    vo.setTime(time);
                    vo.setTrace(sb.toString());
                    ProfilingDAO.create(vo);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
