package com.wjy.wisdom.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.League;
import com.wjy.wisdom.entity.dto.ImportLeagueDto;
import com.wjy.wisdom.mapper.LeagueMapper;
import com.wjy.wisdom.service.LeagueService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2019-02-02
 */
@Service
public class LeagueServiceImpl extends ServiceImpl<LeagueMapper, League> implements LeagueService {

    @Autowired
    private Validator defaultValidator;

    @Override
    public Page<League> selectWithLeague(Page<League> plusPage, Wrapper<League> leagueWrapper) {
        List<League> leagues = baseMapper.selectWithLeague(plusPage,leagueWrapper.eq("a.isDeleted",0));
        plusPage.setRecords(leagues);
        return plusPage;
    }

    @Override
    public List<ImportLeagueDto> importDataToDo(List<Map<String, Object>> importData) {
        List<ImportLeagueDto> studentDtos = new ArrayList<>();
        Field[] fields = League.class.getDeclaredFields(); //反射获取专业类中的所有字段

        //查询数据库的数据
        Wrapper<League> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("name").eq("isDeleted",false);
        List<League> leagues = baseMapper.selectList(wrapper); // 数据库的数据

        for(Map<String, Object>  data : importData){
            ImportLeagueDto leagueDto = new ImportLeagueDto();
            leagueDto.setSuccess(true);
            List<String> tips = new ArrayList<>();
            //错误字段
            List<String> errorField = createByReflect(leagueDto,fields,data);
            //验证返回列中是否存在
            //数据库重复检验
            if(leagueDto.getName() != null){
                Boolean exit = studentDtos.stream().filter(b -> leagueDto.getName().equals(b.getName())).count() > 0;
                if(exit) {continue;} //存在就直接跳出
                    exit = leagues.stream().filter(b -> leagueDto.getName().equals(b.getName())).count() > 0;
                    if (exit) {
                        leagueDto.setSuccess(true);
                        tips.add(leagueDto.getName()+"学生信息已存在");
                    }
            }

            //字段转换失败
            if(errorField.size()>0){
                tips.add("字段转换失败：" + String.join(";",errorField));
                leagueDto.setSuccess(false);
            }

            //验证
            Set<ConstraintViolation<ImportLeagueDto>> violations = defaultValidator.validate(leagueDto);//验证
            if(violations.size()>0){
                leagueDto.setSuccess(false);
                String err = String.join(";",violations.stream().map(b ->{
                    return b.getMessage();
                }).collect(Collectors.joining()));
                tips.add("字段验证失败："+err);
            }
            leagueDto.setTips(String.join(".",tips));
            studentDtos.add(leagueDto);
        }
        return studentDtos;
    }

    @Override
    public Page<League> selectWithLeagueWithGanbu(Page<League> plusPage, Wrapper<League> leagueWrapper) {
        List<League> leagues = baseMapper.selectWithLeague(plusPage,leagueWrapper.eq("a.isDeleted",0)
        .eq("a.tuanGan","是"));
        plusPage.setRecords(leagues);
        return plusPage;
    }

    /**
     * 创建通过反射
     * @param student
     * @param fields
     * @param data
     * @return List: 错误信息数据
     */
    private List<String> createByReflect(ImportLeagueDto studentDto, Field[] fields, Map<String, Object> data) {
        List<String> errfiledList = new ArrayList<>();
        for (Field field : fields) {
            String key = field.getName();
            Object val = data.get(key);
            if (val == null) {continue;}
            try {
                //取消访问权限控制
                field.setAccessible(true);
                if (field.getType().equals(Integer.class)) {
                    field.set(studentDto, new Integer(data.get(key).toString()));
                } else {
                    field.set(studentDto, data.get(key));
                }
            } catch (IllegalAccessException e) {
                errfiledList.add(e.toString() + "填入失败");
            }
        }
        return errfiledList;
    }
}
