package com.antilope.openutils.base;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 *  json 工具类
 *
 * @author         :    xshwlx
 * @date           :    
 * @version        :    1.0 20120307 
 * @since          :    1.0 20120307
 *
 */
public class JsonUtils {

   
    public static List<Map<String, Object>> parseJSON2List(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }
    
    
   
    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
    
   
    public static List<Map<String, Object>> getListByUrl(String url){
        try {
            //通过HTTP获取JSON数据
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return parseJSON2List(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
   
    public static Map<String, Object> getMapByUrl(String url){
        try {
            //通过HTTP获取JSON数据
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return parseJSON2Map(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static Map parserToMap(String s){
    	Map map=new HashMap();
    	JSONObject json=JSONObject.fromObject(s);
    	Iterator keys=json.keys();
    	while(keys.hasNext()){
    	String key=(String) keys.next();
    	String value=json.get(key).toString();
    	if(value.startsWith("{")&&value.endsWith("}")){
    	map.put(key, parserToMap(value));
    	}else{
    	map.put(key, value);
    	}

    	}
    	return map;
    	}
    //test
    public static void main(String[] args) {
        String str = " [{'id':'00','univs':'','name':'中国','provs':[" +
        		"{'id':1,'univs':[{'id':1001,'name':'清华大学'},{'id':1003,'name':'中国人民大学'}],'country_id':0,'name':'北京'}," +
        		"{'id':2,'univs':[{'id':2001,'name':'复旦大学'},{'id':2002,'name':'上海交通大学'}],'country_id':0,'name':'上海'}," +
        		"{'id':3,'univs':[{'id':3001,'name':'哈尔滨工业大学'},{'id':3002,'name':'哈工程'}],'country_id':0,'name':'黑龙江'}," +
        		"{'id':34,'univs':[{'id':34001,'name':'国立台湾大学'},{'id':34002,'name':'国立中正大学'}],'country_id':0,'name':'台湾'}]" +
        		"}," +
        		"{'id':'01','univs':[" +
        		"{'id':100001,'name':'University of Sydney'},{'id':100002,'name':'University of New South Wales'}],'name':'澳大利亚','provs':''}," +
        		"{'id':'29','univs':[" +
        		"{'id':2900001,'name':'Akademie der bildenden Künste Wien'},{'id':2900002,'name':'Donau-Universit\u00e4t Krems'}],'name':'奥地利','provs':''}]";
        
        String str2 = "{'id':'01','univs':[" +
                                  		"{'id':100001,'name':'University of Sydney'},{'id':100002,'name':'University of New South Wales'}],'name':'澳大利亚','provs':''}";
        List<Map<String,Object>> ulist = getListByUrl("http://s.xnimg.cn/a37614/allunivlist.js");
        //List<Map<String, Object>> ulist = parseJSON2List(str);
        
        
        for(int i = 0;i <ulist.size();i++){
        	//System.out.println(ulist.get(i));
        	if(String.valueOf(ulist.get(i).get("provs")).length() > 0){
        		System.out.println(ulist.get(i).get("name"));
        		System.out.println(ulist.get(i).get("provs"));
        		List<Map<String,Object>>  citylist = (List<Map<String, Object>>) ulist.get(i).get("provs");
        		for(int j =0;j<citylist.size();j++){
        			System.out.println(citylist.get(j).get("name"));
        			System.out.println(citylist.get(j).get("univs"));
        		}
        		
        	}else{
        		System.out.println(ulist.get(i).get("name"));
        		System.out.println(ulist.get(i).get("univs"));
        	}
        	
        }
//       for(Iterator it = umap.entrySet().iterator();it.hasNext();){
//    	   Map.Entry  en = (Entry) it.next();
//    	   String key = (String) en.getKey();
//    	   Object value = en.getValue();
//    	   parseJSON2List(value.toString());
//    	   System.out.println(key+":"+value);
//    	   
//       }
        System.out.println(ulist.size());
    }
}
