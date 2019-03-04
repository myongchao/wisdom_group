package com.wjy.wisdom.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.League;
import com.wjy.wisdom.entity.dto.ImportLeagueDto;
import com.wjy.wisdom.entity.dto.ListLeagueDto;
import com.wjy.wisdom.entity.jsonUtil.JsonResultEntity;
import com.wjy.wisdom.entity.jsonUtil.JsonResultUtils;
import com.wjy.wisdom.mian.entity.CommonSearchDto;
import com.wjy.wisdom.service.LeagueService;
import com.wjy.wisdom.util.ExcelUtil;
import com.wjy.wisdom.util.ExportExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-02-02
 */
@RestController
@RequestMapping("/league")
@Api(value = "团员管理",description = "团员管理")
public class LeagueController implements Serializable{

    @Autowired
    private LeagueService leagueService;

    private final static String templatePath = "/template/leagueImportTemplate.xlsx";

    @ApiOperation(value = "团员模板下载", notes = "模板下载")
    @GetMapping("/download/template")
    public JsonResultEntity download() {
        String path = templatePath;
        JsonResultEntity resultEntity = JsonResultUtils.success(path);
        return resultEntity;
    }

    @ApiOperation(value = "添加团员信息")
    @PostMapping(value = "add")
    public JsonResultEntity add(@RequestBody League league){
        league.setStatus("正常");
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.insert(league));
        return resultEntity;
    }

    @ApiOperation(value = "批量添加团员信息")
    @PostMapping(value = "multipleAdd")
    public JsonResultEntity multipleAdd(@RequestBody ListLeagueDto leagueDto){
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.insertBatch(leagueDto.getData()));
        return resultEntity;
    }

    @ApiOperation(value = "删除团员信息")
    @PostMapping(value = "del")
    public JsonResultEntity del(@RequestParam("id") Long id){
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.deleteById(id));
        return resultEntity;
    }

    @ApiOperation(value = "修改团员信息")
    @PostMapping(value = "edit")
    public JsonResultEntity update(@RequestBody League league){
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.updateById(league));
        return resultEntity;
    }

    @ApiOperation(value = "添加管理员")
    @PostMapping(value = "addManager")
    public JsonResultEntity addManager(@RequestBody League league){
        League league1 = leagueService.selectOne(new EntityWrapper<League>()
        .eq("id",league.getId())
        .eq("position",league.getPosition()));
        league1.setIsAdmin("是");
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.updateById(league1));
        return resultEntity;
    }

    @ApiOperation(value = "修改团干部信息")
    @PostMapping(value = "editGanbu")
    public JsonResultEntity updateGanbu(@RequestBody League league){
        String name = league.getName();
        League league1 = leagueService.selectOne(new EntityWrapper<League>().eq("name",name));
        league1.setPosition(league.getPosition());
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.updateById(league1));
        return resultEntity;
    }

    @ApiOperation(value = "获取单个团员信息")
    @GetMapping(value = "/get")
    public JsonResultEntity getOne(@RequestParam("id")Long id){
        League league = leagueService.selectById(id);
        JsonResultEntity resultEntity = JsonResultUtils.success(league);
        return resultEntity;
    }

    @ApiOperation(value = "团员列表")
    @GetMapping(value = "/list")
    public JsonResultEntity list(){
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.selectList(new EntityWrapper<>()));
        return resultEntity;
    }

    @ApiOperation(value = "团干部列表非管理")
    @GetMapping(value = "/listTuangan")
    public JsonResultEntity listTuangan(){
        List<League> leagues = leagueService.selectList(new EntityWrapper<League>()
                .eq("tuanGan","是")
                .eq("is_admin","否"));
        JsonResultEntity resultEntity = JsonResultUtils.success(leagues);
        return resultEntity;
    }

    @ApiOperation(value = "团员分页")
    @PostMapping(value = "page")
    public JsonResultEntity page(@RequestBody CommonSearchDto<League> searchDto) throws JSONException {
        Page<League> page =
                leagueService.selectWithLeague(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{"name"},"a."));
        JsonResultEntity resultEntity = JsonResultUtils.success(page);
        return resultEntity;
    }

    @ApiOperation(value = "团干部分页")
    @PostMapping(value = "pageWithGanbu")
    public JsonResultEntity pageWithGanbu(@RequestBody CommonSearchDto<League> searchDto) throws JSONException {
        Page<League> page =
                leagueService.selectWithLeagueWithGanbu(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{"name","isAdmin"},"a."));
        JsonResultEntity resultEntity = JsonResultUtils.success(page);
        return resultEntity;
    }

    @ApiOperation(value = "解析Excel文件",notes = "解析Excel文件")
    @PostMapping(value = "/import")
    @ResponseBody
    public JsonResultEntity upLoad(MultipartFile file){
        String[] a = {"name","idNumber","national","politicalLandscape","education","phone","qq","tuanGan","tuanGanXZ","position","partyMember"};
        List<String> fields = Arrays.asList(a);
        List<Map<String, Object>> mapList = null;
        try {
            Workbook workbook = null;
            String filename = file.getOriginalFilename();
            if (filename.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }else if(filename.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            }
            mapList = ExcelUtil.readFilds(workbook, fields);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ImportLeagueDto> studentDtos = leagueService.importDataToDo(mapList);
        JsonResultEntity resultEntity = JsonResultUtils.success(studentDtos);
        return resultEntity;
    }


    @ApiOperation(value = "导出",notes = "导出")
    @PostMapping(value = "/export")
    @ResponseBody
    public void export(@RequestBody List<Long> ids, HttpServletResponse response){
        //获取数据
        List<League> list = leagueService.selectList(new EntityWrapper<League>().in("id",ids));
        //excel标题
        String[] title = {"姓名","身份证号码","民族","政治面貌","文化程度","手机号","入团日期","QQ",}; //是否团干","团干性质","现任职务","是否党员","是否管理员"

        //创建院系实例
        // Department department = new Department();
        //标准时间格式为：年月日时分秒
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //excel文件名
        //String fileName = "专业信息表"+System.currentTimeMillis()+".xls";
        String fileName = "123"+".xlsx";
        //sheet名
        String sheetName = "团员信息表";
        int line = 0;
        line = list.size();
        String [][] content = new String[line][];
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            League obj = list.get(i);
//            Long dId = Long.valueOf(String.valueOf(obj.getDepartmentsId()));
//            department = departmentService.selectOne(new EntityWrapper<Department>().eq("id",dId));
            content[i][0] = obj.getName();
            content[i][1] = obj.getIdNumber();
            content[i][2] = obj.getNational();
            content[i][3] = obj.getPoliticalLandscape();
            content[i][4] = obj.getEducation();
            content[i][5] = obj.getPhone();
            content[i][6] = format.format(obj.getLeagueTime());
            content[i][7] = obj.getQq();
//            content[i][8] = obj.getTuanGan();
//            content[i][9] = obj.getTuanGanXZ();
//            content[i][10] = obj.getPosition();
//            content[i][11] = obj.getPartyMember();
//            content[i][8] = obj.getIsAdmin();
        }
//创建HSSFWorkbook
        HSSFWorkbook wb = ExportExcel.getHSSFWorkbook(sheetName, title, content, null);

//响应到客户端
        HSSFWorkbook sss = new HSSFWorkbook();
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.printf(response);
    }

    /**
     * @description  发送响应流方法
     * @author 马勇超
     * @date 2018/11/28 8:55
     * @return
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}

