package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

public class PageDto {
    private Integer size=10;//default value
    private Integer no=0;//default value

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}
