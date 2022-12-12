package com.ems.emailAnalytics;

import com.ems.emailAnalytics.buisness.Analytics;
import com.ems.emailAnalytics.buisness.ClickAnalytics;
import com.ems.emailAnalytics.buisness.PixelAnalytics;
import com.google.common.io.ByteStreams;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;

@Controller()
@RequestMapping("/analytics")
public class AnalyticsController {

    @RequestMapping(value = "/pixel", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getPixel(@RequestParam(required = true) String pixelid, HttpServletRequest request, HttpServletResponse response){
        try {

            Analytics analytics=new PixelAnalytics();
            analytics.performAnalytics(pixelid);
            File file= new File("./src/main/resources/static/assets/pixel.png");
            final InputStream in =
                    new DataInputStream(new FileInputStream(file));
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/click", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public RedirectView getClickUrl(@RequestParam(required = true) String clickid, @RequestParam(required = true) String link, HttpServletRequest request, HttpServletResponse response){

        Analytics analytics=new ClickAnalytics();
        analytics.performAnalytics(clickid);
        RedirectView redirectView = new RedirectView();
        String decodedUrl=new String((Base64.getDecoder().decode(link)));

        redirectView.setUrl(decodedUrl);
        return redirectView;

    }
}
