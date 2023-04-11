/**
 * OpenKM, Open Document Management System (http://www.openkm.com)
 * Copyright (c) Paco Avila & Josep Llort
 * <p>
 * No bytes were intentionally harmed during the development of this application.
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package sys.dm.util;

import sys.dm.core.Config;
import sys.dm.repository.ProfilingDAO;
import sys.dm.repository.bean.Profiling;
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
