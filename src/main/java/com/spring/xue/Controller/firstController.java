package com.spring.xue.Controller;

import com.spring.xue.service.firstService;
import com.spring.xue.utils.JWTUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/")
public class firstController {

    @Autowired
    firstService f;

    /**
     * @program: xue
     * @create: 2018-11-02-11-48
     *
     */
    @RequestMapping("/first")
    public String first() {
        String a = f.fitst();
        System.out.println("first");
        return a;
    }

    @RequestMapping("/fileupload")
    public Map<String, Object> fileUpload(MultipartFile filename) throws IOException {
        System.out.println("1---" + this.getClass().getResource("/") + filename.getOriginalFilename());
        System.out.println("2---" + this.getClass().getResource("/") + filename.getOriginalFilename());
        filename.transferTo(new File(this.getClass().getResource("/").getPath() + filename.getOriginalFilename()));
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("msg", 200);
        return obj;
    }

    @RequestMapping("/filedownload")
    public String filedownload(HttpServletResponse response) {
//        File file = new File();//加载一个文件
        List<String> list = Arrays.asList("1", "2", "3");
        HSSFWorkbook workbook = new HSSFWorkbook();//一个excel文件
        HSSFSheet sheet = workbook.createSheet("test");//新建一个work表
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(list.get(i));
        }


        response.reset();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "test.xls");

//        ServletOutputStream sout = response.getOutputStream();
//        BufferedOutputStream bouts = new BufferedOutputStream();
//        byte[] buff = new byte[2048];

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "200";
    }

//    @RequestMapping("/gettoken")
//    public String gettoken(String userId,String password){
//        String token = JWTUtils.sign(userId,password,"",1000L);
//        return token;
//    }

}
