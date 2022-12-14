package com.ems.bulkEmail.buisness;

public class HtmlFormatter implements IFormatMail{
    @Override
    public String formatMail(String components) {
        String htmlContent="<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  </head>\n" +
                "  <body>" +components+
                "</body>" +
                "</html>";
        return htmlContent;

    }
}
