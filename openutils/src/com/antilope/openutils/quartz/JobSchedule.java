package com.antilope.openutils.quartz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public  class JobSchedule{

	
	
	  @Override
		public JobSchedule clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return (JobSchedule)super.clone();
		}
	  
	  
    public JobSchedule(){
    	
    }
    
    protected Class dealClass;
	public Class getDealClass() {
		return dealClass;
	}

	public void setDealClass(Class dealClass) {
		this.dealClass = dealClass;
	}
	
	protected String cronTriggerName;
	public String getCronTriggerName() {
		return cronTriggerName;
	}

	public void setCronTriggerName(String cronTriggerName) {
		this.cronTriggerName = cronTriggerName;
	}

	protected Map jobDataMap;
	public Map getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(Map jobDataMap) {
		this.jobDataMap = jobDataMap;
	}
	
    public void clear(){
        this.job_scheduleID=0;
        this.createTime=new Date();
        this.type=0;
        this.name="";
        this.groupName="";
        this.resdata_addpolicyID=0;
        this.startTime=new Date();
        this.endTime=new Date();
        this.processTime=new Date();
        this.scheduleTime=new Date();
        this.fireTime=new Date();
        this.finishTime=new Date();
        this.nextFireTime=new Date();
        this.previousFireTime=new Date();
        this.runStatus=0;
        this.statusTime=new Date();
        this.description="";
        this.jobType=0;
        this.cronExp="";

    }

    protected int job_scheduleID;
    
    public void setJob_scheduleID(int job_scheduleID){
        this.job_scheduleID=job_scheduleID;
    }
    public int getJob_scheduleID(){
        return this.job_scheduleID;
    }

    protected Date createTime;
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public Date getCreateTime(){
        return this.createTime;
    }

    protected int type;
    public void setType(int type){
        this.type=type;
    }
    public int getType(){
        return this.type;
    }

    protected String name;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

    protected String groupName;
    public void setGroupName(String groupName){
        this.groupName=groupName;
    }
    public String getGroupName(){
        return this.groupName;
    }

    protected int resdata_addpolicyID;
    public void setResdata_addpolicyID(int resdata_addpolicyID){
        this.resdata_addpolicyID=resdata_addpolicyID;
    }
    public int getResdata_addpolicyID(){
        return this.resdata_addpolicyID;
    }

    protected Date startTime;
    public void setStartTime(Date startTime){
        this.startTime=startTime;
    }
    public Date getStartTime(){
        return this.startTime;
    }

    protected Date endTime;
    public void setEndTime(Date endTime){
        this.endTime=endTime;
    }
    public Date getEndTime(){
        return this.endTime;
    }

    protected Date processTime;
    public void setProcessTime(Date processTime){
        this.processTime=processTime;
    }
    public Date getProcessTime(){
        return this.processTime;
    }

    protected Date scheduleTime;
    public void setScheduleTime(Date scheduleTime){
        this.scheduleTime=scheduleTime;
    }
    public Date getScheduleTime(){
        return this.scheduleTime;
    }

    protected Date fireTime;
    public void setFireTime(Date fireTime){
        this.fireTime=fireTime;
    }
    public Date getFireTime(){
        return this.fireTime;
    }

    protected Date finishTime;
    public void setFinishTime(Date finishTime){
        this.finishTime=finishTime;
    }
    public Date getFinishTime(){
        return this.finishTime;
    }

    protected Date nextFireTime;
    public void setNextFireTime(Date nextFireTime){
        this.nextFireTime=nextFireTime;
    }
    public Date getNextFireTime(){
        return this.nextFireTime;
    }

    protected Date previousFireTime;
    public void setPreviousFireTime(Date previousFireTime){
        this.previousFireTime=previousFireTime;
    }
    public Date getPreviousFireTime(){
        return this.previousFireTime;
    }

    protected int runStatus;
    public void setRunStatus(int runStatus){
        this.runStatus=runStatus;
    }
    public int getRunStatus(){
        return this.runStatus;
    }

    protected Date statusTime;
    public void setStatusTime(Date statusTime){
        this.statusTime=statusTime;
    }
    public Date getStatusTime(){
        return this.statusTime;
    }

    protected String description;
    public void setDescription(String description){
        this.description=description;
    }
    public String getDescription(){
        return this.description;
    }

    protected int jobType;
    public void setJobType(int jobtype){
        this.jobType=jobtype;
    }
    public int getJobType(){
        return this.jobType;
    }

    protected String cronExp;
    public void setCronExp(String cronExp){
        this.cronExp=cronExp;
    }
    public String getCronExp(){
        return this.cronExp;
    }





	public boolean set(ResultSet rs) {
		try {
            this.job_scheduleID=rs.getInt("job_scheduleID");
            this.createTime=rs.getDate("createTime");
            this.type=rs.getInt("type");
            this.name=rs.getString("name");
            this.groupName=rs.getString("groupName");
            this.resdata_addpolicyID=rs.getInt("resdata_addpolicyID");
            this.startTime=rs.getDate("startTime");
            this.endTime=rs.getDate("endTime");
            this.processTime=rs.getDate("processTime");
            this.scheduleTime=rs.getDate("scheduleTime");
            this.fireTime=rs.getDate("fireTime");
            this.finishTime=rs.getDate("finishTime");
//            this.nextFireTime=rs.getDate("nextFireTime");
            this.previousFireTime=rs.getDate("previousFireTime");
            this.runStatus=rs.getInt("runStatus");
            this.statusTime=rs.getDate("statusTime");
            this.description=rs.getString("description");
            this.jobType=rs.getInt("jobtype");
            this.cronExp=rs.getString("cronExp");

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
   
}
