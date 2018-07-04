package college.result;

import college.constant.CollegeConstant;

/**
 * 返回结果
 * @author xuxb
 *
 */
public class ResultVO {

	private String Code;

	private String msg;

	private Object data;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResultVO() {
		this(null);
	}
	
	public ResultVO(String msg) {
		this(msg, null);
	}
	
	public ResultVO(String msg, Object data) {
		this.Code = CollegeConstant.RESPONSECODE_SUCC;
		this.data = data;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return (new StringBuilder("Code")).append(":").append(Code).append(":").append(msg).toString();
	}

}
