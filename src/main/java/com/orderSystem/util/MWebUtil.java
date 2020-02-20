package com.orderSystem.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class MWebUtil {

	/**
	 * 使用此函数web.xml上要添加 <listener> <listener-class>
	 * org.springframework.web.context.request.RequestContextListener
	 * </listener-class> </listener>
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}


	public static String getIP() {
		return getIP(getRequest());
	}

	public static String getIP(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public static String urlEncode(String str) {
		try {
			str = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			str = null;
		}
		return MStr.noNull(str);
	}

	public static String urlDecode(String str) {
		try {
			str = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			str = null;
		}
		return MStr.noNull(str);
	}

	public static String removeHtml(String html) {
		if (html == null)
			return null;
		if (html.length() == 0)
			return "";
		html = html.replaceAll("\\<.*?>", "");
		html = html.replaceAll("&nbsp;", "");
		html = html.replaceAll("&amp;", "");
		return html;
	}

	public static void redirect(HttpServletResponse response, String url)
			throws IOException {
		redirect(response, url, true);
	}

	public static void redirect(HttpServletResponse response, String url,
                                boolean noCache) throws IOException {
		if (noCache) {
			noCache(response);
		}
		response.sendRedirect(url);
	}

	public static void outputEnd(HttpServletResponse response, String info) {
		try {
			if (info != null) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(info);
			}
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void outputEnd(HttpServletResponse response) {
		outputEnd(response, null);
	}

	public static void outputJson(HttpServletResponse response, Object data) {

		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=UTF-8");
			MWebUtil.noCache(response);
			out = response.getWriter();
			out.append(JSONObject.toJSONString(data));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static void outputJson(HttpServletResponse response, JSONObject json) {

		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=UTF-8");
			MWebUtil.noCache(response);
			out = response.getWriter();
			out.append(json.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static void outputJson(HttpServletResponse response,
                                  JSONArray jsonArray) {

		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=UTF-8");
			MWebUtil.noCache(response);
			out = response.getWriter();
			out.append(jsonArray.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static void outputJsonArray(HttpServletResponse response,
                                       Object dataArray) {

		PrintWriter out = null;
		try {
			response.setContentType("application/json;charset=UTF-8");
			MWebUtil.noCache(response);
			out = response.getWriter();
			out.append(JSONObject.toJSONString(dataArray));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	public static void outputJsonResult(HttpServletResponse response, String error) {
		try {
			JSONObject json = new JSONObject();
			json.put("error", error);
			MWebUtil.outputJson(response, json);
		} catch (Exception e) {
			e.printStackTrace();
			MWebUtil.outputJson(response, "0");
		}
	}

	public static void outputJsonResultSuccess(HttpServletResponse response) {
		outputJsonResult(response, null);
	}

	public static void outputJsonResultFailure(HttpServletResponse response,
                                               String error) {
		outputJsonResult(response, error);
	}

	public static void noCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	}
}
