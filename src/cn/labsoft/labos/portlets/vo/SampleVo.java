package cn.labsoft.labos.portlets.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class SampleVo extends BaseVo{
	
	private int weekNew;
	private int weekFinished;
	private int weekNeeded;
	private double weekCharge;
	
	private int monthNew;
	private int monthFinished;
	private int monthNeeded;
	private double monthCharge;
	
	public int getWeekNew() {
		return weekNew;
	}
	public void setWeekNew(int weekNew) {
		this.weekNew = weekNew;
	}
	public int getWeekFinished() {
		return weekFinished;
	}
	public void setWeekFinished(int weekFinished) {
		this.weekFinished = weekFinished;
	}
	public int getWeekNeeded() {
		return weekNeeded;
	}
	public void setWeekNeeded(int weekNeeded) {
		this.weekNeeded = weekNeeded;
	}
	public double getWeekCharge() {
		return weekCharge;
	}
	public void setWeekCharge(double weekCharge) {
		this.weekCharge = weekCharge;
	}
	public int getMonthNew() {
		return monthNew;
	}
	public void setMonthNew(int monthNew) {
		this.monthNew = monthNew;
	}
	public int getMonthFinished() {
		return monthFinished;
	}
	public void setMonthFinished(int monthFinished) {
		this.monthFinished = monthFinished;
	}
	public int getMonthNeeded() {
		return monthNeeded;
	}
	public void setMonthNeeded(int monthNeeded) {
		this.monthNeeded = monthNeeded;
	}
	public double getMonthCharge() {
		return monthCharge;
	}
	public void setMonthCharge(double monthCharge) {
		this.monthCharge = monthCharge;
	}
}
