package com.example.demo.model.vo;

import com.example.demo.exception.SQLInjectionException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@ApiModel(value = "PageVo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PageVo implements IPage, Serializable {

    private static final String NOT_NULL = "Can Not Be Null";
    private static final String NUMBER_BETWEEN_0_1 = "Value Should Be 0 Or 1";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数量", required = true)
    @NotNull(message = NOT_NULL)
    private Integer count;

    @ApiModelProperty(value = "页", required = true)
    @NotNull(message = NOT_NULL)
    private Integer page;

    @ApiModelProperty(value = "排序")
    private String field;

    @ApiModelProperty(value = "1: 升序（true），0: 降序（false）")
    @Range(min = 0, max = 1, message = NUMBER_BETWEEN_0_1)
    private Integer isAsc = 0;

    @ApiModelProperty(value = "排序字段")
    private String orderBy;

    /**
     * 获取分页参数
     * @return
     */
    public IPage getPageParam() {
        check();
        return this;
    }

    @Override
    public Integer getPageNum() {
        return this.page;
    }

    @Override
    public Integer getPageSize() {
        return this.count;
    }

    private boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        return "".equals("" + obj);
    }

    public void check() {

        // 页面条数: 默认为20
        if (isEmpty(count) || count < 0) {
            count = 20;
        }
        // 默认为第一页
        if (isEmpty(page) || page < 1) {
            page = 1;
        }
        // 是否升序: 默认为升序
        // 前端只能传0或1
        // 1代表升序，0代表降序
        if (isEmpty(isAsc) || (isAsc != 0 && isAsc != 1)) {
            isAsc = 1;
        }
        if (isEmpty(field)) {
            orderBy = null;
        } else {
            Pattern pattern= Pattern.compile("\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or)\\b|(\\*|;|\\+|-|'|%)");
            Matcher matcher=pattern.matcher(field.toString().toLowerCase());
            if (matcher.find()) {
                throw new SQLInjectionException();
            }
            orderBy = field + " " + (isAsc == 1 ? "ASC" : "DESC");
        }
    }

    public Map<String, Object> genMap() {
        check();
        Map<String, Object> map = new HashMap<>(4);
        map.put("limit", count);
        map.put("page", page);
        map.put("site", (page - 1) * count);
        if (!isEmpty(field)) {
            map.put("field", field);
            map.put("isAsc", isAsc == 1 ? "ASC" : "DESC");
        }
        return map;
    }
}
