package com.antilope.openutils.protocol.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCraw {
	
//	<tr logr="j_2_16983893461766_17573771378563_3_2_0">
// 	<td class="img"><a href="http://bj.58.com/dog/17573771378563x.shtml" target="_blank"     ><img src="http://pic8.58cdn.com.cn/p1/tiny/n_s12260179281039435144.jpg" alt="" /><span class='datu-shu'>20图</span></a></td>
// 	<td class="t">
// 		<a href="http://bj.58.com/dog/17573771378563x.shtml"   target="_blank" class="t" tips="tips">
//		                 		【双色精品柯基幼犬】——【实物拍摄疫苗做齐可刷卡】</a>
// 		<span class="wb-m-wlt-ico" title="58.com建议您优先选择网邻通商家"><i>1</i></span>
//		<span name="zaixian_16983893461766"></span>
//        <i class="clear"></i>
//        <p class="pet-post-detail clearfix">
//										<font>3个月</font><span>/</span>							<font>3针疫苗</font><span>/</span>							<font>已驱虫</font><span>/</span>							<font>30只在售</font><span>/</span>							<font>公母均有</font><span>/</span>                                                   	                           		<font>朝阳</font><span>/</span>
//       	    							<font>今天</font>
//        </p>
//               <p class="pet-detail pet-detail1"><span><a class="xiaobao xiaobao2" href="/buyer.shtml" target="_blank" title="宠物保障计划"></a><i><i class="red">&nbsp;&nbsp;CKU</i>纯种<i class="red">&nbsp;&nbsp;3年内</i>包治<i class="red">&nbsp;&nbsp;90天</i>包退换</i></span><span><i class="pet-pic"></i><i>种公种母</i></span></p>
//		</td>
// 	<td class="tc">
//     	            	         <b class='pri'>3000</b> 元                 	                     	</td>
//</tr>
	public static ExecutorService  crawPool =  Executors.newFixedThreadPool(5);
	public static String baseUrl = "http://bj.58.com/dog/pn%num%/";
	public static final int maxPage = 2;
	public static void main(String[] args) {
		int count = 0;
		for(int i = 0 ; i < maxPage; i ++){
			String contants = HttpUtils.doGet(baseUrl.replace("%num%", i+""));
			//数据解析
			String []trContent = contants.split("<tr logr");
			for(String reContent : trContent){
				try {
					String name = reContent.substring(reContent.indexOf("tips=\"tips\">"),reContent.indexOf("<span class=\"wb-m-wlt-ico\"")).replace("tips=\"tips\">", "").replace("</a>", "").trim();
					String ageContent = reContent.substring(reContent.indexOf("<p class=\"pet-post-detail"),reContent.indexOf("<p class=\"pet-detail"));
					String age = ageContent.substring(ageContent.indexOf("font>"),ageContent.indexOf("</font>")).replace("font>", "");
					String charge = reContent.substring(reContent.indexOf("<b class='pri'>"),reContent.indexOf("</b>")).replace("<b class='pri'>", "").trim();
					String img = reContent.substring(reContent.indexOf("img src="),reContent.indexOf("alt=")).trim().replace("img src=\"", "").replace("\"", "");
					System.out.println("-------------------------------------------------------------");
					System.out.println("名字:"+name+"\n年龄："+age+"\n售价："+charge+"\n图片地址"+img);
					System.out.println("-------------------------------------------------------------");
					count ++;
					
				} catch (Throwable e) {
					// TODO: handle exception
				}
//				Dog d = new Dog();
//				d.setName(name);
			}
			System.out.println("抓取总数 ：" + count);
		}
	}
	
	 class Dog{
		
		public Dog() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.name + " " + this.age +" "+this.charge+" "+this.img;
		}
		//图片地址
		private String img;
		//名字
		private String name;
		private String age;
		private double charge;
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public double getCharge() {
			return charge;
		}
		public void setCharge(double charge) {
			this.charge = charge;
		}
		
	}
	
}

