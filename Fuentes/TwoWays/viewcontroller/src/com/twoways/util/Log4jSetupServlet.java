package com.twoways.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.Logger;

public class Log4jSetupServlet extends HttpServlet {


    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String props = config.getInitParameter("props");
        String filename = config.getServletContext().getRealPath(props);
        System.out.println("log4j=" + filename);

        if ((filename == null) || (props.length() == 0) || 
            (!new File(filename).isFile())) {
            System.err.println("ERROR: Cannot read the configuration file. Please check the path of the config init param in web.xml");

            throw new ServletException();
        }

        String watch = config.getInitParameter("watch");

        if ((watch != null) && (watch.equalsIgnoreCase("true")))
            DOMConfigurator.configureAndWatch(filename);
        else
            DOMConfigurator.configure(filename);
    }

    protected void doGet(HttpServletRequest p0, 
                         HttpServletResponse p1) throws IOException, 
                                                        ServletException {
        super.doGet(p0, p1);

        Logger logger = Logger.getLogger(this.getClass());

        logger.debug("Here is some DEBUG");
        logger.info("Here is some INFO");
        logger.warn("Here is some WARN");
        logger.error("Here is some ERROR");
        logger.fatal("Here is some FATAL");
    }
}
