package br.com.progress.codeshare.esbservice.TerminateService;
import java.util.*;

import com.sonicsw.xq.XQInitContext;
import com.sonicsw.xq.XQLog;
import com.sonicsw.xq.XQParameterInfo;
import com.sonicsw.xq.XQParameters;
import com.sonicsw.xq.XQServiceContext;
import com.sonicsw.xq.XQServiceEx;
import com.sonicsw.xq.XQServiceException;

public class TerminateService
    implements XQServiceEx
{
	
	private XQLog m_xqLog;
    private String m_logPrefix;
    private static int s_major = 1;
    private static int s_minor = 0;
    private static int s_buildNumber = 0;
	
    public TerminateService()
    {
        m_logPrefix = "";
    }

    public void init(XQInitContext initialContext)
        throws XQServiceException
    {
        XQParameters params = initialContext.getParameters();
        m_xqLog = initialContext.getLog();
        setLogPrefix(params);
        m_xqLog.logInformation(m_logPrefix + " Initializing ...");
        writeStartupMessage(params);
        writeParameters(params);
        m_xqLog.logInformation(m_logPrefix + " Initialized ...");
    }

    public void service(XQServiceContext ctx)
        throws XQServiceException
    {
        m_xqLog.logDebug(m_logPrefix + "Service processing...");
        m_xqLog.logDebug(m_logPrefix + "Service processed...");
    }

    public void destroy()
    {
        m_xqLog.logInformation(m_logPrefix + "Destroying...");
        m_xqLog.logInformation(m_logPrefix + "Destroyed...");
    }

    public void start()
    {
        m_xqLog.logInformation(m_logPrefix + "Starting...");
        m_xqLog.logInformation(m_logPrefix + "Started...");
    }

    public void stop()
    {
        m_xqLog.logInformation(m_logPrefix + "Stopping...");
        m_xqLog.logInformation(m_logPrefix + "Stopped...");
    }

    protected void setLogPrefix(XQParameters params)
    {
        String serviceName = params.getParameter("SonicXQ.ServiceName", 1);
        m_logPrefix = "[ " + serviceName + " ]";
    }

    protected String getVersion()
    {
        return s_major + "." + s_minor + ". build " + s_buildNumber;
    }

    protected void writeStartupMessage(XQParameters params)
    {
        StringBuffer buffer = new StringBuffer();
        String serviceTypeName = params.getParameter("SonicXQ.ServiceType", 1);
        buffer.append("\n\n");
        buffer.append("\t\t " + serviceTypeName + "\n ");
        buffer.append("\t\t Version ");
        buffer.append(" " + getVersion());
        buffer.append("\n");
        m_xqLog.logInformation(buffer.toString());
    }

    protected void writeParameters(XQParameters params)
    {
        Map map = params.getAllInfo();
        for(Iterator iter = map.values().iterator(); iter.hasNext();)
        {
            XQParameterInfo info = (XQParameterInfo)iter.next();
            if(info.getType() == 2)
                m_xqLog.logInformation(m_logPrefix + "Parameter Name =  " + info.getName());
            else
            if(info.getType() == 1)
                m_xqLog.logInformation(m_logPrefix + "Parameter Name = " + info.getName());
            if(info.getRef() != null)
            {
                m_xqLog.logInformation(m_logPrefix + "Parameter Reference = " + info.getRef());
                m_xqLog.logInformation(m_logPrefix + "----Parameter Value Start--------");
                m_xqLog.logInformation("\n" + info.getValue() + "\n");
                m_xqLog.logInformation(m_logPrefix + "----Parameter Value End--------");
            } else
            {
                m_xqLog.logInformation(m_logPrefix + "Parameter Value = " + info.getValue());
            }
        }

    }

    
}
