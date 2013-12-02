package com.antilope.openutils.base;

import java.util.List;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


public class DwrUtils {
	
	public static void main(String[] args) {
		
	}
	
	
	public void runClientFun(String funName){
		WebContext context = WebContextFactory.get();
		ScriptBuffer buffer = new ScriptBuffer();
		buffer.appendScript(funName+"(");
		buffer.appendScript(")");
		ScriptSession scse = context.getScriptSession();
		scse.addScript(buffer);
	}
	
	public void runClientFunOfString(String funName,String info){
		WebContext context = WebContextFactory.get();
		ScriptBuffer buffer = new ScriptBuffer();
		buffer.appendScript(funName+"(");
		buffer.appendScript(info);
		buffer.appendScript(")");
		ScriptSession scse = context.getScriptSession();
		scse.addScript(buffer);
	}
	
	public void runClientFunOfList(String funName,List list){
		WebContext context = WebContextFactory.get();
		ScriptBuffer buffer = new ScriptBuffer();
		buffer.appendScript(funName+"(");
		buffer.appendData(list);
		buffer.appendScript(")");
		ScriptSession scse = context.getScriptSession();
		scse.addScript(buffer);
	}
	
	public static void runClientFunOfObject(String funName,Object obj){
	
		WebContext context = WebContextFactory.get();
		ScriptBuffer buffer = new ScriptBuffer();
		buffer.appendScript(funName+"(");
		buffer.appendData(obj);
		buffer.appendScript(")");
		ScriptSession scse = context.getScriptSession();
		scse.addScript(buffer);
	}
}
