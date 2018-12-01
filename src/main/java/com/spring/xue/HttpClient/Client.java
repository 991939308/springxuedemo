package com.spring.xue.HttpClient;

import com.spring.xue.Bean.Parameter;
import com.spring.xue.mapper.MemeberMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * sdds
 * dsds
 * ds
 */
@Api(value="/test", tags="测试接口模块")
@Slf4j
@RestController
@RequestMapping("/")
public class Client {

    @Resource
    private MemeberMapper memeberMapper = null;

    /**
     * @param id
     * @return
     */
    @ApiOperation(value="测试数据", notes = "直接返回数据")
    @ResponseBody
    @RequestMapping(value = "/client",method = RequestMethod.POST)
    public Map accept(@RequestParam(name="id",required = true) Integer id){
        Map map = memeberMapper.findObjectById(1);


        return map;

    }

    @ApiOperation(value="测试数据2", notes = "发挥")
    @RequestMapping(value = "/clientbean",method = RequestMethod.POST)
    public String clientbean(@Valid Parameter parameter, BindingResult result ){
        if(result.hasErrors()){
            result.getAllErrors().stream().forEach(
                errors -> {
                    FieldError fieldError = (FieldError)errors;
                    log.info("错误字段：{} -> 错误信息：{}",
                            fieldError.getField(),fieldError.getDefaultMessage()
                    );
                }
            );
        }
        log.info("获取到的参数信息是->"+parameter);
        return parameter.getMessage();
    }
}
