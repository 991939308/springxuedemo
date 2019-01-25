package com.spring.xue.Controller;

import com.spring.xue.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单管理
 */
@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

//    @Autowired
//    private MenuService menuService;
    /**
     * 初始化菜单页面
     */
    @RequestMapping("/initMenuPage")
    public void  initMenu(){
//        menuService.initMenuPage();

    }

    /**
     * 新增菜单
     */
    @RequestMapping("/addMenuPage")
    public void  addMenuPage(){

//        menuService.addMenuPage();

    }
    /**
     * 查询菜单
     */
    @RequestMapping("/searchMenuPage")
    public void  searchMenuPage(){


    }
    /**
     * 修改菜单
     */
    @RequestMapping("/updateMenuPage")
    public void  updateMenuPage(){


    }
}
