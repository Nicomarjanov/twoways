
import oracle.jsp.runtime.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;


public class _cliente extends com.orionserver.http.OrionHttpJspPage {


  // ** Begin Declarations


  // ** End Declarations

  public void _jspService(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, ServletException {

    response.setContentType( "text/html;charset=windows-1252");
    /* set up the intrinsic variables using the pageContext goober:
    ** session = HttpSession
    ** application = ServletContext
    ** out = JspWriter
    ** page = this
    ** config = ServletConfig
    ** all session/app beans declared in globals.jsa
    */
    PageContext pageContext = JspFactory.getDefaultFactory().getPageContext( this, request, response, null, true, JspWriter.DEFAULT_BUFFER, true);
    // Note: this is not emitted if the session directive == false
    HttpSession session = pageContext.getSession();
    int __jsp_tag_starteval;
    ServletContext application = pageContext.getServletContext();
    JspWriter out = pageContext.getOut();
    _cliente page = this;
    ServletConfig config = pageContext.getServletConfig();

    try {


      out.write(__oracle_jsp_text[0]);
      out.write(__oracle_jsp_text[1]);
      out.write(__oracle_jsp_text[2]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_1=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value escapeXml");
        __jsp_taghandler_1.setParent(null);
        __jsp_taghandler_1.setValue("${mensaje}");
        __jsp_taghandler_1.setEscapeXml("false");
        __jsp_tag_starteval=__jsp_taghandler_1.doStartTag();
        if (__jsp_taghandler_1.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_1,1);
      }
      out.write(__oracle_jsp_text[3]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_2=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_2.setParent(null);
        __jsp_taghandler_2.setValue("${cliente.cliId}");
        __jsp_tag_starteval=__jsp_taghandler_2.doStartTag();
        if (__jsp_taghandler_2.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_2,1);
      }
      out.write(__oracle_jsp_text[4]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_3=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_3.setParent(null);
        __jsp_taghandler_3.setValue("${cliente.cliName}");
        __jsp_tag_starteval=__jsp_taghandler_3.doStartTag();
        if (__jsp_taghandler_3.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_3,1);
      }
      out.write(__oracle_jsp_text[5]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_4=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_4.setParent(null);
        __jsp_taghandler_4.setValue("${cliente.cliDescription}");
        __jsp_tag_starteval=__jsp_taghandler_4.doStartTag();
        if (__jsp_taghandler_4.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_4,1);
      }
      out.write(__oracle_jsp_text[6]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_5=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_5.setParent(null);
        __jsp_taghandler_5.setValue("${cliente.cliMail}");
        __jsp_tag_starteval=__jsp_taghandler_5.doStartTag();
        if (__jsp_taghandler_5.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_5,1);
      }
      out.write(__oracle_jsp_text[7]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_6=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_6.setParent(null);
        __jsp_taghandler_6.setValue("${cliente.cliPhone}");
        __jsp_tag_starteval=__jsp_taghandler_6.doStartTag();
        if (__jsp_taghandler_6.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_6,1);
      }
      out.write(__oracle_jsp_text[8]);
      {
        org.apache.taglibs.standard.tag.el.core.ForEachTag __jsp_taghandler_7=(org.apache.taglibs.standard.tag.el.core.ForEachTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.ForEachTag.class,"org.apache.taglibs.standard.tag.el.core.ForEachTag items var");
        __jsp_taghandler_7.setParent(null);
        __jsp_taghandler_7.setItems("${listaMoneda}");
        __jsp_taghandler_7.setVar("item");
        try {
          __jsp_tag_starteval=__jsp_taghandler_7.doStartTag();
          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
          {
            do {
              out.write(__oracle_jsp_text[9]);
              {
                org.apache.taglibs.standard.tag.common.core.ChooseTag __jsp_taghandler_8=(org.apache.taglibs.standard.tag.common.core.ChooseTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.common.core.ChooseTag.class,"org.apache.taglibs.standard.tag.common.core.ChooseTag");
                __jsp_taghandler_8.setParent(__jsp_taghandler_7);
                __jsp_tag_starteval=__jsp_taghandler_8.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  do {
                    out.write(__oracle_jsp_text[10]);
                    {
                      org.apache.taglibs.standard.tag.el.core.WhenTag __jsp_taghandler_9=(org.apache.taglibs.standard.tag.el.core.WhenTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.WhenTag.class,"org.apache.taglibs.standard.tag.el.core.WhenTag test");
                      __jsp_taghandler_9.setParent(__jsp_taghandler_8);
                      __jsp_taghandler_9.setTest("${item.curId == cliente.currencyTO.curId}");
                      __jsp_tag_starteval=__jsp_taghandler_9.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        do {
                          out.write(__oracle_jsp_text[11]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_10=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_10.setParent(__jsp_taghandler_9);
                            __jsp_taghandler_10.setValue("${item.curId}");
                            __jsp_tag_starteval=__jsp_taghandler_10.doStartTag();
                            if (__jsp_taghandler_10.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_10,4);
                          }
                          out.write(__oracle_jsp_text[12]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_11=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_11.setParent(__jsp_taghandler_9);
                            __jsp_taghandler_11.setValue("${item.curName}");
                            __jsp_tag_starteval=__jsp_taghandler_11.doStartTag();
                            if (__jsp_taghandler_11.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_11,4);
                          }
                          out.write(__oracle_jsp_text[13]);
                        } while (__jsp_taghandler_9.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                      }
                      if (__jsp_taghandler_9.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_9,3);
                    }
                    out.write(__oracle_jsp_text[14]);
                    {
                      org.apache.taglibs.standard.tag.common.core.OtherwiseTag __jsp_taghandler_12=(org.apache.taglibs.standard.tag.common.core.OtherwiseTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class,"org.apache.taglibs.standard.tag.common.core.OtherwiseTag");
                      __jsp_taghandler_12.setParent(__jsp_taghandler_8);
                      __jsp_tag_starteval=__jsp_taghandler_12.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        do {
                          out.write(__oracle_jsp_text[15]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_13=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_13.setParent(__jsp_taghandler_12);
                            __jsp_taghandler_13.setValue("${item.curId}");
                            __jsp_tag_starteval=__jsp_taghandler_13.doStartTag();
                            if (__jsp_taghandler_13.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_13,4);
                          }
                          out.write(__oracle_jsp_text[16]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_14=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_14.setParent(__jsp_taghandler_12);
                            __jsp_taghandler_14.setValue("${item.curName}");
                            __jsp_tag_starteval=__jsp_taghandler_14.doStartTag();
                            if (__jsp_taghandler_14.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_14,4);
                          }
                          out.write(__oracle_jsp_text[17]);
                        } while (__jsp_taghandler_12.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                      }
                      if (__jsp_taghandler_12.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_12,3);
                    }
                    out.write(__oracle_jsp_text[18]);
                  } while (__jsp_taghandler_8.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                }
                if (__jsp_taghandler_8.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_8,2);
              }
              out.write(__oracle_jsp_text[19]);
            } while (__jsp_taghandler_7.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
          }
          if (__jsp_taghandler_7.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
            return;
        } catch (Throwable th) {
          __jsp_taghandler_7.doCatch(th);
        } finally {
          __jsp_taghandler_7.doFinally();
        }
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_7,1);
      }
      out.write(__oracle_jsp_text[20]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_15=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_15.setParent(null);
        __jsp_taghandler_15.setValue("${cliente.cliAddress}");
        __jsp_tag_starteval=__jsp_taghandler_15.doStartTag();
        if (__jsp_taghandler_15.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_15,1);
      }
      out.write(__oracle_jsp_text[21]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_16=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_16.setParent(null);
        __jsp_taghandler_16.setValue("${cliente.cliPostalCode}");
        __jsp_tag_starteval=__jsp_taghandler_16.doStartTag();
        if (__jsp_taghandler_16.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_16,1);
      }
      out.write(__oracle_jsp_text[22]);
      {
        org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_17=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
        __jsp_taghandler_17.setParent(null);
        __jsp_taghandler_17.setValue("${cliente.cliCountry}");
        __jsp_tag_starteval=__jsp_taghandler_17.doStartTag();
        if (__jsp_taghandler_17.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
          return;
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_17,1);
      }
      out.write(__oracle_jsp_text[23]);
      {
        org.apache.taglibs.standard.tag.el.core.ForEachTag __jsp_taghandler_18=(org.apache.taglibs.standard.tag.el.core.ForEachTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.ForEachTag.class,"org.apache.taglibs.standard.tag.el.core.ForEachTag items var");
        __jsp_taghandler_18.setParent(null);
        __jsp_taghandler_18.setItems("${listaTarifa}");
        __jsp_taghandler_18.setVar("item");
        try {
          __jsp_tag_starteval=__jsp_taghandler_18.doStartTag();
          if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
          {
            do {
              out.write(__oracle_jsp_text[24]);
              {
                org.apache.taglibs.standard.tag.common.core.ChooseTag __jsp_taghandler_19=(org.apache.taglibs.standard.tag.common.core.ChooseTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.common.core.ChooseTag.class,"org.apache.taglibs.standard.tag.common.core.ChooseTag");
                __jsp_taghandler_19.setParent(__jsp_taghandler_18);
                __jsp_tag_starteval=__jsp_taghandler_19.doStartTag();
                if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                {
                  do {
                    out.write(__oracle_jsp_text[25]);
                    {
                      org.apache.taglibs.standard.tag.el.core.WhenTag __jsp_taghandler_20=(org.apache.taglibs.standard.tag.el.core.WhenTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.WhenTag.class,"org.apache.taglibs.standard.tag.el.core.WhenTag test");
                      __jsp_taghandler_20.setParent(__jsp_taghandler_19);
                      __jsp_taghandler_20.setTest("${item.ratId == cliente.rateTO.ratId}");
                      __jsp_tag_starteval=__jsp_taghandler_20.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        do {
                          out.write(__oracle_jsp_text[26]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_21=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_21.setParent(__jsp_taghandler_20);
                            __jsp_taghandler_21.setValue("${item.ratId}");
                            __jsp_tag_starteval=__jsp_taghandler_21.doStartTag();
                            if (__jsp_taghandler_21.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_21,4);
                          }
                          out.write(__oracle_jsp_text[27]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_22=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_22.setParent(__jsp_taghandler_20);
                            __jsp_taghandler_22.setValue("${item.ratName}");
                            __jsp_tag_starteval=__jsp_taghandler_22.doStartTag();
                            if (__jsp_taghandler_22.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_22,4);
                          }
                          out.write(__oracle_jsp_text[28]);
                        } while (__jsp_taghandler_20.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                      }
                      if (__jsp_taghandler_20.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_20,3);
                    }
                    out.write(__oracle_jsp_text[29]);
                    {
                      org.apache.taglibs.standard.tag.common.core.OtherwiseTag __jsp_taghandler_23=(org.apache.taglibs.standard.tag.common.core.OtherwiseTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class,"org.apache.taglibs.standard.tag.common.core.OtherwiseTag");
                      __jsp_taghandler_23.setParent(__jsp_taghandler_19);
                      __jsp_tag_starteval=__jsp_taghandler_23.doStartTag();
                      if (OracleJspRuntime.checkStartTagEval(__jsp_tag_starteval))
                      {
                        do {
                          out.write(__oracle_jsp_text[30]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_24=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_24.setParent(__jsp_taghandler_23);
                            __jsp_taghandler_24.setValue("${item.ratId}");
                            __jsp_tag_starteval=__jsp_taghandler_24.doStartTag();
                            if (__jsp_taghandler_24.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_24,4);
                          }
                          out.write(__oracle_jsp_text[31]);
                          {
                            org.apache.taglibs.standard.tag.el.core.OutTag __jsp_taghandler_25=(org.apache.taglibs.standard.tag.el.core.OutTag)OracleJspRuntime.getTagHandler(pageContext,org.apache.taglibs.standard.tag.el.core.OutTag.class,"org.apache.taglibs.standard.tag.el.core.OutTag value");
                            __jsp_taghandler_25.setParent(__jsp_taghandler_23);
                            __jsp_taghandler_25.setValue("${item.ratName}");
                            __jsp_tag_starteval=__jsp_taghandler_25.doStartTag();
                            if (__jsp_taghandler_25.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                            OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_25,4);
                          }
                          out.write(__oracle_jsp_text[32]);
                        } while (__jsp_taghandler_23.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                      }
                      if (__jsp_taghandler_23.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                        return;
                      OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_23,3);
                    }
                    out.write(__oracle_jsp_text[33]);
                  } while (__jsp_taghandler_19.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
                }
                if (__jsp_taghandler_19.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
                OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_19,2);
              }
              out.write(__oracle_jsp_text[34]);
            } while (__jsp_taghandler_18.doAfterBody()==javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN);
          }
          if (__jsp_taghandler_18.doEndTag()==javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
            return;
        } catch (Throwable th) {
          __jsp_taghandler_18.doCatch(th);
        } finally {
          __jsp_taghandler_18.doFinally();
        }
        OracleJspRuntime.releaseTagHandler(pageContext,__jsp_taghandler_18,1);
      }
      out.write(__oracle_jsp_text[35]);

    }
    catch (Throwable e) {
      if (!(e instanceof javax.servlet.jsp.SkipPageException)){
        try {
          if (out != null) out.clear();
        }
        catch (Exception clearException) {
        }
        pageContext.handlePageException(e);
      }
    }
    finally {
      OracleJspRuntime.extraHandlePCFinally(pageContext, true);
      JspFactory.getDefaultFactory().releasePageContext(pageContext);
    }

  }
  private static final char __oracle_jsp_text[][]=new char[36][];
  static {
    try {
    __oracle_jsp_text[0] = 
    "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n\"http://www.w3.org/TR/html4/loose.dtd\">\n".toCharArray();
    __oracle_jsp_text[1] = 
    "\n".toCharArray();
    __oracle_jsp_text[2] = 
    "\n<html>\n  <head>\n   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\"/>\n    <link href=\"./twoways.css\" rel=\"stylesheet\" type=\"text/css\"/>\n    <script type='text/javascript' src=\"./js/cliente.js\"></script>\n    <script type='text/javascript' src=\"./js/utils.js\"></script>\n    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>\n    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>\n    <script type='text/javascript' src='/twoways/dwr/util.js'></script>\n    \n    <title>cliente</title>\n    \n  </head>\n \n  \n  <body>\n  ".toCharArray();
    __oracle_jsp_text[3] = 
    "\n  <form name=\"cliente\" action=\"clientes\" method=\"POST\">\n  <input type=\"hidden\" id=\"accion\" name=\"accion\" value=\"\"/>\n  <input type=\"hidden\" id=\"cliId\" name=\"cliId\" value=\"".toCharArray();
    __oracle_jsp_text[4] = 
    "\"/>\n  <table width=\"100%\">\n  <tr>\n    <th colspan=\"6\" class=\"tw_form\">Ingrese los campos con los datos de los clientes</th>\n  </tr>\n  <tr>\n  </tr>\n  <tr>\n    <td>Nombre o Razón social:</td>\n    <td><input type=\"text\" class=\"tw_form\" id=\"nomCliente\" name=\"nomCliente\"  value=\"".toCharArray();
    __oracle_jsp_text[5] = 
    "\"  size=\"50\" maxlength=\"100\"  onkeyup=\"buscarClientes()\"  onfocus=\"javascript:this.style.background='#FFFFFF';\"></input></td>\n    <td>Descripción:</td>\n    <td colspan=3><input type=\"text\" class=\"tw_form\" id=\"descCliente\" name=\"descCliente\"  value=\"".toCharArray();
    __oracle_jsp_text[6] = 
    "\" size=\"100\" maxlength=\"250\"></input></td>\n  </tr>\n  <tr>\n    <td>Mail:</td>\n    <td><input type=\"text\" class=\"tw_form\" id=\"mailCliente\"  name=\"mailCliente\"  value=\"".toCharArray();
    __oracle_jsp_text[7] = 
    "\" size=\"30\" maxlength=\"100\"></input></td>\n    <td>Teléfono:</td>\n    <td><input type=\"text\" class=\"tw_form\" id=\"telCliente\" name=\"telCliente\"  value=\"".toCharArray();
    __oracle_jsp_text[8] = 
    "\"  size=\"25\" maxlength=\"25\"></input></td>\n    <td>Moneda:</td>\n    <td>\n     <select name=\"listaMoneda\" id=\"listaMoneda\" style=\"border:solid 1px #005C8D;\" onfocus=\"javascript:this.style.background='#FFFFFF';\">\n                <option value=\"\" >Seleccionar</option>\n                ".toCharArray();
    __oracle_jsp_text[9] = 
    "\n                  ".toCharArray();
    __oracle_jsp_text[10] = 
    "\n                    ".toCharArray();
    __oracle_jsp_text[11] = 
    "\n                       <option value=\"".toCharArray();
    __oracle_jsp_text[12] = 
    "\" style=\"background-color:#A4BAC7;\" selected=\"selected\">\n                        ".toCharArray();
    __oracle_jsp_text[13] = 
    "\n                      </option> \n                    ".toCharArray();
    __oracle_jsp_text[14] = 
    "\n                    ".toCharArray();
    __oracle_jsp_text[15] = 
    "\n                    <option value=\"".toCharArray();
    __oracle_jsp_text[16] = 
    "\" style=\"background-color:#A4BAC7;\">\n                        ".toCharArray();
    __oracle_jsp_text[17] = 
    "\n                    </option>\n                    ".toCharArray();
    __oracle_jsp_text[18] = 
    "\n                    ".toCharArray();
    __oracle_jsp_text[19] = 
    "\n                ".toCharArray();
    __oracle_jsp_text[20] = 
    "\n       </select> \n    </td>\n  </tr>\n  <tr>\n    <td>Dirección:</td>\n    <td><input type=\"text\" class=\"tw_form\" id=\"dirCliente\" name=\"dirCliente\"   value=\"".toCharArray();
    __oracle_jsp_text[21] = 
    "\" size=\"50\" maxlength=\"250\"></input></td>\n    <td>Codigo Postal:</td>\n    <td><input type=\"text\" class=\"tw_form\" id=\"cpCliente\" name=\"cpCliente\"  value=\"".toCharArray();
    __oracle_jsp_text[22] = 
    "\"  size=\"10\" maxlength=\"10\"></input></td>\n    <td>País:</td>\n    <td><input type=\"text\" class=\"tw_form\" id=\"paisCliente\" name=\"paisCliente\"  value=\"".toCharArray();
    __oracle_jsp_text[23] = 
    "\"  size=\"20\" maxlength=\"100\"></input></td>    \n  </tr> \n  </table>\n  <br>\n  <hr class=\"tw_hr\">\n  <table width=\"100%\">\n  <tr>\n  <td>Tarifas:</td>\n  <td><select name=\"listaTarifa\" id=\"listaTarifa\" style=\"border:solid 1px #005C8D;\" onfocus=\"javascript:this.style.background='#FFFFFF';\">\n                <option value=\"\" >Seleccionar</option>\n                ".toCharArray();
    __oracle_jsp_text[24] = 
    "\n                   ".toCharArray();
    __oracle_jsp_text[25] = 
    "\n                    ".toCharArray();
    __oracle_jsp_text[26] = 
    "\n                       <option value=\"".toCharArray();
    __oracle_jsp_text[27] = 
    "\" style=\"background-color:#A4BAC7;\" selected=\"selected\">\n                        ".toCharArray();
    __oracle_jsp_text[28] = 
    "\n                      </option> \n                    ".toCharArray();
    __oracle_jsp_text[29] = 
    "\n                    ".toCharArray();
    __oracle_jsp_text[30] = 
    "\n                    <option value=\"".toCharArray();
    __oracle_jsp_text[31] = 
    "\" style=\"background-color:#A4BAC7;\">\n                        ".toCharArray();
    __oracle_jsp_text[32] = 
    "\n                    </option>\n                    ".toCharArray();
    __oracle_jsp_text[33] = 
    "\n                    ".toCharArray();
    __oracle_jsp_text[34] = 
    "\n                ".toCharArray();
    __oracle_jsp_text[35] = 
    "\n       </select>\n    </td>\n  </tr>\n  </table>\n  <table width=\"100%\">\n  <tr>\n  <td align=\"right\"><input type=\"button\" id=\"aceptar\" value=\"Aceptar\" onclick=\"agregar()\"/></td>   \n  <td align=\"left\"><input type=\"button\" id=\"cancel\" value=\"Cancelar\" OnClick=\"cancelar()\"/></td>   \n  </tr>\n  </table>\n  \n  <div id=\"div-clientes\"  >\n  <table id =\"tabla-clientes\" >\n  <tr><th>Nombre o Razón social</th><th>Decripción</th><th>Teléfono</th><th>Mail</th><th>&nbsp;</th></tr>\n  </table>\n  \n  </div>\n  </form>\n  </body>\n</html>".toCharArray();
    }
    catch (Throwable th) {
      System.err.println(th);
    }
}
}
