package cool.kar.neteasecloudmusic.demo.vo;

import lombok.Data;


public class SuccessVO extends VO {
    public SuccessVO(Object data) {
        super();
        this.code = 20000;
        this.data = data;
    }
    public SuccessVO() {
        super();
        this.code = 20000;
        this.data = null;
    }
}
