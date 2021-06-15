package cool.kar.neteasecloudmusic.demo.vo;

public class ErrorVO extends VO {
    public ErrorVO(Object data) {
        super();
        this.code = 50000;
        this.data = data;
    }

    public ErrorVO() {
        super();
        this.code = 50000;
        this.data = null;
    }

}
