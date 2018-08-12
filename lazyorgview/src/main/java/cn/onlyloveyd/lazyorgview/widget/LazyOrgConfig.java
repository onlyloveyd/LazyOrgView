package cn.onlyloveyd.lazyorgview.widget;

import android.graphics.Color;
import cn.onlyloveyd.lazyorgview.R;

/**
 * 文 件 名: LazyOrgConfig
 * 创建日期: 2018/08/12 08/10
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 *
 * @author: yidong
 */
public class LazyOrgConfig {

	/**
	 * 连接线的宽度
	 */
	private int lineWidth = 1;

	/**
	 * 连接线的颜色
	 */
	private int lineColor = Color.BLACK;

	/**
	 * 字体大小
	 */
	private int textSize = 14;

	/**
	 * 文字颜色
	 */
	private int textColor = Color.BLACK;

	/**
	 * 文本框背景
	 */
	private int textBgRes = R.drawable.rect_shape;

	/**
	 * 文本框背景色
	 */
	private int textBgColor = Color.WHITE;

	public LazyOrgConfig() {
	}

	public int getLineWidth() {
		return lineWidth;
	}

	public LazyOrgConfig setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
		return this;
	}

	public int getLineColor() {
		return lineColor;
	}

	public LazyOrgConfig setLineColor(int lineColor) {
		this.lineColor = lineColor;
		return this;
	}

	public int getTextSize() {
		return textSize;
	}

	public LazyOrgConfig setTextSize(int textSize) {
		this.textSize = textSize;
		return  this;
	}

	public int getTextColor() {
		return textColor;
	}

	public LazyOrgConfig setTextColor(int textColor) {
		this.textColor = textColor;
		return this;
	}

	public int getTextBgRes() {
		return textBgRes;
	}

	public LazyOrgConfig setTextBgRes(int textBgRes) {
		this.textBgRes = textBgRes;
		return this;
	}

	public int getTextBgColor() {
		return textBgColor;
	}

	public LazyOrgConfig setTextBgColor(int textBgColor) {
		this.textBgColor = textBgColor;
		return this;
	}
}
