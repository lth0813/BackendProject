package com.example.blog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.dao.BlogDao;



@Controller
public class DownloadController {
    @Autowired
    BlogDao blogdao;

    @GetMapping("/boarddownload")
    public ResponseEntity<Resource> download(@RequestParam("seq") String seq) throws Exception {
        String downloadFolder = "C:/Kepco A/blog_lth/src/main/resources/static/images/";
        String fileName = blogdao.selectBoard(seq).get(0).get("imagename").toString();
        File file = new File(downloadFolder+fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
            .header("content-disposition","filename="+URLEncoder.encode(fileName, "utf-8"))
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);
    }
}
