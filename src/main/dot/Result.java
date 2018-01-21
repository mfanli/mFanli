package main.dot;

public class Result {
    private String retCode;
    private String retMsg;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Result (String retCode, String retMsg)
    {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
}
