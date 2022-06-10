package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

public class PageDto {
    private Integer size=10;//default value
    private Integer page =0;//default value

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
