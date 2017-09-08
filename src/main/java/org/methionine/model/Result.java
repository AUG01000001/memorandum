package org.methionine.model;

public class Result {

    String massage;
    Object result;

    public Result(String massage, Object result) {
        this.massage = massage;
        this.result = result;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
