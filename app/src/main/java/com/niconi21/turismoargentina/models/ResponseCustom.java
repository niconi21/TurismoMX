package com.niconi21.turismoargentina.models;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseCustom {
    private Boolean ok = false;
    private int status = 0;
    private String message = "";
    private Result result = new Result();

    public ResponseCustom() {
    }

    public void setResponse(JSONObject response) {
        try {
            this.message = response.getString("message");
            this.ok = response.getBoolean("ok");
            this.status = response.getInt("status");
            this.result = this.result.parseResult(response.getJSONObject("result"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
