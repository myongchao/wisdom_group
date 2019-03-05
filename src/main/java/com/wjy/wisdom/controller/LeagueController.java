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
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.Transient;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
        Date date = new Date();
        league.setStatus("正常");
        league.setApplyDate(date);
        JsonResultEntity resultEntity = JsonResultUtils.success(leagueService.insert(league));
        return resultEntity;
    }

    @ApiOperation(value = "批量添加团员信息")
    @PostMapping(value = "multipleAdd")
    public JsonResultEntity multipleAdd(@RequestBody ListLeagueDto leagueDto){
        for (League l:leagueDto.getData()) {
            Date date = new Date();
            l.setLeagueTime(date);
            l.setApplyDate(date);
        }
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


    @ApiOperation(value = "导出 ")
    @RequestMapping(value = "UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        List<League> classmateList = leagueService.selectList(new EntityWrapper<League>());

        String fileName = "league"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = {"姓名","身份证号码","民族","政治面貌","文化程度","手机号","入团日期","QQ","是否团干","团干性质","现任职务","是否党员","是否管理员"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //在表中存放查询到的数据放入对应的列
        for (League teacher : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getName());
            row1.createCell(1).setCellValue(teacher.getIdNumber());
            row1.createCell(2).setCellValue(teacher.getNational());
            row1.createCell(3).setCellValue(teacher.getPoliticalLandscape());
            row1.createCell(4).setCellValue(teacher.getEducation());
            row1.createCell(5).setCellValue(teacher.getPhone());
            row1.createCell(6).setCellValue(format.format(teacher.getLeagueTime()));
            row1.createCell(7).setCellValue(teacher.getQq());
            row1.createCell(8).setCellValue(teacher.getTuanGan());
            row1.createCell(9).setCellValue(teacher.getTuanGanXZ());
            row1.createCell(10).setCellValue(teacher.getPosition());
            row1.createCell(11).setCellValue(teacher.getPartyMember());
            row1.createCell(12).setCellValue(teacher.getIsAdmin());
            rowNum++;
        }

        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


}

