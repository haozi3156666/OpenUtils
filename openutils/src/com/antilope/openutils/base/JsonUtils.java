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
 *  json ������
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
        //��������
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k); 
            //����ڲ㻹������Ļ�����������
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
            //ͨ��HTTP��ȡJSON����
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
            //ͨ��HTTP��ȡJSON����
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
        String str = " [{'id':'00','univs':'','name':'�й�','provs':[" +
        		"{'id':1,'univs':[{'id':1001,'name':'�廪��ѧ'},{'id':1003,'name':'�й������ѧ'}],'country_id':0,'name':'����'}," +
        		"{'id':2,'univs':[{'id':2001,'name':'������ѧ'},{'id':2002,'name':'�Ϻ���ͨ��ѧ'}],'country_id':0,'name':'�Ϻ�'}," +
        		"{'id':3,'univs':[{'id':3001,'name':'��������ҵ��ѧ'},{'id':3002,'name':'������'}],'country_id':0,'name':'������'}," +
        		"{'id':34,'univs':[{'id':34001,'name':'����̨���ѧ'},{'id':34002,'name':'����������ѧ'}],'country_id':0,'name':'̨��'}]" +
        		"}," +
        		"{'id':'01','univs':[" +
        		"{'id':100001,'name':'University of Sydney'},{'id':100002,'name':'University of New South Wales'}],'name':'�Ĵ�����','provs':''}," +
        		"{'id':'29','univs':[" +
        		"{'id':2900001,'name':'Akademie der bildenden K��nste Wien'},{'id':2900002,'name':'Donau-Universit\u00e4t Krems'}],'name':'�µ���','provs':''}]";
        
        String str2 = "{'id':'01','univs':[" +
                                  		"{'id':100001,'name':'University of Sydney'},{'id':100002,'name':'University of New South Wales'}],'name':'�Ĵ�����','provs':''}";
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
