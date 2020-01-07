package cn.htz.chs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@Slf4j
public class MediaStreamController {

    @GetMapping("media_stream")
    public void getStream(HttpServletRequest request, HttpServletResponse httpServletResponse, String filePath) {
        System.out.println("getStream filePath:" + filePath);
        //"C:\\htz\\resources\\videos\\mixinxiaoshipin\\[2019-05-21]_001.mp4"
        byte[] bytes = file2byte(filePath);


        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setContentLength(bytes.length);
        try {
            httpServletResponse.getOutputStream().write(bytes);
        } catch (IOException e) {
            System.out.println("IO异常----");
        }
    }

    public byte[] file2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
